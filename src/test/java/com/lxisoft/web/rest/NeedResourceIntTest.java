package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.Need;
import com.lxisoft.repository.NeedRepository;
import com.lxisoft.service.NeedService;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.mapper.NeedMapper;
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
 * Test class for the NeedResource REST controller.
 *
 * @see NeedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class NeedResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_TYPE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private NeedRepository needRepository;

    @Autowired
    private NeedMapper needMapper;

    @Autowired
    private NeedService needService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNeedMockMvc;

    private Need need;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NeedResource needResource = new NeedResource(needService);
        this.restNeedMockMvc = MockMvcBuilders.standaloneSetup(needResource)
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
    public static Need createEntity(EntityManager em) {
        Need need = new Need()
            .description(DEFAULT_DESCRIPTION)
            .beneficiaryType(DEFAULT_BENEFICIARY_TYPE)
            .date(DEFAULT_DATE);
        return need;
    }

    @Before
    public void initTest() {
        need = createEntity(em);
    }

    @Test
    @Transactional
    public void createNeed() throws Exception {
        int databaseSizeBeforeCreate = needRepository.findAll().size();

        // Create the Need
        NeedDTO needDTO = needMapper.toDto(need);
        restNeedMockMvc.perform(post("/api/needs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(needDTO)))
            .andExpect(status().isCreated());

        // Validate the Need in the database
        List<Need> needList = needRepository.findAll();
        assertThat(needList).hasSize(databaseSizeBeforeCreate + 1);
        Need testNeed = needList.get(needList.size() - 1);
        assertThat(testNeed.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNeed.getBeneficiaryType()).isEqualTo(DEFAULT_BENEFICIARY_TYPE);
        assertThat(testNeed.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createNeedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = needRepository.findAll().size();

        // Create the Need with an existing ID
        need.setId(1L);
        NeedDTO needDTO = needMapper.toDto(need);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNeedMockMvc.perform(post("/api/needs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(needDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Need in the database
        List<Need> needList = needRepository.findAll();
        assertThat(needList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNeeds() throws Exception {
        // Initialize the database
        needRepository.saveAndFlush(need);

        // Get all the needList
        restNeedMockMvc.perform(get("/api/needs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(need.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].beneficiaryType").value(hasItem(DEFAULT_BENEFICIARY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }

    @Test
    @Transactional
    public void getNeed() throws Exception {
        // Initialize the database
        needRepository.saveAndFlush(need);

        // Get the need
        restNeedMockMvc.perform(get("/api/needs/{id}", need.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(need.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.beneficiaryType").value(DEFAULT_BENEFICIARY_TYPE.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNeed() throws Exception {
        // Get the need
        restNeedMockMvc.perform(get("/api/needs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNeed() throws Exception {
        // Initialize the database
        needRepository.saveAndFlush(need);
        int databaseSizeBeforeUpdate = needRepository.findAll().size();

        // Update the need
        Need updatedNeed = needRepository.findOne(need.getId());
        // Disconnect from session so that the updates on updatedNeed are not directly saved in db
        em.detach(updatedNeed);
        updatedNeed
            .description(UPDATED_DESCRIPTION)
            .beneficiaryType(UPDATED_BENEFICIARY_TYPE)
            .date(UPDATED_DATE);
        NeedDTO needDTO = needMapper.toDto(updatedNeed);

        restNeedMockMvc.perform(put("/api/needs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(needDTO)))
            .andExpect(status().isOk());

        // Validate the Need in the database
        List<Need> needList = needRepository.findAll();
        assertThat(needList).hasSize(databaseSizeBeforeUpdate);
        Need testNeed = needList.get(needList.size() - 1);
        assertThat(testNeed.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNeed.getBeneficiaryType()).isEqualTo(UPDATED_BENEFICIARY_TYPE);
        assertThat(testNeed.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingNeed() throws Exception {
        int databaseSizeBeforeUpdate = needRepository.findAll().size();

        // Create the Need
        NeedDTO needDTO = needMapper.toDto(need);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNeedMockMvc.perform(put("/api/needs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(needDTO)))
            .andExpect(status().isCreated());

        // Validate the Need in the database
        List<Need> needList = needRepository.findAll();
        assertThat(needList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNeed() throws Exception {
        // Initialize the database
        needRepository.saveAndFlush(need);
        int databaseSizeBeforeDelete = needRepository.findAll().size();

        // Get the need
        restNeedMockMvc.perform(delete("/api/needs/{id}", need.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Need> needList = needRepository.findAll();
        assertThat(needList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Need.class);
        Need need1 = new Need();
        need1.setId(1L);
        Need need2 = new Need();
        need2.setId(need1.getId());
        assertThat(need1).isEqualTo(need2);
        need2.setId(2L);
        assertThat(need1).isNotEqualTo(need2);
        need1.setId(null);
        assertThat(need1).isNotEqualTo(need2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NeedDTO.class);
        NeedDTO needDTO1 = new NeedDTO();
        needDTO1.setId(1L);
        NeedDTO needDTO2 = new NeedDTO();
        assertThat(needDTO1).isNotEqualTo(needDTO2);
        needDTO2.setId(needDTO1.getId());
        assertThat(needDTO1).isEqualTo(needDTO2);
        needDTO2.setId(2L);
        assertThat(needDTO1).isNotEqualTo(needDTO2);
        needDTO1.setId(null);
        assertThat(needDTO1).isNotEqualTo(needDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(needMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(needMapper.fromId(null)).isNull();
    }
}
