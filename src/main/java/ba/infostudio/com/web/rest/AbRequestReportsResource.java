package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.AbRequests;
import ba.infostudio.com.domain.Action;
import ba.infostudio.com.repository.AbRequestsRepository;
import ba.infostudio.com.web.rest.util.AuditUtil;
import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbRequestReports;

import ba.infostudio.com.repository.AbRequestReportsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.AbRequestReportsDTO;
import ba.infostudio.com.service.mapper.AbRequestReportsMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AbRequestReports.
 */
@RestController
@RequestMapping("/api")
public class AbRequestReportsResource {

    private final Logger log = LoggerFactory.getLogger(AbRequestReportsResource.class);

    private static final String ENTITY_NAME = "abRequestReports";

    private final AbRequestReportsRepository abRequestReportsRepository;

    private final AbRequestReportsMapper abRequestReportsMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final AbRequestsRepository abRequestsRepository;

    public AbRequestReportsResource(AbRequestReportsRepository abRequestReportsRepository,
                                    AbRequestReportsMapper abRequestReportsMapper,
                                    ApplicationEventPublisher applicationEventPublisher,
                                    AbRequestsRepository abRequestsRepository) {
        this.abRequestReportsRepository = abRequestReportsRepository;
        this.abRequestReportsMapper = abRequestReportsMapper;
        this.applicationEventPublisher = applicationEventPublisher;
        this.abRequestsRepository = abRequestsRepository;
    }

    /**
     * POST  /ab-request-reports : Create a new abRequestReports.
     *
     * @param abRequestReportsDTO the abRequestReportsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abRequestReportsDTO, or with status 400 (Bad Request) if the abRequestReports has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-request-reports")
    @Timed
    public ResponseEntity<AbRequestReportsDTO> createAbRequestReports(@RequestBody AbRequestReportsDTO abRequestReportsDTO) throws URISyntaxException {
        log.debug("REST request to save AbRequestReports : {}", abRequestReportsDTO);
        if (abRequestReportsDTO.getId() != null) {
            throw new BadRequestAlertException("A new abRequestReports cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbRequestReports abRequestReports = abRequestReportsMapper.toEntity(abRequestReportsDTO);
        abRequestReports = abRequestReportsRepository.save(abRequestReports);
        AbRequestReportsDTO result = abRequestReportsMapper.toDto(abRequestReports);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                result.getCreatedBy(),
                "absence",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        AbRequests abRequests = abRequestsRepository.findOne(abRequestReports.getIdRequest().getId());
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                abRequests.getIdEmployee().getId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.POST
            )
        );
        return ResponseEntity.created(new URI("/api/ab-request-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-request-reports : Updates an existing abRequestReports.
     *
     * @param abRequestReportsDTO the abRequestReportsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abRequestReportsDTO,
     * or with status 400 (Bad Request) if the abRequestReportsDTO is not valid,
     * or with status 500 (Internal Server Error) if the abRequestReportsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-request-reports")
    @Timed
    public ResponseEntity<AbRequestReportsDTO> updateAbRequestReports(@RequestBody AbRequestReportsDTO abRequestReportsDTO) throws URISyntaxException {
        log.debug("REST request to update AbRequestReports : {}", abRequestReportsDTO);
        if (abRequestReportsDTO.getId() == null) {
            return createAbRequestReports(abRequestReportsDTO);
        }
        AbRequestReports abRequestReports = abRequestReportsMapper.toEntity(abRequestReportsDTO);
        abRequestReports = abRequestReportsRepository.save(abRequestReports);
        AbRequestReportsDTO result = abRequestReportsMapper.toDto(abRequestReports);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                result.getUpdatedBy(),
                "absence",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        AbRequests abRequests = abRequestsRepository.findOne(abRequestReports.getIdRequest().getId());
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                abRequests.getIdEmployee().getId().toString(),
                "employee",
                ENTITY_NAME,
                result.getId().toString(),
                Action.PUT
            )
        );
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abRequestReportsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-request-reports : get all the abRequestReports.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of abRequestReports in body
     */
    @GetMapping("/ab-request-reports")
    @Timed
    public ResponseEntity<List<AbRequestReportsDTO>> getAllAbRequestReports(Pageable pageable) {
        log.debug("REST request to get a page of AbRequestReports");
        Page<AbRequestReports> page = abRequestReportsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ab-request-reports");
        return new ResponseEntity<>(abRequestReportsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

        /**
     * GET  /ab-request-reports/request/:id : get all the absence reports by request
     *
     * @param id of request
     * @return the ResponseEntity with status 200 (OK) and the list of abRequestReport in body
     */
    @GetMapping("/ab-request-reports/request/{id}")
    @Timed
    public ResponseEntity<List<AbRequestReportsDTO>> getAllReportsByRequestId(@PathVariable Long id) {
        List<AbRequestReports> reports = abRequestReportsRepository.findByIdRequestId(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestReportsMapper.toDto(reports)));
    }

    /**
     * GET  /ab-request-reports/:id : get the "id" abRequestReports.
     *
     * @param id the id of the abRequestReportsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abRequestReportsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-request-reports/{id}")
    @Timed
    public ResponseEntity<AbRequestReportsDTO> getAbRequestReports(@PathVariable Long id) {
        log.debug("REST request to get AbRequestReports : {}", id);
        AbRequestReports abRequestReports = abRequestReportsRepository.findOne(id);
        AbRequestReportsDTO abRequestReportsDTO = abRequestReportsMapper.toDto(abRequestReports);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestReportsDTO));
    }

    /**
     * DELETE  /ab-request-reports/:id : delete the "id" abRequestReports.
     *
     * @param id the id of the abRequestReportsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-request-reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbRequestReports(@PathVariable Long id) {
        log.debug("REST request to delete AbRequestReports : {}", id);
        AbRequestReports report = abRequestReportsRepository.findOne(id);
        AbRequestReportsDTO reportsDTO = abRequestReportsMapper.toDto(report);
        abRequestReportsRepository.delete(id);
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                reportsDTO.getUpdatedBy(),
                "absence",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        AbRequests abRequests = abRequestsRepository.findOne(report.getIdRequest().getId());
        applicationEventPublisher.publishEvent(
            AuditUtil.createAuditEvent(
                abRequests.getIdEmployee().getId().toString(),
                "employee",
                ENTITY_NAME,
                id.toString(),
                Action.DELETE
            )
        );
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
