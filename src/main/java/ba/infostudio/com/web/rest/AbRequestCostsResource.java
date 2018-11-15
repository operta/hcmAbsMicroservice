package ba.infostudio.com.web.rest;

import com.codahale.metrics.annotation.Timed;
import ba.infostudio.com.domain.AbRequestCosts;

import ba.infostudio.com.repository.AbRequestCostsRepository;
import ba.infostudio.com.web.rest.errors.BadRequestAlertException;
import ba.infostudio.com.web.rest.util.HeaderUtil;
import ba.infostudio.com.web.rest.util.PaginationUtil;
import ba.infostudio.com.service.dto.AbRequestCostsDTO;
import ba.infostudio.com.service.mapper.AbRequestCostsMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * REST controller for managing AbRequestCosts.
 */
@RestController
@RequestMapping("/api")
public class AbRequestCostsResource {

    private final Logger log = LoggerFactory.getLogger(AbRequestCostsResource.class);

    private static final String ENTITY_NAME = "abRequestCosts";

    private final AbRequestCostsRepository abRequestCostsRepository;

    private final AbRequestCostsMapper abRequestCostsMapper;

    public AbRequestCostsResource(AbRequestCostsRepository abRequestCostsRepository, AbRequestCostsMapper abRequestCostsMapper) {
        this.abRequestCostsRepository = abRequestCostsRepository;
        this.abRequestCostsMapper = abRequestCostsMapper;
    }

    /**
     * POST  /ab-request-costs : Create a new abRequestCosts.
     *
     * @param abRequestCostsDTO the abRequestCostsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abRequestCostsDTO, or with status 400 (Bad Request) if the abRequestCosts has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ab-request-costs")
    @Timed
    public ResponseEntity<AbRequestCostsDTO> createAbRequestCosts(@RequestBody AbRequestCostsDTO abRequestCostsDTO) throws URISyntaxException {
        log.debug("REST request to save AbRequestCosts : {}", abRequestCostsDTO);
        if (abRequestCostsDTO.getId() != null) {
            throw new BadRequestAlertException("A new abRequestCosts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AbRequestCosts abRequestCosts = abRequestCostsMapper.toEntity(abRequestCostsDTO);
        abRequestCosts = abRequestCostsRepository.save(abRequestCosts);
        AbRequestCostsDTO result = abRequestCostsMapper.toDto(abRequestCosts);
        return ResponseEntity.created(new URI("/api/ab-request-costs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ab-request-costs : Updates an existing abRequestCosts.
     *
     * @param abRequestCostsDTO the abRequestCostsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abRequestCostsDTO,
     * or with status 400 (Bad Request) if the abRequestCostsDTO is not valid,
     * or with status 500 (Internal Server Error) if the abRequestCostsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ab-request-costs")
    @Timed
    public ResponseEntity<AbRequestCostsDTO> updateAbRequestCosts(@RequestBody AbRequestCostsDTO abRequestCostsDTO) throws URISyntaxException {
        log.debug("REST request to update AbRequestCosts : {}", abRequestCostsDTO);
        if (abRequestCostsDTO.getId() == null) {
            return createAbRequestCosts(abRequestCostsDTO);
        }
        AbRequestCosts abRequestCosts = abRequestCostsMapper.toEntity(abRequestCostsDTO);
        abRequestCosts = abRequestCostsRepository.save(abRequestCosts);
        AbRequestCostsDTO result = abRequestCostsMapper.toDto(abRequestCosts);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abRequestCostsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ab-request-costs : get all the abRequestCosts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of abRequestCosts in body
     */
    @GetMapping("/ab-request-costs")
    @Timed
    public ResponseEntity<List<AbRequestCostsDTO>> getAllAbRequestCosts(Pageable pageable) {
        log.debug("REST request to get a page of AbRequestCosts");
        Page<AbRequestCosts> page = abRequestCostsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ab-request-costs");
        return new ResponseEntity<>(abRequestCostsMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /ab-request-costs/:id : get the "id" abRequestCosts.
     *
     * @param id the id of the abRequestCostsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abRequestCostsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ab-request-costs/{id}")
    @Timed
    public ResponseEntity<AbRequestCostsDTO> getAbRequestCosts(@PathVariable Long id) {
        log.debug("REST request to get AbRequestCosts : {}", id);
        AbRequestCosts abRequestCosts = abRequestCostsRepository.findOne(id);
        AbRequestCostsDTO abRequestCostsDTO = abRequestCostsMapper.toDto(abRequestCosts);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestCostsDTO));
    }
    
    public static class YearlyCost{
        private Integer year;
        private Double totalCostInDollars;

        public YearlyCost(Integer year, Double totalCostInDollars) {
            this.year = year;
            this.totalCostInDollars = totalCostInDollars;
        }

        public Integer getYear() {
            return year;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public Double getTotalCostInDollars() {
            return totalCostInDollars;
        }

        public void setTotalCostInDollars(Double totalCostInDollars) {
            this.totalCostInDollars = totalCostInDollars;
        }

        @Override
        public boolean equals(Object o) {
            if(o == this){
                return true;
            }
            if(o == null || o.getClass() != getClass()){
                return false;
            }
            YearlyCost otherCost = (YearlyCost) o;
            return otherCost.year.equals(this.year) &&
                Math.abs(otherCost.getTotalCostInDollars() - this.getTotalCostInDollars()) < 1e-9;
        }
    }

    public static List<YearlyCost> getYearlyCostList(List<AbRequestCosts> abRequestCosts){
        Map<Integer, Double> yearlyCost = new TreeMap<>();
        for (AbRequestCosts cost : abRequestCosts) {
            int yearOfCost = LocalDateTime.ofInstant(cost.getcreatedAt(), ZoneOffset.UTC).getYear();
            if(yearlyCost.containsKey(yearOfCost)){
                double previousCost = yearlyCost.get(yearOfCost);
                yearlyCost.put(yearOfCost, cost.getAmountDollar() + previousCost);
            }else{
                yearlyCost.put(yearOfCost, cost.getAmountDollar());
            }
        }
        List<YearlyCost> yearlyCostList = new ArrayList<>();
        for(Map.Entry<Integer, Double> calculatedYear: yearlyCost.entrySet()){
            yearlyCostList.add(new YearlyCost(calculatedYear.getKey(), calculatedYear.getValue()));
        }
        return yearlyCostList;

    }
    
    @GetMapping("/ab-request-costs/total-per-year")
    @Timed
    public ResponseEntity<List<YearlyCost>> getAbRequestCostsPerTotalPerYear() {
        List<AbRequestCosts> abRequestCosts = this.abRequestCostsRepository.findAll();
        List<YearlyCost> yearlyCostList = getYearlyCostList(abRequestCosts);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(yearlyCostList));
    }

       /**
     * GET  /ab-request-costs/request/:id : get all the absence costs by request
     *
     * @param id of request
     * @return the ResponseEntity with status 200 (OK) and the list of abRequestCost in body
     */
    @GetMapping("/ab-request-costs/request/{id}")
    @Timed
    public ResponseEntity<List<AbRequestCostsDTO>> getAllReportsByRequestId(@PathVariable Long id) {
        List<AbRequestCosts> costs = abRequestCostsRepository.findByIdRequestId(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(abRequestCostsMapper.toDto(costs)));
    }

    /**
     * DELETE  /ab-request-costs/:id : delete the "id" abRequestCosts.
     *
     * @param id the id of the abRequestCostsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ab-request-costs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbRequestCosts(@PathVariable Long id) {
        log.debug("REST request to delete AbRequestCosts : {}", id);
        abRequestCostsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
