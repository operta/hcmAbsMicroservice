package ba.infostudio.com.web.rest;

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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AbRequests.
 */
@RestController
@RequestMapping("/api")
public class AbRequestsResource {

    private final Logger log = LoggerFactory.getLogger(AbRequestsResource.class);

    private static final String ENTITY_NAME = "abRequests";

    private final AbRequestsRepository abRequestsRepository;

    private final AbRequestsMapper abRequestsMapper;

    public AbRequestsResource(AbRequestsRepository abRequestsRepository, AbRequestsMapper abRequestsMapper) {
        this.abRequestsRepository = abRequestsRepository;
        this.abRequestsMapper = abRequestsMapper;
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
        AbRequests abRequests = abRequestsMapper.toEntity(abRequestsDTO);
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
        AbRequests abRequests = abRequestsMapper.toEntity(abRequestsDTO);
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
