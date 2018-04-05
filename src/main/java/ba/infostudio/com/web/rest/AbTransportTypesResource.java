package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbTransportTypes;

import ba.infostudio.com.repository.AbTransportTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbTransportTypesDTO;
import ba.infostudio.com.service.mapper.AbTransportTypesMapper;
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
 * REST controller for managing AbTransportTypes.
 */
@RestController
@RequestMapping("/api")
public class AbTransportTypesResource {

    private final Logger log = LoggerFactory.getLogger(AbTransportTypesResource.class);

    private static final String ENTITY_NAME = "abTransportTypes";

    private final AbTransportTypesRepository abTransportTypesRepository;

    private final AbTransportTypesMapper abTransportTypesMapper;

    public AbTransportTypesResource(AbTransportTypesRepository abTransportTypesRepository, AbTransportTypesMapper abTransportTypesMapper) {
        this.abTransportTypesRepository = abTransportTypesRepository;
        this.abTransportTypesMapper = abTransportTypesMapper;
    }

    /**
     * POST  /ab-transport-types : Create a new abTransportTypes.
     *
     * @param abTransportTypesDTO the abTransportTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abTransportTypesDTO, or with status 400 (Bad Request) if the abTransportTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-transport-types")
    @Timed
    public ResponseEntity<AbTransportTypesDTO> createAbTransportTypes(@Valid @RequestBody AbTransportTypesDTO abTransportTypesDTO) throws URISyntaxException {
        log.debug("REST request to save AbTransportTypes : {}", abTransportTypesDTO);
        if (abTransportTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abTransportTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbTransportTypes abTransportTypes = abTransportTypesMapper.toEntity(abTransportTypesDTO);
        abTransportTypes = abTransportTypesRepository.save(abTransportTypes);
        AbTransportTypesDTO result = abTransportTypesMapper.toDto(abTransportTypes);
        return ResponseEntity.created(new URI("/api/ab-transport-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-transport-types : Updates an existing abTransportTypes.
     *
     * @param abTransportTypesDTO the abTransportTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abTransportTypesDTO,
     * or with status 400 (Bad Request) if the abTransportTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abTransportTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-transport-types")
    @Timed
    public ResponseEntity<AbTransportTypesDTO> updateAbTransportTypes(@Valid @RequestBody AbTransportTypesDTO abTransportTypesDTO) throws URISyntaxException {
        log.debug("REST request to update AbTransportTypes : {}", abTransportTypesDTO);
        if (abTransportTypesDTO.getId() == null) {
            return createAbTransportTypes(abTransportTypesDTO);
        }
        AbTransportTypes abTransportTypes = abTransportTypesMapper.toEntity(abTransportTypesDTO);
        abTransportTypes = abTransportTypesRepository.save(abTransportTypes);
        AbTransportTypesDTO result = abTransportTypesMapper.toDto(abTransportTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abTransportTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-transport-types : get all the abTransportTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abTransportTypes in body
     */
    @GetMapping("/ab-transport-types")
    @Timed
    public List<AbTransportTypesDTO> getAllAbTransportTypes() {
        log.debug("REST request to get all AbTransportTypes");
        List<AbTransportTypes> abTransportTypes = abTransportTypesRepository.findAll();
        return abTransportTypesMapper.toDto(abTransportTypes);
        }

    /**
     * GET  /ab-transport-types/:id : get the "id" abTransportTypes.
     *
     * @param id the id of the abTransportTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abTransportTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-transport-types/{id}")
    @Timed
    public ResponseEntity<AbTransportTypesDTO> getAbTransportTypes(@PathVariable Long id) {
        log.debug("REST request to get AbTransportTypes : {}", id);
        AbTransportTypes abTransportTypes = abTransportTypesRepository.findOne(id);
        AbTransportTypesDTO abTransportTypesDTO = abTransportTypesMapper.toDto(abTransportTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abTransportTypesDTO));
    }

    /**
     * DELETE  /ab-transport-types/:id : delete the "id" abTransportTypes.
     *
     * @param id the id of the abTransportTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-transport-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbTransportTypes(@PathVariable Long id) {
        log.debug("REST request to delete AbTransportTypes : {}", id);
        abTransportTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
