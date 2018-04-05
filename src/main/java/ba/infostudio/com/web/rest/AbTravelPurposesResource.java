package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbTravelPurposes;

import ba.infostudio.com.repository.AbTravelPurposesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbTravelPurposesDTO;
import ba.infostudio.com.service.mapper.AbTravelPurposesMapper;
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
 * REST controller for managing AbTravelPurposes.
 */
@RestController
@RequestMapping("/api")
public class AbTravelPurposesResource {

    private final Logger log = LoggerFactory.getLogger(AbTravelPurposesResource.class);

    private static final String ENTITY_NAME = "abTravelPurposes";

    private final AbTravelPurposesRepository abTravelPurposesRepository;

    private final AbTravelPurposesMapper abTravelPurposesMapper;

    public AbTravelPurposesResource(AbTravelPurposesRepository abTravelPurposesRepository, AbTravelPurposesMapper abTravelPurposesMapper) {
        this.abTravelPurposesRepository = abTravelPurposesRepository;
        this.abTravelPurposesMapper = abTravelPurposesMapper;
    }

    /**
     * POST  /ab-travel-purposes : Create a new abTravelPurposes.
     *
     * @param abTravelPurposesDTO the abTravelPurposesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abTravelPurposesDTO, or with status 400 (Bad Request) if the abTravelPurposes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-travel-purposes")
    @Timed
    public ResponseEntity<AbTravelPurposesDTO> createAbTravelPurposes(@Valid @RequestBody AbTravelPurposesDTO abTravelPurposesDTO) throws URISyntaxException {
        log.debug("REST request to save AbTravelPurposes : {}", abTravelPurposesDTO);
        if (abTravelPurposesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abTravelPurposes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbTravelPurposes abTravelPurposes = abTravelPurposesMapper.toEntity(abTravelPurposesDTO);
        abTravelPurposes = abTravelPurposesRepository.save(abTravelPurposes);
        AbTravelPurposesDTO result = abTravelPurposesMapper.toDto(abTravelPurposes);
        return ResponseEntity.created(new URI("/api/ab-travel-purposes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-travel-purposes : Updates an existing abTravelPurposes.
     *
     * @param abTravelPurposesDTO the abTravelPurposesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abTravelPurposesDTO,
     * or with status 400 (Bad Request) if the abTravelPurposesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abTravelPurposesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-travel-purposes")
    @Timed
    public ResponseEntity<AbTravelPurposesDTO> updateAbTravelPurposes(@Valid @RequestBody AbTravelPurposesDTO abTravelPurposesDTO) throws URISyntaxException {
        log.debug("REST request to update AbTravelPurposes : {}", abTravelPurposesDTO);
        if (abTravelPurposesDTO.getId() == null) {
            return createAbTravelPurposes(abTravelPurposesDTO);
        }
        AbTravelPurposes abTravelPurposes = abTravelPurposesMapper.toEntity(abTravelPurposesDTO);
        abTravelPurposes = abTravelPurposesRepository.save(abTravelPurposes);
        AbTravelPurposesDTO result = abTravelPurposesMapper.toDto(abTravelPurposes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abTravelPurposesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-travel-purposes : get all the abTravelPurposes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abTravelPurposes in body
     */
    @GetMapping("/ab-travel-purposes")
    @Timed
    public List<AbTravelPurposesDTO> getAllAbTravelPurposes() {
        log.debug("REST request to get all AbTravelPurposes");
        List<AbTravelPurposes> abTravelPurposes = abTravelPurposesRepository.findAll();
        return abTravelPurposesMapper.toDto(abTravelPurposes);
        }

    /**
     * GET  /ab-travel-purposes/:id : get the "id" abTravelPurposes.
     *
     * @param id the id of the abTravelPurposesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abTravelPurposesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-travel-purposes/{id}")
    @Timed
    public ResponseEntity<AbTravelPurposesDTO> getAbTravelPurposes(@PathVariable Long id) {
        log.debug("REST request to get AbTravelPurposes : {}", id);
        AbTravelPurposes abTravelPurposes = abTravelPurposesRepository.findOne(id);
        AbTravelPurposesDTO abTravelPurposesDTO = abTravelPurposesMapper.toDto(abTravelPurposes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abTravelPurposesDTO));
    }

    /**
     * DELETE  /ab-travel-purposes/:id : delete the "id" abTravelPurposes.
     *
     * @param id the id of the abTravelPurposesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-travel-purposes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbTravelPurposes(@PathVariable Long id) {
        log.debug("REST request to delete AbTravelPurposes : {}", id);
        abTravelPurposesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
