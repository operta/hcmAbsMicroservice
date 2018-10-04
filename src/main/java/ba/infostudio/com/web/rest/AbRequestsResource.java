package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.AbRequestCosts;
import ba.infostudio.com.domain.AbStatuses;
import ba.infostudio.com.domain.AbVacationLeaveDays;
import ba.infostudio.com.repository.AbStatusesRepository;
import ba.infostudio.com.repository.AbVacationLeaveDaysRepository;
import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbRequests;

import ba.infostudio.com.repository.AbRequestsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.AbRequestsDTO;
import ba.infostudio.com.service.mapper.AbRequestsMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.util.*;

/**
 * REST controller for managing AbRequests.
 */
@RestController
@RequestMapping("/api")
public class AbRequestsResource {

    private final Logger log = LoggerFactory.getLogger(AbRequestsResource.class);

    private static final String ENTITY_NAME = "abRequests";

    private final AbRequestsRepository abRequestsRepository;

    private final AbVacationLeaveDaysRepository abVacationLeaveDaysRepository;

    private final AbStatusesRepository abStatusesRepository;

    private final AbRequestsMapper abRequestsMapper;

    public AbRequestsResource(AbRequestsRepository abRequestsRepository, AbRequestsMapper abRequestsMapper,
                              AbVacationLeaveDaysRepository abVacationLeaveDaysRepository,
                              AbStatusesRepository abStatusesRepository) {
        this.abRequestsRepository = abRequestsRepository;
        this.abRequestsMapper = abRequestsMapper;
        this.abVacationLeaveDaysRepository = abVacationLeaveDaysRepository;
        this.abStatusesRepository = abStatusesRepository;
    }

