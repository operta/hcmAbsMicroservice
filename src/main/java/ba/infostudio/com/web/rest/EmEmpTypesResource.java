package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.EmEmpTypes;

import ba.infostudio.com.repository.EmEmpTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.EmEmpTypesDTO;
import ba.infostudio.com.service.mapper.EmEmpTypesMapper;
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
 * REST controller for managing EmEmpTypes.
 */
@RestController
@RequestMapping("/api")
public class EmEmpTypesResource {

    private final Logger log = LoggerFactory.getLogger(EmEmpTypesResource.class);

    private static final String ENTITY_NAME = "emEmpTypes";

    private final EmEmpTypesRepository emEmpTypesRepository;

    private final EmEmpTypesMapper emEmpTypesMapper;

    public EmEmpTypesResource(EmEmpTypesRepository emEmpTypesRepository, EmEmpTypesMapper emEmpTypesMapper) {
        this.emEmpTypesRepository = emEmpTypesRepository;
        this.emEmpTypesMapper = emEmpTypesMapper;
    }

    /**
     * POST  /em-emp-types : Create a new emEmpTypes.
     *
     * @param emEmpTypesDTO the emEmpTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emEmpTypesDTO, or with status 400 (Bad Request) if the emEmpTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/em-emp-types")
    @Timed
    public ResponseEntity<EmEmpTypesDTO> createEmEmpTypes(@Valid @RequestBody EmEmpTypesDTO emEmpTypesDTO) throws URISyntaxException {
        log.debug("REST request to save EmEmpTypes : {}", emEmpTypesDTO);
        if (emEmpTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new emEmpTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmEmpTypes emEmpTypes = emEmpTypesMapper.toEntity(emEmpTypesDTO);
        emEmpTypes = emEmpTypesRepository.save(emEmpTypes);
        EmEmpTypesDTO result = emEmpTypesMapper.toDto(emEmpTypes);
        return ResponseEntity.created(new URI("/api/em-emp-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /em-emp-types : Updates an existing emEmpTypes.
     *
     * @param emEmpTypesDTO the emEmpTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emEmpTypesDTO,
     * or with status 400 (Bad Request) if the emEmpTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the emEmpTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/em-emp-types")
    @Timed
    public ResponseEntity<EmEmpTypesDTO> updateEmEmpTypes(@Valid @RequestBody EmEmpTypesDTO emEmpTypesDTO) throws URISyntaxException {
        log.debug("REST request to update EmEmpTypes : {}", emEmpTypesDTO);
        if (emEmpTypesDTO.getId() == null) {
            return createEmEmpTypes(emEmpTypesDTO);
        }
        EmEmpTypes emEmpTypes = emEmpTypesMapper.toEntity(emEmpTypesDTO);
        emEmpTypes = emEmpTypesRepository.save(emEmpTypes);
        EmEmpTypesDTO result = emEmpTypesMapper.toDto(emEmpTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emEmpTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /em-emp-types : get all the emEmpTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emEmpTypes in body
     */
    @GetMapping("/em-emp-types")
    @Timed
    public List<EmEmpTypesDTO> getAllEmEmpTypes() {
        log.debug("REST request to get all EmEmpTypes");
        List<EmEmpTypes> emEmpTypes = emEmpTypesRepository.findAll();
        return emEmpTypesMapper.toDto(emEmpTypes);
        }

    /**
     * GET  /em-emp-types/:id : get the "id" emEmpTypes.
     *
     * @param id the id of the emEmpTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emEmpTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/em-emp-types/{id}")
    @Timed
    public ResponseEntity<EmEmpTypesDTO> getEmEmpTypes(@PathVariable Long id) {
        log.debug("REST request to get EmEmpTypes : {}", id);
        EmEmpTypes emEmpTypes = emEmpTypesRepository.findOne(id);
        EmEmpTypesDTO emEmpTypesDTO = emEmpTypesMapper.toDto(emEmpTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emEmpTypesDTO));
    }

    /**
     * DELETE  /em-emp-types/:id : delete the "id" emEmpTypes.
     *
     * @param id the id of the emEmpTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/em-emp-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmEmpTypes(@PathVariable Long id) {
        log.debug("REST request to delete EmEmpTypes : {}", id);
        emEmpTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
