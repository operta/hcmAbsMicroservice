package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.EmEmployees;

import ba.infostudio.com.repository.EmEmployeesRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.service.dto.EmEmployeesDTO;
import ba.infostudio.com.service.mapper.EmEmployeesMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EmEmployees.
 */
@RestController
@RequestMapping("/api")
public class EmEmployeesResource {

    private final Logger log = LoggerFactory.getLogger(EmEmployeesResource.class);

    private static final String ENTITY_NAME = "emEmployees";

    private final EmEmployeesRepository emEmployeesRepository;

    private final EmEmployeesMapper emEmployeesMapper;

    public EmEmployeesResource(EmEmployeesRepository emEmployeesRepository, EmEmployeesMapper emEmployeesMapper) {
        this.emEmployeesRepository = emEmployeesRepository;
        this.emEmployeesMapper = emEmployeesMapper;
    }

    /**
     * POST  /em-employees : Create a new emEmployees.
     *
     * @param emEmployeesDTO the emEmployeesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new emEmployeesDTO, or with status 400 (Bad Request) if the emEmployees has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/em-employees")
    @Timed
    public ResponseEntity<EmEmployeesDTO> createEmEmployees(@Valid @RequestBody EmEmployeesDTO emEmployeesDTO) throws URISyntaxException {
        log.debug("REST request to save EmEmployees : {}", emEmployeesDTO);
        if (emEmployeesDTO.getId() != null) {
            throw new BadRequestAlertException("A new emEmployees cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmEmployees emEmployees = emEmployeesMapper.toEntity(emEmployeesDTO);
        emEmployees = emEmployeesRepository.save(emEmployees);
        EmEmployeesDTO result = emEmployeesMapper.toDto(emEmployees);
        return ResponseEntity.created(new URI("/api/em-employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /em-employees : Updates an existing emEmployees.
     *
     * @param emEmployeesDTO the emEmployeesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated emEmployeesDTO,
     * or with status 400 (Bad Request) if the emEmployeesDTO is not valid,
     * or with status 500 (Internal Server Error) if the emEmployeesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/em-employees")
    @Timed
    public ResponseEntity<EmEmployeesDTO> updateEmEmployees(@Valid @RequestBody EmEmployeesDTO emEmployeesDTO) throws URISyntaxException {
        log.debug("REST request to update EmEmployees : {}", emEmployeesDTO);
        if (emEmployeesDTO.getId() == null) {
            return createEmEmployees(emEmployeesDTO);
        }
        EmEmployees emEmployees = emEmployeesMapper.toEntity(emEmployeesDTO);
        emEmployees = emEmployeesRepository.save(emEmployees);
        EmEmployeesDTO result = emEmployeesMapper.toDto(emEmployees);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, emEmployeesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /em-employees : get all the emEmployees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of emEmployees in body
     */
    @GetMapping("/em-employees")
    @Timed
    public List<EmEmployeesDTO> getAllEmEmployees() {
        log.debug("REST request to get all EmEmployees");
        List<EmEmployees> emEmployees = emEmployeesRepository.findAll();
        return emEmployeesMapper.toDto(emEmployees);
        }

    /**
     * GET  /em-employees/:id : get the "id" emEmployees.
     *
     * @param id the id of the emEmployeesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emEmployeesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/em-employees/{id}")
    @Timed
    public ResponseEntity<EmEmployeesDTO> getEmEmployees(@PathVariable Long id) {
        log.debug("REST request to get EmEmployees : {}", id);
        EmEmployees emEmployees = emEmployeesRepository.findOne(id);
        EmEmployeesDTO emEmployeesDTO = emEmployeesMapper.toDto(emEmployees);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emEmployeesDTO));
    }


    /**
     * GET  /em-employees/user/:id : get the emEmployee by user id.
     *
     * @param id the id of the User to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the emEmployees, or with status 404 (Not Found)
     */
    @GetMapping("/em-employees/user/{id}")
    @Timed
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<EmEmployeesDTO> getEmEmployeesByUserId(@PathVariable Integer id) {
        log.debug("REST request to get EmEmployees by user id: {}", id);
        EmEmployees emEmployees = emEmployeesRepository.findByIdUser(id);
        EmEmployeesDTO emEmployeesDTO = emEmployeesMapper.toDto(emEmployees);
        if(emEmployeesDTO == null) {
            emEmployeesDTO = new EmEmployeesDTO();
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(emEmployeesDTO));
    }


    /**
     * DELETE  /em-employees/:id : delete the "id" emEmployees.
     *
     * @param id the id of the emEmployeesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/em-employees/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmEmployees(@PathVariable Long id) {
        log.debug("REST request to delete EmEmployees : {}", id);
        emEmployeesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
