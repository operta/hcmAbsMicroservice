package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.AbRequests;
import ba.infostudio.com.domain.Action;
import ba.infostudio.com.repository.AbRequestsRepository;
import ba.infostudio.com.web.rest.util.AuditUtil;
import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbVacationLeaveDays;

import ba.infostudio.com.repository.AbVacationLeaveDaysRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbVacationLeaveDaysDTO;
import ba.infostudio.com.service.mapper.AbVacationLeaveDaysMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing AbVacationLeaveDays.
 */
@RestController
@RequestMapping("/api")
public class AbVacationLeaveDaysResource {

    private final Logger log = LoggerFactory.getLogger(AbVacationLeaveDaysResource.class);

    private static final String ENTITY_NAME = "abVacationLeaveDays";

    private final AbVacationLeaveDaysRepository abVacationLeaveDaysRepository;

    private final AbVacationLeaveDaysMapper abVacationLeaveDaysMapper;

    private final AbRequestsRepository abRequestsRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public AbVacationLeaveDaysResource(AbVacationLeaveDaysRepository abVacationLeaveDaysRepository,
                                       AbVacationLeaveDaysMapper abVacationLeaveDaysMapper,
                                       AbRequestsRepository abRequestsRepository,
                                       ApplicationEventPublisher applicationEventPublisher) {
        this.abVacationLeaveDaysRepository = abVacationLeaveDaysRepository;
        this.abVacationLeaveDaysMapper = abVacationLeaveDaysMapper;
        this.abRequestsRepository = abRequestsRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * POST  /ab-vacation-leave-days : Create a new abVacationLeaveDays.
     *
     * @param abVacationLeaveDaysDTO the abVacationLeaveDaysDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abVacationLeaveDaysDTO, or with status 400 (Bad Request) if the abVacationLeaveDays has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-vacation-leave-days")
    @Timed
    public ResponseEntity<AbVacationLeaveDaysDTO> createAbVacationLeaveDays(@RequestBody AbVacationLeaveDaysDTO abVacationLeaveDaysDTO) throws URISyntaxException {
        log.debug("REST request to save AbVacationLeaveDays : {}", abVacationLeaveDaysDTO);
        if (abVacationLeaveDaysDTO.getId() != null) {
            throw new BadRequestAlertException("A new abVacationLeaveDays cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbVacationLeaveDays abVacationLeaveDays = abVacationLeaveDaysMapper.toEntity(abVacationLeaveDaysDTO);
        abVacationLeaveDays = abVacationLeaveDaysRepository.save(abVacationLeaveDays);
        AbVacationLeaveDaysDTO result = abVacationLeaveDaysMapper.toDto(abVacationLeaveDays);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                result.getCreatedBy(),
                "absence",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                abVacationLeaveDays.getIdEmployee().getId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        return ResponseEntity.created(new URI("/api/ab-vacation-leave-days/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-vacation-leave-days : Updates an existing abVacationLeaveDays.
     *
     * @param abVacationLeaveDaysDTO the abVacationLeaveDaysDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abVacationLeaveDaysDTO,
     * or with status 400 (Bad Request) if the abVacationLeaveDaysDTO is not valid,
     * or with status 500 (Internal Server Error) if the abVacationLeaveDaysDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-vacation-leave-days")
    @Timed
    public ResponseEntity<AbVacationLeaveDaysDTO> updateAbVacationLeaveDays(@RequestBody AbVacationLeaveDaysDTO abVacationLeaveDaysDTO) throws URISyntaxException {
        log.debug("REST request to update AbVacationLeaveDays : {}", abVacationLeaveDaysDTO);
        if (abVacationLeaveDaysDTO.getId() == null) {
            return createAbVacationLeaveDays(abVacationLeaveDaysDTO);
        }
        if (abVacationLeaveDaysDTO.getNumberOfDays() < 0){
            throw new BadRequestAlertException("Cannot set number of days to a negative value", ENTITY_NAME, "negativeDays");
        }
        AbVacationLeaveDays beforeUpdateAbVacationLeaveDays = abVacationLeaveDaysRepository
            .findOne(abVacationLeaveDaysDTO.getId());

        AbVacationLeaveDays afterUpdateAbVacationLeaveDays = abVacationLeaveDaysMapper.toEntity(abVacationLeaveDaysDTO);
        afterUpdateAbVacationLeaveDays = abVacationLeaveDaysRepository.save(afterUpdateAbVacationLeaveDays);

        updateAbRequestsWithNewVacLeaveDay(afterUpdateAbVacationLeaveDays);

        AbVacationLeaveDaysDTO result = abVacationLeaveDaysMapper.toDto(afterUpdateAbVacationLeaveDays);

        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                result.getUpdatedBy(),
                "absence",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                afterUpdateAbVacationLeaveDays.getIdEmployee().getId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abVacationLeaveDaysDTO.getId().toString()))
            .body(result);
    }

    private void updateAbRequestsWithNewVacLeaveDay(AbVacationLeaveDays afterUpdateAbVacationLeaveDays) {
        int numOfLeaveDaysLeft = afterUpdateAbVacationLeaveDays.getNumberOfDays();


        long employeeId = afterUpdateAbVacationLeaveDays.getIdEmployee().getId();
        int vacationYear = afterUpdateAbVacationLeaveDays.getYear();

        List<AbRequests> abRequestsToUpdate = abRequestsRepository.findByIdEmployeeIdAndYear(employeeId, vacationYear);

        for (AbRequests req : abRequestsToUpdate) {
            numOfLeaveDaysLeft -= req.getNoOfDays();
        }

        // If there does not exist any abRequests then do not do anything
        if (abRequestsToUpdate.isEmpty()){
           return ;
        }

        numOfLeaveDaysLeft = numOfLeaveDaysLeft < 0 ? 0 : numOfLeaveDaysLeft;

        abRequestsRepository.updateAllAbRequestsWithNoOfDays(numOfLeaveDaysLeft, employeeId, vacationYear);
    }

    /**
     * GET  /ab-vacation-leave-days : get all the abVacationLeaveDays.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abVacationLeaveDays in body
     */
    @GetMapping("/ab-vacation-leave-days")
    @Timed
    public List<AbVacationLeaveDaysDTO> getAllAbVacationLeaveDays() {
        log.debug("REST request to get all AbVacationLeaveDays");
        List<AbVacationLeaveDays> abVacationLeaveDays = abVacationLeaveDaysRepository.findAll();
        return abVacationLeaveDaysMapper.toDto(abVacationLeaveDays);
    }

    /**
     * GET  /ab-vacation-leave-days/:id : get the "id" abVacationLeaveDays.
     *
     * @param id the id of the abVacationLeaveDaysDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abVacationLeaveDaysDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-vacation-leave-days/{id}")
    @Timed
    public ResponseEntity<AbVacationLeaveDaysDTO> getAbVacationLeaveDays(@PathVariable Long id) {
        log.debug("REST request to get AbVacationLeaveDays : {}", id);
        AbVacationLeaveDays abVacationLeaveDays = abVacationLeaveDaysRepository.findOne(id);
        AbVacationLeaveDaysDTO abVacationLeaveDaysDTO = abVacationLeaveDaysMapper.toDto(abVacationLeaveDays);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abVacationLeaveDaysDTO));
    }

        /**
     * GET  /ab-vacation-leave-days/employee/:employeeId : get the vacation leave days by employee.
     *
     * @param employeeId the employee id
  * @return the ResponseEntity with status 200 (OK) and with body the abVacationLeaveDaysDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-vacation-leave-days/employee/{employeeId}")
    @Timed
    public ResponseEntity<List<AbVacationLeaveDaysDTO>> getAbVacationLeaveDaysByEmployeeId(@PathVariable Long employeeId) {
        log.debug("REST request to get leave days by employee id : {}", employeeId);
        List<AbVacationLeaveDays> leaveDays = abVacationLeaveDaysRepository.findByIdEmployeeId(employeeId);
        List<AbVacationLeaveDaysDTO> ldDTO = abVacationLeaveDaysMapper.toDto(leaveDays);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ldDTO));
    }

//             /**
//      * GET  /ab-vacation-leave-days/remaining-days/:employeeId : get the vacation leave days remaining by employee.
//      *
//      * @param employeeId the employee id
//   * @return the ResponseEntity with status 200 (OK) and with body the abVacationLeaveDaysDTO, or with status 404 (Not Found)
//      */
//     @GetMapping("/ab-vacation-leave-days/remaining-days/{employeeId}")
//     @Timed
//     public ResponseEntity<List<AbVacationLeaveDaysDTO>> getRemainingVacationDaysForEmployee(@PathVariable Long employeeId, @PathVariable Integer year ) {
//         log.debug("REST request to get leave days by employee id : {}", employeeId);
//         List<AbVacationLeaveDays> leaveDays = abVacationLeaveDaysRepository.findByIdEmployeeId(employeeId);
//         List<AbVacationLeaveDays> requestedYearLeaveDays = leaveDays.stream().filter(p -> p.getYear() == year).collect(Collectors.toList());
//         List<AbRequests> current
//         List<AbVacationLeaveDaysDTO> ldDTO = abVacationLeaveDaysMapper.toDto(leaveDays);
//         return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ldDTO));
//     }

    /**
     * DELETE  /ab-vacation-leave-days/:id : delete the "id" abVacationLeaveDays.
     *
     * @param id the id of the abVacationLeaveDaysDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-vacation-leave-days/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbVacationLeaveDays(@PathVariable Long id) {
        log.debug("REST request to delete AbVacationLeaveDays : {}", id);
        AbVacationLeaveDays abVacationLeaveDays = abVacationLeaveDaysRepository.findOne(id);
        AbVacationLeaveDaysDTO abVacationLeaveDaysDTO = abVacationLeaveDaysMapper.toDto(abVacationLeaveDays);
        abVacationLeaveDaysRepository.delete(id);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                abVacationLeaveDaysDTO.getUpdatedBy(),
                "absence",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                abVacationLeaveDays.getIdEmployee().getId().toString(),
                "employee",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
