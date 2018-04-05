package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbPSChanges;

import ba.infostudio.com.repository.AbPSChangesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbPSChangesDTO;
import ba.infostudio.com.service.mapper.AbPSChangesMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AbPSChanges.
 */
@RestController
@RequestMapping("/api")
public class AbPSChangesResource {

    private final Logger log = LoggerFactory.getLogger(AbPSChangesResource.class);

    private static final String ENTITY_NAME = "abPSChanges";

    private final AbPSChangesRepository abPSChangesRepository;

    private final AbPSChangesMapper abPSChangesMapper;

    public AbPSChangesResource(AbPSChangesRepository abPSChangesRepository, AbPSChangesMapper abPSChangesMapper) {
        this.abPSChangesRepository = abPSChangesRepository;
        this.abPSChangesMapper = abPSChangesMapper;
    }

    /**
     * POST  /ab-ps-changes : Create a new abPSChanges.
     *
     * @param abPSChangesDTO the abPSChangesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abPSChangesDTO, or with status 400 (Bad Request) if the abPSChanges has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-ps-changes")
    @Timed
    public ResponseEntity<AbPSChangesDTO> createAbPSChanges(@RequestBody AbPSChangesDTO abPSChangesDTO) throws URISyntaxException {
        log.debug("REST request to save AbPSChanges : {}", abPSChangesDTO);
        if (abPSChangesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abPSChanges cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbPSChanges abPSChanges = abPSChangesMapper.toEntity(abPSChangesDTO);
        abPSChanges = abPSChangesRepository.save(abPSChanges);
        AbPSChangesDTO result = abPSChangesMapper.toDto(abPSChanges);
        return ResponseEntity.created(new URI("/api/ab-ps-changes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-ps-changes : Updates an existing abPSChanges.
     *
     * @param abPSChangesDTO the abPSChangesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abPSChangesDTO,
     * or with status 400 (Bad Request) if the abPSChangesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abPSChangesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-ps-changes")
    @Timed
    public ResponseEntity<AbPSChangesDTO> updateAbPSChanges(@RequestBody AbPSChangesDTO abPSChangesDTO) throws URISyntaxException {
        log.debug("REST request to update AbPSChanges : {}", abPSChangesDTO);
        if (abPSChangesDTO.getId() == null) {
            return createAbPSChanges(abPSChangesDTO);
        }
        AbPSChanges abPSChanges = abPSChangesMapper.toEntity(abPSChangesDTO);
        abPSChanges = abPSChangesRepository.save(abPSChanges);
        AbPSChangesDTO result = abPSChangesMapper.toDto(abPSChanges);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abPSChangesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-ps-changes : get all the abPSChanges.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abPSChanges in body
     */
    @GetMapping("/ab-ps-changes")
    @Timed
    public List<AbPSChangesDTO> getAllAbPSChanges() {
        log.debug("REST request to get all AbPSChanges");
        List<AbPSChanges> abPSChanges = abPSChangesRepository.findAll();
        return abPSChangesMapper.toDto(abPSChanges);
        }

    /**
     * GET  /ab-ps-changes/:id : get the "id" abPSChanges.
     *
     * @param id the id of the abPSChangesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abPSChangesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-ps-changes/{id}")
    @Timed
    public ResponseEntity<AbPSChangesDTO> getAbPSChanges(@PathVariable Long id) {
        log.debug("REST request to get AbPSChanges : {}", id);
        AbPSChanges abPSChanges = abPSChangesRepository.findOne(id);
        AbPSChangesDTO abPSChangesDTO = abPSChangesMapper.toDto(abPSChanges);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abPSChangesDTO));
    }

    /**
     * DELETE  /ab-ps-changes/:id : delete the "id" abPSChanges.
     *
     * @param id the id of the abPSChangesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-ps-changes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbPSChanges(@PathVariable Long id) {
        log.debug("REST request to delete AbPSChanges : {}", id);
        abPSChangesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
