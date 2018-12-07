package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.AbPSChanges;
import ba.infostudio.com.domain.AbRequests;
import ba.infostudio.com.domain.AbStatuses;
import ba.infostudio.com.repository.AbPSChangesRepository;
import ba.infostudio.com.repository.AbStatusesRepository;
import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbRequestStatuses;

import ba.infostudio.com.repository.AbRequestStatusesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.AbRequestStatusesDTO;
import ba.infostudio.com.service.mapper.AbRequestStatusesMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AbRequestStatuses.
 */
@RestController
@RequestMapping("/api")
public class AbRequestStatusesResource {

    private final Logger log = LoggerFactory.getLogger(AbRequestStatusesResource.class);

    private static final String ENTITY_NAME = "abRequestStatuses";

    private final AbRequestStatusesRepository abRequestStatusesRepository;

    private final AbStatusesRepository abStatusesRepository;

    private final AbRequestStatusesMapper abRequestStatusesMapper;

    private final AbPSChangesRepository abPSChangesRepository;

    public AbRequestStatusesResource(AbRequestStatusesRepository abRequestStatusesRepository,
                                     AbRequestStatusesMapper abRequestStatusesMapper,
                                     AbStatusesRepository abStatusesRepository,
                                     AbPSChangesRepository abPSChangesRepository) {
        this.abRequestStatusesRepository = abRequestStatusesRepository;
        this.abRequestStatusesMapper = abRequestStatusesMapper;
        this.abStatusesRepository = abStatusesRepository;
        this.abPSChangesRepository = abPSChangesRepository;
    }

