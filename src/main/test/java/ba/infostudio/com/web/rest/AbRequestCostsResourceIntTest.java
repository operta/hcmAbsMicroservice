package ba.infostudio.com.web.rest;

import ba.infostudio.com.HcmAbsMicroserviceApp;
import ba.infostudio.com.repository.AbRequestCostsRepository;
import ba.infostudio.com.service.mapper.AbRequestCostsMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HcmAbsMicroserviceApp.class)
public class AbRequestCostsResourceIntTest {


    private MockMvc mvc;

    @Autowired
    private AbRequestCostsRepository abRequestCostsRepository;

    @Autowired
    private AbRequestCostsMapper abRequestCostsMapper;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        final AbRequestCostsResource abRequestCostsResource = new AbRequestCostsResource(abRequestCostsRepository,
            abRequestCostsMapper);

        mvc = MockMvcBuilders.standaloneSetup(abRequestCostsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .build();
    }


    @Test
    public void shouldReturnOneAbRequestCosts() throws Exception{
         mvc.perform(get("/api/ab-request-costs"))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
             .andExpect(jsonPath("$").exists());
    }
}
