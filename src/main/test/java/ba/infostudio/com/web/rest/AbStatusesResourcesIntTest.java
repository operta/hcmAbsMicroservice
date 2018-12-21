package ba.infostudio.com.web.rest;


import ba.infostudio.com.HcmAbsMicroserviceApp;
import ba.infostudio.com.domain.AbStatuses;
import ba.infostudio.com.repository.AbStatusesRepository;
import ba.infostudio.com.service.mapper.AbStatusesMapper;
import ba.infostudio.com.web.rest.errors.ExceptionTranslator;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static ba.infostudio.com.web.rest.TestUtil.createFormattingConversionService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HcmAbsMicroserviceApp.class)
public class AbStatusesResourcesIntTest {

    private final String STATUS_NAME_1 = "AAAA";

    private AbStatuses abStatuses;

    private MockMvc mvc;

    @Autowired
    private AbStatusesRepository abStatusesRepository;

    @Autowired
    private AbStatusesMapper abStatusesMapper;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;



    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        abStatuses = new AbStatuses();
        final AbStatusesResource abStatusesResource = new AbStatusesResource(abStatusesRepository, abStatusesMapper);

        mvc = MockMvcBuilders.standaloneSetup(abStatusesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setControllerAdvice(exceptionTranslator)
            .build();
    }

    @Test
    public void shouldReturnAtLeastOneAbStatus() throws Exception{
        mvc.perform(get("/api/ab-statuses"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$").exists());
    }

    @Test
    @Transactional
    public void shouldAddOneAbStatus() throws Exception{
        abStatuses.setName(STATUS_NAME_1);
        mvc.perform(post("/api/ab-statuses")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abStatuses)))
            .andExpect(jsonPath("$.name").value(Matchers.equalTo(STATUS_NAME_1)))
            .andExpect(jsonPath("$.id").exists())
            .andExpect(status().isCreated());
    }


    @Test
    public void shouldReturnBadRequestWhenAbStatusCreatedWithExistingId() throws Exception{
        abStatuses.setId(1L);
        mvc.perform(post("/api/ab-statuses")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abStatuses)))
            .andExpect(status().isBadRequest());
    }
}
