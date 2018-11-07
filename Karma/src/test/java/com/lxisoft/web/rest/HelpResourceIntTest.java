package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.Help;
import com.lxisoft.repository.HelpRepository;
import com.lxisoft.service.HelpService;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.mapper.HelpMapper;
import com.lxisoft.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.lxisoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HelpResource REST controller.
 *
 * @see HelpResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class HelpResourceIntTest {

    private static final Instant DEFAULT_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private HelpMapper helpMapper;
    
    @Autowired
    private HelpService helpService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHelpMockMvc;

    private Help help;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HelpResource helpResource = new HelpResource(helpService);
        this.restHelpMockMvc = MockMvcBuilders.standaloneSetup(helpResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Help createEntity(EntityManager em) {
        Help help = new Help()
            .time(DEFAULT_TIME)
            .description(DEFAULT_DESCRIPTION);
        return help;
    }

    @Before
    public void initTest() {
        help = createEntity(em);
    }

    @Test
    @Transactional
    public void createHelp() throws Exception {
        int databaseSizeBeforeCreate = helpRepository.findAll().size();

        // Create the Help
        HelpDTO helpDTO = helpMapper.toDto(help);
        restHelpMockMvc.perform(post("/api/helps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(helpDTO)))
            .andExpect(status().isCreated());

        // Validate the Help in the database
        List<Help> helpList = helpRepository.findAll();
        assertThat(helpList).hasSize(databaseSizeBeforeCreate + 1);
        Help testHelp = helpList.get(helpList.size() - 1);
        assertThat(testHelp.getTime()).isEqualTo(DEFAULT_TIME);
        assertThat(testHelp.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createHelpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = helpRepository.findAll().size();

        // Create the Help with an existing ID
        help.setId(1L);
        HelpDTO helpDTO = helpMapper.toDto(help);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHelpMockMvc.perform(post("/api/helps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(helpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Help in the database
        List<Help> helpList = helpRepository.findAll();
        assertThat(helpList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHelps() throws Exception {
        // Initialize the database
        helpRepository.saveAndFlush(help);

        // Get all the helpList
        restHelpMockMvc.perform(get("/api/helps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(help.getId().intValue())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    @Test
    @Transactional
    public void getHelp() throws Exception {
        // Initialize the database
        helpRepository.saveAndFlush(help);

        // Get the help
        restHelpMockMvc.perform(get("/api/helps/{id}", help.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(help.getId().intValue()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHelp() throws Exception {
        // Get the help
        restHelpMockMvc.perform(get("/api/helps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHelp() throws Exception {
        // Initialize the database
        helpRepository.saveAndFlush(help);

        int databaseSizeBeforeUpdate = helpRepository.findAll().size();

        // Update the help
        Help updatedHelp = helpRepository.findById(help.getId()).get();
        // Disconnect from session so that the updates on updatedHelp are not directly saved in db
        em.detach(updatedHelp);
        updatedHelp
            .time(UPDATED_TIME)
            .description(UPDATED_DESCRIPTION);
        HelpDTO helpDTO = helpMapper.toDto(updatedHelp);

        restHelpMockMvc.perform(put("/api/helps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(helpDTO)))
            .andExpect(status().isOk());

        // Validate the Help in the database
        List<Help> helpList = helpRepository.findAll();
        assertThat(helpList).hasSize(databaseSizeBeforeUpdate);
        Help testHelp = helpList.get(helpList.size() - 1);
        assertThat(testHelp.getTime()).isEqualTo(UPDATED_TIME);
        assertThat(testHelp.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingHelp() throws Exception {
        int databaseSizeBeforeUpdate = helpRepository.findAll().size();

        // Create the Help
        HelpDTO helpDTO = helpMapper.toDto(help);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHelpMockMvc.perform(put("/api/helps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(helpDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Help in the database
        List<Help> helpList = helpRepository.findAll();
        assertThat(helpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHelp() throws Exception {
        // Initialize the database
        helpRepository.saveAndFlush(help);

        int databaseSizeBeforeDelete = helpRepository.findAll().size();

        // Get the help
        restHelpMockMvc.perform(delete("/api/helps/{id}", help.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Help> helpList = helpRepository.findAll();
        assertThat(helpList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Help.class);
        Help help1 = new Help();
        help1.setId(1L);
        Help help2 = new Help();
        help2.setId(help1.getId());
        assertThat(help1).isEqualTo(help2);
        help2.setId(2L);
        assertThat(help1).isNotEqualTo(help2);
        help1.setId(null);
        assertThat(help1).isNotEqualTo(help2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HelpDTO.class);
        HelpDTO helpDTO1 = new HelpDTO();
        helpDTO1.setId(1L);
        HelpDTO helpDTO2 = new HelpDTO();
        assertThat(helpDTO1).isNotEqualTo(helpDTO2);
        helpDTO2.setId(helpDTO1.getId());
        assertThat(helpDTO1).isEqualTo(helpDTO2);
        helpDTO2.setId(2L);
        assertThat(helpDTO1).isNotEqualTo(helpDTO2);
        helpDTO1.setId(null);
        assertThat(helpDTO1).isNotEqualTo(helpDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(helpMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(helpMapper.fromId(null)).isNull();
    }
}
