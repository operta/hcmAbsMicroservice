package ba.infostudio.com.web.rest;

import ba.infostudio.com.domain.AbRequestCosts;
import ba.infostudio.com.web.rest.AbRequestCostsResource;
import ba.infostudio.com.web.rest.AbRequestCostsResource.YearlyCost;
import org.joda.time.Instant;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class AbRequestCostsResourceIntTest {
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

    @Before
    public void setup(){
        abRequestCosts = new ArrayList<>();

        cost1 = new AbRequestCosts();
        cost1.setAmountDollar(AMOUNT_COST);

        cost2 = new AbRequestCosts();
        cost2.setAmountDollar(AMOUNT_COST2);
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
