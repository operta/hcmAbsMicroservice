package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbCostTypes;

import ba.infostudio.com.repository.AbCostTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbCostTypesDTO;
import ba.infostudio.com.service.mapper.AbCostTypesMapper;
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
 * REST controller for managing AbCostTypes.
 */
@RestController
@RequestMapping("/api")
public class AbCostTypesResource {

    private final Logger log = LoggerFactory.getLogger(AbCostTypesResource.class);

    private static final String ENTITY_NAME = "abCostTypes";

    private final AbCostTypesRepository abCostTypesRepository;

    private final AbCostTypesMapper abCostTypesMapper;

    public AbCostTypesResource(AbCostTypesRepository abCostTypesRepository, AbCostTypesMapper abCostTypesMapper) {
        this.abCostTypesRepository = abCostTypesRepository;
        this.abCostTypesMapper = abCostTypesMapper;
    }

    /**
     * POST  /ab-cost-types : Create a new abCostTypes.
     *
     * @param abCostTypesDTO the abCostTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abCostTypesDTO, or with status 400 (Bad Request) if the abCostTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-cost-types")
    @Timed
    public ResponseEntity<AbCostTypesDTO> createAbCostTypes(@Valid @RequestBody AbCostTypesDTO abCostTypesDTO) throws URISyntaxException {
        log.debug("REST request to save AbCostTypes : {}", abCostTypesDTO);
        if (abCostTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abCostTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbCostTypes abCostTypes = abCostTypesMapper.toEntity(abCostTypesDTO);
        abCostTypes = abCostTypesRepository.save(abCostTypes);
        AbCostTypesDTO result = abCostTypesMapper.toDto(abCostTypes);
        return ResponseEntity.created(new URI("/api/ab-cost-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-cost-types : Updates an existing abCostTypes.
     *
     * @param abCostTypesDTO the abCostTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abCostTypesDTO,
     * or with status 400 (Bad Request) if the abCostTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abCostTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-cost-types")
    @Timed
    public ResponseEntity<AbCostTypesDTO> updateAbCostTypes(@Valid @RequestBody AbCostTypesDTO abCostTypesDTO) throws URISyntaxException {
        log.debug("REST request to update AbCostTypes : {}", abCostTypesDTO);
        if (abCostTypesDTO.getId() == null) {
            return createAbCostTypes(abCostTypesDTO);
        }
        AbCostTypes abCostTypes = abCostTypesMapper.toEntity(abCostTypesDTO);
        abCostTypes = abCostTypesRepository.save(abCostTypes);
        AbCostTypesDTO result = abCostTypesMapper.toDto(abCostTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abCostTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-cost-types : get all the abCostTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abCostTypes in body
     */
    @GetMapping("/ab-cost-types")
    @Timed
    public List<AbCostTypesDTO> getAllAbCostTypes() {
        log.debug("REST request to get all AbCostTypes");
        List<AbCostTypes> abCostTypes = abCostTypesRepository.findAll();
        return abCostTypesMapper.toDto(abCostTypes);
        }

    /**
     * GET  /ab-cost-types/:id : get the "id" abCostTypes.
     *
     * @param id the id of the abCostTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abCostTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-cost-types/{id}")
    @Timed
    public ResponseEntity<AbCostTypesDTO> getAbCostTypes(@PathVariable Long id) {
        log.debug("REST request to get AbCostTypes : {}", id);
        AbCostTypes abCostTypes = abCostTypesRepository.findOne(id);
        AbCostTypesDTO abCostTypesDTO = abCostTypesMapper.toDto(abCostTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abCostTypesDTO));
    }

    /**
     * DELETE  /ab-cost-types/:id : delete the "id" abCostTypes.
     *
     * @param id the id of the abCostTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-cost-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbCostTypes(@PathVariable Long id) {
        log.debug("REST request to delete AbCostTypes : {}", id);
        abCostTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
