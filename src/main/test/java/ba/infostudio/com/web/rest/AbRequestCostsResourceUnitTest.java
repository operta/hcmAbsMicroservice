package ba.infostudio.com.web.rest;

import ba.infostudio.com.HcmAbsMicroserviceApp;
import ba.infostudio.com.domain.AbRequestCosts;
import ba.infostudio.com.repository.AbRequestCostsRepository;
import ba.infostudio.com.service.dto.AbRequestCostsDTO;
import ba.infostudio.com.service.mapper.AbRequestCostsMapper;
import ba.infostudio.com.web.rest.AbRequestCostsResource;
import ba.infostudio.com.web.rest.AbRequestCostsResource.YearlyCost;
import com.netflix.discovery.converters.Auto;
import org.joda.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HcmAbsMicroserviceApp.class)
//@AutoConfigureMockMvc
public class AbRequestCostsResourceUnitTest {
    private final Double AMOUNT_COST = 1000.0;
    private final Double AMOUNT_COST2 = 2000.0;
    private final Integer COST_YEAR1 = 2016;
    private final Integer COST_YEAR2 = 2018;
    private final Integer ANY_YEAR = 2018;
    private final Double ANY_COST = 2102.1;
    private final Integer YEAR = LocalDate.now().getYear();

    private List<AbRequestCosts> abRequestCosts;
    private AbRequestCosts cost1;
    private AbRequestCosts cost2;

    private MockMvc mvc;


    @MockBean
    private AbRequestCostsRepository abRequestCostsRepository;

    @MockBean
    private AbRequestCostsMapper abRequestCostsMapper;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        AbRequestCostsResource abRequestCostsResource = new AbRequestCostsResource(abRequestCostsRepository,
            abRequestCostsMapper);

        mvc = MockMvcBuilders.standaloneSetup(abRequestCostsResource).build();
        //abRequestCostsRepository = mock(AbRequestCostsRepository.class);
        abRequestCosts = new ArrayList<>();

        cost1 = new AbRequestCosts();
        cost1.setAmountDollar(AMOUNT_COST);

        cost2 = new AbRequestCosts();
        cost2.setAmountDollar(AMOUNT_COST2);
    }

    @Test
    public void shouldReturnOneAbRequestCost() throws Exception{
        AbRequestCosts abRequestCosts = new AbRequestCosts();
        abRequestCosts.setId((long)3);
        when(abRequestCostsRepository.findOne((long)3)).thenReturn(abRequestCosts);

        AbRequestCostsDTO abRequestCostsDto = new AbRequestCostsDTO();
        abRequestCostsDto.setId((long)3);
        when(abRequestCostsMapper.toDto(abRequestCosts)).thenReturn(abRequestCostsDto);

        mvc.perform(get("/api/ab-request-costs/{id}",  abRequestCosts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id").value(abRequestCosts.getId()));
    }


    @Test
    public void shouldReturnYearlyCostListForOneYear(){
        abRequestCosts.add(cost1);
        abRequestCosts.add(cost2);

        List<YearlyCost> yearlyCostList =
            AbRequestCostsResource.getYearlyCostList(abRequestCosts);

        // expected
        List<YearlyCost> expectedYearlyCostList = new ArrayList<>();
        expectedYearlyCostList.add(new YearlyCost(YEAR, AMOUNT_COST + AMOUNT_COST2));

        assertEquals(expectedYearlyCostList.toArray(), yearlyCostList.toArray());
    }

    @Test
    public void shouldReturnYearlyCostForAllYears(){
        cost1.setcreatedAt(LocalDate.of(COST_YEAR1, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC));
        cost2.setcreatedAt(LocalDate.of(COST_YEAR2, 1, 1).atStartOfDay().toInstant(ZoneOffset.UTC));

        abRequestCosts.add(cost1);
        abRequestCosts.add(cost2);

        List<YearlyCost> yearlyCostList = AbRequestCostsResource.getYearlyCostList(abRequestCosts);

        // expected
        List<YearlyCost> expectedYearlyCostList = new ArrayList<>();
        expectedYearlyCostList.add(new YearlyCost(COST_YEAR1, AMOUNT_COST));
        expectedYearlyCostList.add(new YearlyCost(COST_YEAR2, AMOUNT_COST2));

        assertEquals(expectedYearlyCostList.toArray(), yearlyCostList.toArray());
    }

    @Test
    public void shouldReturnEmptyYearlyCostList(){
        List<AbRequestCosts> abRequestCosts = new ArrayList<>();
        List<YearlyCost> yearlyCostList =
            AbRequestCostsResource.getYearlyCostList(abRequestCosts);

        assertEquals(new YearlyCost[0], yearlyCostList.toArray());
    }

    @Test
    public void shouldReturnTrueForEqualYearlyCosts(){
        YearlyCost yearlyCost = new YearlyCost(ANY_YEAR, ANY_COST);
        YearlyCost expectedYearlyCost = new YearlyCost(ANY_YEAR, ANY_COST);

        assertEquals(expectedYearlyCost, yearlyCost);
    }

    @Test
    public void shouldReturnFalseForDifferentYearlyCosts(){
        YearlyCost yearlyCost = new YearlyCost(ANY_YEAR, 2100.0);
        YearlyCost expectedYearlyCost = new YearlyCost(ANY_YEAR, ANY_COST);

        assertNotEquals(expectedYearlyCost, yearlyCost);
    }


}