    /**
     * POST  /ab-request-statuses : Create a new abRequestStatuses.
     *
     * @param abRequestStatusesDTO the abRequestStatusesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abRequestStatusesDTO, or with status 400 (Bad Request) if the abRequestStatuses has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-request-statuses")
    @Timed
    public ResponseEntity<AbRequestStatusesDTO> createAbRequestStatuses(@RequestBody AbRequestStatusesDTO abRequestStatusesDTO) throws URISyntaxException {
        log.debug("REST request to save AbRequestStatuses : {}", abRequestStatusesDTO);
        if (abRequestStatusesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abRequestStatuses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        checkIfStatusChangePermitted(abRequestStatusesDTO);

        AbRequestStatuses abRequestStatuses = abRequestStatusesMapper.toEntity(abRequestStatusesDTO);
        abRequestStatuses = abRequestStatusesRepository.save(abRequestStatuses);

        String nameOfStatus = abStatusesRepository.findOne(abRequestStatuses.getIdStatus().getId()).getName();
        if(nameOfStatus.equals("Calculated")){
            addFinishedStatus(abRequestStatuses);
        }

        AbRequestStatusesDTO result = abRequestStatusesMapper.toDto(abRequestStatuses);
        return ResponseEntity.created(new URI("/api/ab-request-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    private void checkIfStatusChangePermitted(AbRequestStatusesDTO abRequestStatusesDTO) {
        List<AbPSChanges> abPSChanges = this.abPSChangesRepository.findAll();
        if(abPSChanges.isEmpty()){
            throw new BadRequestAlertException("You first have to update the AbPermittedStatusChanges Table",
                ENTITY_NAME, "nopermittedstatuses");
        }

        AbStatuses toStatus = abStatusesRepository.findOne(abRequestStatusesDTO.getIdStatusId());

        Long abRequestId = abRequestStatusesDTO.getIdRequestId();
        AbRequestStatuses fromRequestStatus = abRequestStatusesRepository.findByIdRequestId(abRequestId)
            .stream().max(Comparator.comparing(AbRequestStatuses::getcreatedAt)).get();

        AbStatuses fromStatus = abStatusesRepository.findOne(fromRequestStatus.getIdStatus().getId());

        boolean canChange = false;

        // check if status change is permitted i.e. a record in the AbPSChanges entity exists with that status change
        for (AbPSChanges permittedStatusChange : abPSChanges) {
            String fromPermitted = permittedStatusChange.getIdStatusFrom().getName();
            String toPermitted = permittedStatusChange.getIdStatusTo().getName();

            if(fromStatus.getName().equals(fromPermitted) &&
               toStatus.getName().equals(toPermitted)){
                canChange = true;
            }
        }
        if(!canChange){
            String message = String.format("You cannot change the status from '%s' to '%s'", fromStatus.getName(),
                toStatus.getName());
            throw new BadRequestAlertException(message, ENTITY_NAME, "statuschangenotpermitted");
        }

    }

    private void addFinishedStatus(AbRequestStatuses abRequestStatuses) {
        AbRequestStatuses finishedAbRequestStatus = new AbRequestStatuses();

        finishedAbRequestStatus.setDateFrom(abRequestStatuses.getDateFrom());

        AbStatuses finishedStatus = this.abStatusesRepository.findByName("Finished");

        if(finishedStatus == null){
            log.error("There is no AbStatus with name 'Finished' in the Database");
            return ;
        }

        finishedAbRequestStatus.setIdStatus(finishedStatus);
        finishedAbRequestStatus.setIdRequest(abRequestStatuses.getIdRequest());

        abRequestStatusesRepository.save(finishedAbRequestStatus);
    }

    /**
     * PUT  /ab-request-statuses : Updates an existing abRequestStatuses.
     *
     * @param abRequestStatusesDTO the abRequestStatusesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abRequestStatusesDTO,
     * or with status 400 (Bad Request) if the abRequestStatusesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abRequestStatusesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-request-statuses")
    @Timed
    public ResponseEntity<AbRequestStatusesDTO> updateAbRequestStatuses(@RequestBody AbRequestStatusesDTO abRequestStatusesDTO) throws URISyntaxException {
        log.debug("REST request to update AbRequestStatuses : {}", abRequestStatusesDTO);
        if (abRequestStatusesDTO.getId() == null) {
            return createAbRequestStatuses(abRequestStatusesDTO);
        }
        AbRequestStatuses abRequestStatuses = abRequestStatusesMapper.toEntity(abRequestStatusesDTO);
        abRequestStatuses = abRequestStatusesRepository.save(abRequestStatuses);
        AbRequestStatusesDTO result = abRequestStatusesMapper.toDto(abRequestStatuses);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abRequestStatusesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-request-statuses : get all the abRequestStatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of abRequestStatuses in body
     */
    @GetMapping("/ab-request-statuses")
    @Timed
    public ResponseEntity<List<AbRequestStatusesDTO>> getAllAbRequestStatuses(Pageable pageable) {
        log.debug("REST request to get a page of AbRequestStatuses");
        Page<AbRequestStatuses> page = abRequestStatusesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ab-request-statuses");
        return new ResponseEntity<>(abRequestStatusesMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /ab-request-statuses/:id : get the "id" abRequestStatuses.
     *
     * @param id the id of the abRequestStatusesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abRequestStatusesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-request-statuses/{id}")
    @Timed
    public ResponseEntity<AbRequestStatusesDTO> getAbRequestStatuses(@PathVariable Long id) {
        log.debug("REST request to get AbRequestStatuses : {}", id);
        AbRequestStatuses abRequestStatuses = abRequestStatusesRepository.findOne(id);
        AbRequestStatusesDTO abRequestStatusesDTO = abRequestStatusesMapper.toDto(abRequestStatuses);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestStatusesDTO));
    }

    /**
     * GET  /ab-request-statuses/request/:id : get all the abRequestStatuses by request
     *
     * @param id of request
     * @return the ResponseEntity with status 200 (OK) and the list of abRequestStatuses in body
     */
    @GetMapping("/ab-request-statuses/request/{id}")
    @Timed
    public ResponseEntity<List<AbRequestStatusesDTO>> getAllAbRequestStatusesByRequest(@PathVariable Long id) {

        List<AbRequestStatuses> statuses = abRequestStatusesRepository.findByIdRequestId(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestStatusesMapper.toDto(statuses)));
    }

    /**
     * DELETE  /ab-request-statuses/:id : delete the "id" abRequestStatuses.
     *
     * @param id the id of the abRequestStatusesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-request-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbRequestStatuses(@PathVariable Long id) {
        log.debug("REST request to delete AbRequestStatuses : {}", id);
        abRequestStatusesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
