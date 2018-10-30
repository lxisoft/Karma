package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.Violation;
import com.lxisoft.repository.ViolationRepository;
import com.lxisoft.service.ViolationService;
import com.lxisoft.service.dto.ViolationDTO;
import com.lxisoft.service.mapper.ViolationMapper;
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
 * Test class for the ViolationResource REST controller.
 *
 * @see ViolationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class ViolationResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ANONYMOUS = false;
    private static final Boolean UPDATED_IS_ANONYMOUS = true;

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ViolationRepository violationRepository;

    @Autowired
    private ViolationMapper violationMapper;
    
    @Autowired
    private ViolationService violationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restViolationMockMvc;

    private Violation violation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ViolationResource violationResource = new ViolationResource(violationService);
        this.restViolationMockMvc = MockMvcBuilders.standaloneSetup(violationResource)
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
    public static Violation createEntity(EntityManager em) {
        Violation violation = new Violation()
            .description(DEFAULT_DESCRIPTION)
            .isAnonymous(DEFAULT_IS_ANONYMOUS)
            .date(DEFAULT_DATE);
        return violation;
    }

    @Before
    public void initTest() {
        violation = createEntity(em);
    }

    @Test
    @Transactional
    public void createViolation() throws Exception {
        int databaseSizeBeforeCreate = violationRepository.findAll().size();

        // Create the Violation
        ViolationDTO violationDTO = violationMapper.toDto(violation);
        restViolationMockMvc.perform(post("/api/violations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(violationDTO)))
            .andExpect(status().isCreated());

        // Validate the Violation in the database
        List<Violation> violationList = violationRepository.findAll();
        assertThat(violationList).hasSize(databaseSizeBeforeCreate + 1);
        Violation testViolation = violationList.get(violationList.size() - 1);
        assertThat(testViolation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testViolation.isIsAnonymous()).isEqualTo(DEFAULT_IS_ANONYMOUS);
        assertThat(testViolation.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createViolationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = violationRepository.findAll().size();

        // Create the Violation with an existing ID
        violation.setId(1L);
        ViolationDTO violationDTO = violationMapper.toDto(violation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restViolationMockMvc.perform(post("/api/violations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(violationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Violation in the database
        List<Violation> violationList = violationRepository.findAll();
        assertThat(violationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllViolations() throws Exception {
        // Initialize the database
        violationRepository.saveAndFlush(violation);

        // Get all the violationList
        restViolationMockMvc.perform(get("/api/violations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(violation.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].isAnonymous").value(hasItem(DEFAULT_IS_ANONYMOUS.booleanValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getViolation() throws Exception {
        // Initialize the database
        violationRepository.saveAndFlush(violation);

        // Get the violation
        restViolationMockMvc.perform(get("/api/violations/{id}", violation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(violation.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.isAnonymous").value(DEFAULT_IS_ANONYMOUS.booleanValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingViolation() throws Exception {
        // Get the violation
        restViolationMockMvc.perform(get("/api/violations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateViolation() throws Exception {
        // Initialize the database
        violationRepository.saveAndFlush(violation);

        int databaseSizeBeforeUpdate = violationRepository.findAll().size();

        // Update the violation
        Violation updatedViolation = violationRepository.findById(violation.getId()).get();
        // Disconnect from session so that the updates on updatedViolation are not directly saved in db
        em.detach(updatedViolation);
        updatedViolation
            .description(UPDATED_DESCRIPTION)
            .isAnonymous(UPDATED_IS_ANONYMOUS)
            .date(UPDATED_DATE);
        ViolationDTO violationDTO = violationMapper.toDto(updatedViolation);

        restViolationMockMvc.perform(put("/api/violations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(violationDTO)))
            .andExpect(status().isOk());

        // Validate the Violation in the database
        List<Violation> violationList = violationRepository.findAll();
        assertThat(violationList).hasSize(databaseSizeBeforeUpdate);
        Violation testViolation = violationList.get(violationList.size() - 1);
        assertThat(testViolation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testViolation.isIsAnonymous()).isEqualTo(UPDATED_IS_ANONYMOUS);
        assertThat(testViolation.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingViolation() throws Exception {
        int databaseSizeBeforeUpdate = violationRepository.findAll().size();

        // Create the Violation
        ViolationDTO violationDTO = violationMapper.toDto(violation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViolationMockMvc.perform(put("/api/violations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(violationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Violation in the database
        List<Violation> violationList = violationRepository.findAll();
        assertThat(violationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteViolation() throws Exception {
        // Initialize the database
        violationRepository.saveAndFlush(violation);

        int databaseSizeBeforeDelete = violationRepository.findAll().size();

        // Get the violation
        restViolationMockMvc.perform(delete("/api/violations/{id}", violation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Violation> violationList = violationRepository.findAll();
        assertThat(violationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Violation.class);
        Violation violation1 = new Violation();
        violation1.setId(1L);
        Violation violation2 = new Violation();
        violation2.setId(violation1.getId());
        assertThat(violation1).isEqualTo(violation2);
        violation2.setId(2L);
        assertThat(violation1).isNotEqualTo(violation2);
        violation1.setId(null);
        assertThat(violation1).isNotEqualTo(violation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ViolationDTO.class);
        ViolationDTO violationDTO1 = new ViolationDTO();
        violationDTO1.setId(1L);
        ViolationDTO violationDTO2 = new ViolationDTO();
        assertThat(violationDTO1).isNotEqualTo(violationDTO2);
        violationDTO2.setId(violationDTO1.getId());
        assertThat(violationDTO1).isEqualTo(violationDTO2);
        violationDTO2.setId(2L);
        assertThat(violationDTO1).isNotEqualTo(violationDTO2);
        violationDTO1.setId(null);
        assertThat(violationDTO1).isNotEqualTo(violationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(violationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(violationMapper.fromId(null)).isNull();
    }
}
