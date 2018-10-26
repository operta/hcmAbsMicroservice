package ba.infostudio.com.web.rest;

import org.apache.commons.lang.RandomStringUtils;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbAbsenceTypes;

import ba.infostudio.com.repository.AbAbsenceTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbAbsenceTypesDTO;
import ba.infostudio.com.service.mapper.AbAbsenceTypesMapper;
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
 * REST controller for managing AbAbsenceTypes.
 */
@RestController
@RequestMapping("/api")
public class AbAbsenceTypesResource {

    private final Logger log = LoggerFactory.getLogger(AbAbsenceTypesResource.class);

    private static final String ENTITY_NAME = "abAbsenceTypes";

    private final AbAbsenceTypesRepository abAbsenceTypesRepository;

    private final AbAbsenceTypesMapper abAbsenceTypesMapper;

    public AbAbsenceTypesResource(AbAbsenceTypesRepository abAbsenceTypesRepository, AbAbsenceTypesMapper abAbsenceTypesMapper) {
        this.abAbsenceTypesRepository = abAbsenceTypesRepository;
        this.abAbsenceTypesMapper = abAbsenceTypesMapper;
    }

    /**
     * POST  /ab-absence-types : Create a new abAbsenceTypes.
     *
     * @param abAbsenceTypesDTO the abAbsenceTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abAbsenceTypesDTO, or with status 400 (Bad Request) if the abAbsenceTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-absence-types")
    @Timed
    public ResponseEntity<AbAbsenceTypesDTO> createAbAbsenceTypes(@Valid @RequestBody AbAbsenceTypesDTO abAbsenceTypesDTO) throws URISyntaxException {
        log.debug("REST request to save AbAbsenceTypes : {}", abAbsenceTypesDTO);
        if (abAbsenceTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abAbsenceTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        String newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        while(abAbsenceTypesRepository.findByCode(newCode) != null){
            newCode = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        }
        abAbsenceTypesDTO.setCode(newCode);
        AbAbsenceTypes abAbsenceTypes = abAbsenceTypesMapper.toEntity(abAbsenceTypesDTO);
        abAbsenceTypes = abAbsenceTypesRepository.save(abAbsenceTypes);
        AbAbsenceTypesDTO result = abAbsenceTypesMapper.toDto(abAbsenceTypes);
        return ResponseEntity.created(new URI("/api/ab-absence-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-absence-types : Updates an existing abAbsenceTypes.
     *
     * @param abAbsenceTypesDTO the abAbsenceTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abAbsenceTypesDTO,
     * or with status 400 (Bad Request) if the abAbsenceTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abAbsenceTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-absence-types")
    @Timed
    public ResponseEntity<AbAbsenceTypesDTO> updateAbAbsenceTypes(@Valid @RequestBody AbAbsenceTypesDTO abAbsenceTypesDTO) throws URISyntaxException {
        log.debug("REST request to update AbAbsenceTypes : {}", abAbsenceTypesDTO);
        if (abAbsenceTypesDTO.getId() == null) {
            return createAbAbsenceTypes(abAbsenceTypesDTO);
        }
        AbAbsenceTypes abAbsenceTypes = abAbsenceTypesMapper.toEntity(abAbsenceTypesDTO);
        abAbsenceTypes = abAbsenceTypesRepository.save(abAbsenceTypes);
        AbAbsenceTypesDTO result = abAbsenceTypesMapper.toDto(abAbsenceTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abAbsenceTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-absence-types : get all the abAbsenceTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abAbsenceTypes in body
     */
    @GetMapping("/ab-absence-types")
    @Timed
    public List<AbAbsenceTypesDTO> getAllAbAbsenceTypes() {
        log.debug("REST request to get all AbAbsenceTypes");
        List<AbAbsenceTypes> abAbsenceTypes = abAbsenceTypesRepository.findAll();
        return abAbsenceTypesMapper.toDto(abAbsenceTypes);
        }

    /**
     * GET  /ab-absence-types/:id : get the "id" abAbsenceTypes.
     *
     * @param id the id of the abAbsenceTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abAbsenceTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-absence-types/{id}")
    @Timed
    public ResponseEntity<AbAbsenceTypesDTO> getAbAbsenceTypes(@PathVariable Long id) {
        log.debug("REST request to get AbAbsenceTypes : {}", id);
        AbAbsenceTypes abAbsenceTypes = abAbsenceTypesRepository.findOne(id);
        AbAbsenceTypesDTO abAbsenceTypesDTO = abAbsenceTypesMapper.toDto(abAbsenceTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abAbsenceTypesDTO));
    }

    /**
     * DELETE  /ab-absence-types/:id : delete the "id" abAbsenceTypes.
     *
     * @param id the id of the abAbsenceTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-absence-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbAbsenceTypes(@PathVariable Long id) {
        log.debug("REST request to delete AbAbsenceTypes : {}", id);
        abAbsenceTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
