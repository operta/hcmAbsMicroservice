package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.EmStatuses;

import ba.infostudio.com.repository.EmStatusesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.EmStatusesDTO;
import ba.infostudio.com.service.mapper.EmStatusesMapper;
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
 * REST controller for managing EmStatuses.
 */
@RestController
@RequestMapping("/api")
public class EmStatusesResource {

    private final Logger log = LoggerFactory.getLogger(EmStatusesResource.class);

    private static final String ENTITY_NAME = "emStatuses";

    private final EmStatusesRepository emStatusesRepository;

    private final EmStatusesMapper emStatusesMapper;

    public EmStatusesResource(EmStatusesRepository emStatusesRepository, EmStatusesMapper emStatusesMapper) {
        this.emStatusesRepository = emStatusesRepository;
        this.emStatusesMapper = emStatusesMapper;
    }

    /**
     * POST  /em-statuses : Create a new emStatuses.
     *
     * @param emStatusesDTO the emStatusesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emStatusesDTO, or with status 400 (Bad Request) if the emStatuses has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/em-statuses")
    @Timed
    public ResponseEntity<EmStatusesDTO> createEmStatuses(@Valid @RequestBody EmStatusesDTO emStatusesDTO) throws URISyntaxException {
        log.debug("REST request to save EmStatuses : {}", emStatusesDTO);
        if (emStatusesDTO.getId() != null) {
            throw new BadRequestAlertException("A new emStatuses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmStatuses emStatuses = emStatusesMapper.toEntity(emStatusesDTO);
        emStatuses = emStatusesRepository.save(emStatuses);
        EmStatusesDTO result = emStatusesMapper.toDto(emStatuses);
        return ResponseEntity.created(new URI("/api/em-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /em-statuses : Updates an existing emStatuses.
     *
     * @param emStatusesDTO the emStatusesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emStatusesDTO,
     * or with status 400 (Bad Request) if the emStatusesDTO is not valid,
     * or with status 500 (Internal Server Error) if the emStatusesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/em-statuses")
    @Timed
    public ResponseEntity<EmStatusesDTO> updateEmStatuses(@Valid @RequestBody EmStatusesDTO emStatusesDTO) throws URISyntaxException {
        log.debug("REST request to update EmStatuses : {}", emStatusesDTO);
        if (emStatusesDTO.getId() == null) {
            return createEmStatuses(emStatusesDTO);
        }
        EmStatuses emStatuses = emStatusesMapper.toEntity(emStatusesDTO);
        emStatuses = emStatusesRepository.save(emStatuses);
        EmStatusesDTO result = emStatusesMapper.toDto(emStatuses);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emStatusesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /em-statuses : get all the emStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emStatuses in body
     */
    @GetMapping("/em-statuses")
    @Timed
    public List<EmStatusesDTO> getAllEmStatuses() {
        log.debug("REST request to get all EmStatuses");
        List<EmStatuses> emStatuses = emStatusesRepository.findAll();
        return emStatusesMapper.toDto(emStatuses);
        }

    /**
     * GET  /em-statuses/:id : get the "id" emStatuses.
     *
     * @param id the id of the emStatusesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emStatusesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/em-statuses/{id}")
    @Timed
    public ResponseEntity<EmStatusesDTO> getEmStatuses(@PathVariable Long id) {
        log.debug("REST request to get EmStatuses : {}", id);
        EmStatuses emStatuses = emStatusesRepository.findOne(id);
        EmStatusesDTO emStatusesDTO = emStatusesMapper.toDto(emStatuses);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emStatusesDTO));
    }

    /**
     * DELETE  /em-statuses/:id : delete the "id" emStatuses.
     *
     * @param id the id of the emStatusesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/em-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmStatuses(@PathVariable Long id) {
        log.debug("REST request to delete EmStatuses : {}", id);
        emStatusesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
