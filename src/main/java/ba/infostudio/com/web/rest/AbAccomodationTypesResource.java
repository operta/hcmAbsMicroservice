package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbAccomodationTypes;

import ba.infostudio.com.repository.AbAccomodationTypesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.AbAccomodationTypesDTO;
import ba.infostudio.com.service.mapper.AbAccomodationTypesMapper;
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
 * REST controller for managing AbAccomodationTypes.
 */
@RestController
@RequestMapping("/api")
public class AbAccomodationTypesResource {

    private final Logger log = LoggerFactory.getLogger(AbAccomodationTypesResource.class);

    private static final String ENTITY_NAME = "abAccomodationTypes";

    private final AbAccomodationTypesRepository abAccomodationTypesRepository;

    private final AbAccomodationTypesMapper abAccomodationTypesMapper;

    public AbAccomodationTypesResource(AbAccomodationTypesRepository abAccomodationTypesRepository, AbAccomodationTypesMapper abAccomodationTypesMapper) {
        this.abAccomodationTypesRepository = abAccomodationTypesRepository;
        this.abAccomodationTypesMapper = abAccomodationTypesMapper;
    }

    /**
     * POST  /ab-accomodation-types : Create a new abAccomodationTypes.
     *
     * @param abAccomodationTypesDTO the abAccomodationTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abAccomodationTypesDTO, or with status 400 (Bad Request) if the abAccomodationTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-accomodation-types")
    @Timed
    public ResponseEntity<AbAccomodationTypesDTO> createAbAccomodationTypes(@Valid @RequestBody AbAccomodationTypesDTO abAccomodationTypesDTO) throws URISyntaxException {
        log.debug("REST request to save AbAccomodationTypes : {}", abAccomodationTypesDTO);
        if (abAccomodationTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new abAccomodationTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbAccomodationTypes abAccomodationTypes = abAccomodationTypesMapper.toEntity(abAccomodationTypesDTO);
        abAccomodationTypes = abAccomodationTypesRepository.save(abAccomodationTypes);
        AbAccomodationTypesDTO result = abAccomodationTypesMapper.toDto(abAccomodationTypes);
        return ResponseEntity.created(new URI("/api/ab-accomodation-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-accomodation-types : Updates an existing abAccomodationTypes.
     *
     * @param abAccomodationTypesDTO the abAccomodationTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abAccomodationTypesDTO,
     * or with status 400 (Bad Request) if the abAccomodationTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the abAccomodationTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-accomodation-types")
    @Timed
    public ResponseEntity<AbAccomodationTypesDTO> updateAbAccomodationTypes(@Valid @RequestBody AbAccomodationTypesDTO abAccomodationTypesDTO) throws URISyntaxException {
        log.debug("REST request to update AbAccomodationTypes : {}", abAccomodationTypesDTO);
        if (abAccomodationTypesDTO.getId() == null) {
            return createAbAccomodationTypes(abAccomodationTypesDTO);
        }
        AbAccomodationTypes abAccomodationTypes = abAccomodationTypesMapper.toEntity(abAccomodationTypesDTO);
        abAccomodationTypes = abAccomodationTypesRepository.save(abAccomodationTypes);
        AbAccomodationTypesDTO result = abAccomodationTypesMapper.toDto(abAccomodationTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abAccomodationTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-accomodation-types : get all the abAccomodationTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abAccomodationTypes in body
     */
    @GetMapping("/ab-accomodation-types")
    @Timed
    public List<AbAccomodationTypesDTO> getAllAbAccomodationTypes() {
        log.debug("REST request to get all AbAccomodationTypes");
        List<AbAccomodationTypes> abAccomodationTypes = abAccomodationTypesRepository.findAll();
        return abAccomodationTypesMapper.toDto(abAccomodationTypes);
        }

    /**
     * GET  /ab-accomodation-types/:id : get the "id" abAccomodationTypes.
     *
     * @param id the id of the abAccomodationTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abAccomodationTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-accomodation-types/{id}")
    @Timed
    public ResponseEntity<AbAccomodationTypesDTO> getAbAccomodationTypes(@PathVariable Long id) {
        log.debug("REST request to get AbAccomodationTypes : {}", id);
        AbAccomodationTypes abAccomodationTypes = abAccomodationTypesRepository.findOne(id);
        AbAccomodationTypesDTO abAccomodationTypesDTO = abAccomodationTypesMapper.toDto(abAccomodationTypes);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abAccomodationTypesDTO));
    }

    /**
     * DELETE  /ab-accomodation-types/:id : delete the "id" abAccomodationTypes.
     *
     * @param id the id of the abAccomodationTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-accomodation-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbAccomodationTypes(@PathVariable Long id) {
        log.debug("REST request to delete AbAccomodationTypes : {}", id);
        abAccomodationTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