    /**
     * POST  /ab-requests : Create a new abRequests.
     *
     * @param abRequestsDTO the abRequestsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abRequestsDTO, or with status 400 (Bad Request) if the abRequests has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-requests")
    @Timed
    public ResponseEntity<AbRequestsDTO> createAbRequests(@Valid @RequestBody AbRequestsDTO abRequestsDTO) throws URISyntaxException {
        log.debug("REST request to save AbRequests : {}", abRequestsDTO);
        if (abRequestsDTO.getId() != null) {
            throw new BadRequestAlertException("A new abRequests cannot already have an ID", ENTITY_NAME, "idexists");
        }
        abRequestsDTO.setYear(LocalDate.now().getYear());
        AbRequests abRequests = abRequestsMapper.toEntity(abRequestsDTO);
        List<AbRequests> allAbRequestsForEmp = this.abRequestsRepository
            .findByIdEmployeeIdAndYear(abRequestsDTO.getIdEmployeeId(),
                abRequests.getYear());
        if(allAbRequestsForEmp == null || allAbRequestsForEmp.size() == 0){
            AbVacationLeaveDays abVacationLeaveDays = this.abVacationLeaveDaysRepository
                .findByIdEmployeeIdAndYear(abRequestsDTO.getIdEmployeeId(), abRequests.getYear());
            abRequests.setNoOfDaysLeft(abVacationLeaveDays.getNumberOfDays());
        }else{
            AbRequests lastRequest = allAbRequestsForEmp.stream()
                .max(Comparator.comparing(AbRequests::getupdatedAt))
                .orElse(null);
            abRequests.setNoOfDaysLeft(lastRequest.getNoOfDaysLeft());
        }


        abRequests = abRequestsRepository.save(abRequests);
        AbRequestsDTO result = abRequestsMapper.toDto(abRequests);
        return ResponseEntity.created(new URI("/api/ab-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-requests : Updates an existing abRequests.
     *
     * @param abRequestsDTO the abRequestsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abRequestsDTO,
     * or with status 400 (Bad Request) if the abRequestsDTO is not valid,
     * or with status 500 (Internal Server Error) if the abRequestsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-requests")
    @Timed
    public ResponseEntity<AbRequestsDTO> updateAbRequests(@Valid @RequestBody AbRequestsDTO abRequestsDTO) throws URISyntaxException {
        log.debug("REST request to update AbRequests : {}", abRequestsDTO);
        if (abRequestsDTO.getId() == null) {
            return createAbRequests(abRequestsDTO);
        }

        AbRequests oldAbRequest = abRequestsRepository.findOne(abRequestsDTO.getId());
        AbRequests abRequests = abRequestsMapper.toEntity(abRequestsDTO);
        abRequests.setNoOfDaysLeft(oldAbRequest.getNoOfDaysLeft());
        abRequests.setYear(oldAbRequest.getYear());

        AbStatuses abStatuses = abStatusesRepository.findOne(abRequestsDTO.getIdStatusId());
        if(!oldAbRequest.getIdStatus().getId().equals(abRequestsDTO.getIdStatusId())){
            if(abStatuses.getName().equals("Approved")) {
                List<AbRequests> allAbRequestsForEmp = this.abRequestsRepository
                    .findByIdEmployeeIdAndYear(abRequestsDTO.getIdEmployeeId(),
                        abRequestsDTO.getYear());
                allAbRequestsForEmp.remove(oldAbRequest);
                if (allAbRequestsForEmp.size() == 0) {
                    AbVacationLeaveDays abVacationLeaveDays = this.abVacationLeaveDaysRepository
                        .findByIdEmployeeIdAndYear(abRequestsDTO.getIdEmployeeId(), abRequests.getYear());
                    abRequests.setNoOfDaysLeft(abVacationLeaveDays.getNumberOfDays() - abRequestsDTO.getNoOfDays());
                } else {
                    AbRequests lastRequest = allAbRequestsForEmp.stream()
                        .max(Comparator.comparing(AbRequests::getcreatedAt))
                        .orElse(null);
                    abRequests.setNoOfDaysLeft(lastRequest.getNoOfDaysLeft() - abRequests.getNoOfDays());
                }
            }
        }
        abRequestsRepository.updateAllAbRequestsWithNoOfDays(abRequests.getNoOfDaysLeft(),
                                                             abRequestsDTO.getIdEmployeeId(),
                                                             abRequests.getYear());
        abRequests = abRequestsRepository.save(abRequests);
        AbRequestsDTO result = abRequestsMapper.toDto(abRequests);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abRequestsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-requests : get all the abRequests.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of abRequests in body
     */
    @GetMapping("/ab-requests")
    @Timed
    public ResponseEntity<List<AbRequestsDTO>> getAllAbRequests(Pageable pageable) {
        log.debug("REST request to get a page of AbRequests");
        Page<AbRequests> page = abRequestsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ab-requests");
        return new ResponseEntity<>(abRequestsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping("/ab-requests/search")
    public ResponseEntity<List<AbRequestsDTO>> getAbRequestsBySearchParams(
        @RequestParam(value = "dateFrom", required = false) LocalDate dateFrom,
        @RequestParam(value = "dateTo", required = false) LocalDate dateTo,
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value ="idAbsenceTypeId", required = false) Long idAbsenceTypeId,
        @RequestParam(value = "idStatusId", required = false) Long idStatusId,

        Pageable pageable)
    {


        Set<Integer> requestIds = new HashSet<Integer>(Arrays.asList(extractIds(abRequestsRepository.findAll())));

        if(dateFrom != null) {
            List<AbRequests> list  = abRequestsRepository.findByDateFromGreaterThanEqual(dateFrom);
            Set<Integer> s = new HashSet<Integer>(Arrays.asList(extractIds(list)));
            requestIds.retainAll(s);
        }
        if(dateTo != null) {
            List<AbRequests> list  = abRequestsRepository.findByDateToLessThanEqual(dateTo);
            Set<Integer> s = new HashSet<Integer>(Arrays.asList(extractIds(list)));
            requestIds.retainAll(s);
        }
        if(id != null) {
            List<AbRequests> list  = abRequestsRepository.findById(id);
            Set<Integer> s = new HashSet<Integer>(Arrays.asList(extractIds(list)));
            requestIds.retainAll(s);
        }
        if(idAbsenceTypeId != null) {
            List<AbRequests> list  = abRequestsRepository.findByIdAbsenceTypeId(idAbsenceTypeId);
            Set<Integer> s = new HashSet<Integer>(Arrays.asList(extractIds(list)));
            requestIds.retainAll(s);
        }
        if(idStatusId != null) {
            List<AbRequests> list  = abRequestsRepository.findByIdStatusId(idStatusId);
            Set<Integer> s = new HashSet<Integer>(Arrays.asList(extractIds(list)));
            requestIds.retainAll(s);
        }


        Integer[] result = requestIds.toArray(new Integer[requestIds.size()]);

        List<AbRequests> requests = new ArrayList<AbRequests>();
        for(int i = 0; i < result.length; i++) {
            AbRequests item = abRequestsRepository.findOne(result[i].longValue());
            requests.add(item);
        }

        Page<AbRequests> page = new PageImpl<AbRequests>(requests, pageable, requests.size());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ab-requests/search");
        return new ResponseEntity<>(abRequestsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    public static Integer[] extractIds(List<AbRequests> array) {
        if(array.size() == 0) {
            return new Integer[0];
        }
        Integer[] result = new Integer[array.size()];
        for(int i = 0; i < array.size(); i++) {
            result[i] = (array.get(i).getId().intValue());
        }
        return result;
    }


    /**
     * GET  /ab-requests/:id : get the "id" abRequests.
     *
     * @param id the id of the abRequestsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abRequestsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-requests/{id}")
    @Timed
    public ResponseEntity<AbRequestsDTO> getAbRequests(@PathVariable Long id) {
        log.debug("REST request to get AbRequests : {}", id);
        AbRequests abRequests = abRequestsRepository.findOne(id);
        AbRequestsDTO abRequestsDTO = abRequestsMapper.toDto(abRequests);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestsDTO));
    }


    @GetMapping("/ab-requests/number-of-days-left/employee/{employeeId}")
    @Timed
    public ResponseEntity<AbRequestsDTO> getNumberOfDaysLeftByEmpId(@PathVariable Long employeeId) {
        log.debug("REST request to get AbRequests : {}", employeeId);
        List<AbRequests> abRequests = abRequestsRepository.findByIdEmployeeIdAndYear(employeeId, LocalDate.now().getYear());
        AbRequests lastRequest;
        if(abRequests.size() > 0) {
             lastRequest = abRequests.stream()
                .max(Comparator.comparing(AbRequests::getupdatedAt))
                .orElse(null);
        }else{
            lastRequest = new AbRequests();
            AbVacationLeaveDays abVacationLeaveDays = this.abVacationLeaveDaysRepository
                .findByIdEmployeeIdAndYear(employeeId, LocalDate.now().getYear());
            lastRequest.setNoOfDaysLeft(abVacationLeaveDays.getNumberOfDays());
            lastRequest.setNoOfDays(0);
        }
        AbRequestsDTO abRequestsDTO = abRequestsMapper.toDto(lastRequest);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestsDTO));
    }
    /**
     * GET  /ab-requests/all : get the "id" abRequests.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the abRequestsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-requests/all")
    @Timed
    public ResponseEntity<List<AbRequestsDTO>> getAllAbsenceRequests() {

        List<AbRequests> abRequests = abRequestsRepository.findAll();
        List<AbRequestsDTO> abRequestsDTO = abRequestsMapper.toDto(abRequests);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestsDTO));
    }

    /**
     * GET  /ab-requests/employee/:employeeId : get the abRequests by employee.
     *
     * @param employeeId the employee id of the abRequestsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abRequestsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-requests/employee/{employeeId}")
    @Timed
    public ResponseEntity<List<AbRequestsDTO>> getAbRequestsByEmployee(@PathVariable Long employeeId) {
        log.debug("REST request to get AbRequests by employee id : {}", employeeId);
        List<AbRequests> abRequests = abRequestsRepository.findByIdEmployeeId(employeeId);
        List<AbRequestsDTO> abRequestsDTO = abRequestsMapper.toDto(abRequests);
        System.out.println(abRequestsDTO);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestsDTO));
    }


    /**
     * DELETE  /ab-requests/:id : delete the "id" abRequests.
     *
     * @param id the id of the abRequestsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-requests/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbRequests(@PathVariable Long id) {
        log.debug("REST request to delete AbRequests : {}", id);
        abRequestsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
