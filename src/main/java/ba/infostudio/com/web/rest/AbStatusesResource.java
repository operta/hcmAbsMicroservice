package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbStatuses;

import ba.infostudio.com.repository.AbStatusesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbStatusesDTO;
import ba.infostudio.com.service.mapper.AbStatusesMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AbStatuses.
 */
@RestController
@RequestMapping("/api")
public class AbStatusesResource {

    private final Logger log = LoggerFactory.getLogger(AbStatusesResource.class);

    private static final String ENTITY_NAME = "abStatuses";

    private final AbStatusesRepository abStatusesRepository;

    private final AbStatusesMapper abStatusesMapper;

    public AbStatusesResource(AbStatusesRepository abStatusesRepository, AbStatusesMapper abStatusesMapper) {
        this.abStatusesRepository = abStatusesRepository;
        this.abStatusesMapper = abStatusesMapper;
    }

    /**
     * POST  /ab-statuses : Create a new abStatuses.
     *
     * @param abStatusesDTO the abStatusesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abStatusesDTO, or with status 400 (Bad Request) if the abStatuses has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-statuses")
    @Timed
    public ResponseEntity<AbStatusesDTO> createAbStatuses(@Valid @RequestBody AbStatusesDTO abStatusesDTO) throws URISyntaxException {
        log.debug("REST request to save AbStatuses : {}", abStatusesDTO);
        if (abStatusesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abStatuses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbStatuses abStatuses = abStatusesMapper.toEntity(abStatusesDTO);
        abStatuses = abStatusesRepository.save(abStatuses);
        AbStatusesDTO result = abStatusesMapper.toDto(abStatuses);
        return ResponseEntity.created(new URI("/api/ab-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-statuses : Updates an existing abStatuses.
     *
     * @param abStatusesDTO the abStatusesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abStatusesDTO,
     * or with status 400 (Bad Request) if the abStatusesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abStatusesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-statuses")
    @Timed
    public ResponseEntity<AbStatusesDTO> updateAbStatuses(@Valid @RequestBody AbStatusesDTO abStatusesDTO) throws URISyntaxException {
        log.debug("REST request to update AbStatuses : {}", abStatusesDTO);
        if (abStatusesDTO.getId() == null) {
            return createAbStatuses(abStatusesDTO);
        }
        AbStatuses abStatuses = abStatusesMapper.toEntity(abStatusesDTO);
        abStatuses = abStatusesRepository.save(abStatuses);
        AbStatusesDTO result = abStatusesMapper.toDto(abStatuses);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abStatusesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-statuses : get all the abStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abStatuses in body
     */
    @GetMapping("/ab-statuses")
    @Timed
    public List<AbStatusesDTO> getAllAbStatuses() {
        log.debug("REST request to get all AbStatuses");
        List<AbStatuses> abStatuses = abStatusesRepository.findAll();
        return abStatusesMapper.toDto(abStatuses);
        }

    /**
     * GET  /ab-statuses/:id : get the "id" abStatuses.
     *
     * @param id the id of the abStatusesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abStatusesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-statuses/{id}")
    @Timed
    public ResponseEntity<AbStatusesDTO> getAbStatuses(@PathVariable Long id) {
        log.debug("REST request to get AbStatuses : {}", id);
        AbStatuses abStatuses = abStatusesRepository.findOne(id);
        AbStatusesDTO abStatusesDTO = abStatusesMapper.toDto(abStatuses);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abStatusesDTO));
    }

    /**
     * DELETE  /ab-statuses/:id : delete the "id" abStatuses.
     *
     * @param id the id of the abStatusesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbStatuses(@PathVariable Long id) {
        log.debug("REST request to delete AbStatuses : {}", id);
        abStatusesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
