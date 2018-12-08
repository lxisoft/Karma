package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.IdentityProofType;
import com.lxisoft.repository.IdentityProofTypeRepository;
import com.lxisoft.service.IdentityProofTypeService;
import com.lxisoft.service.dto.IdentityProofTypeDTO;
import com.lxisoft.service.mapper.IdentityProofTypeMapper;
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
import java.util.List;


import static com.lxisoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the IdentityProofTypeResource REST controller.
 *
 * @see IdentityProofTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class IdentityProofTypeResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private IdentityProofTypeRepository identityProofTypeRepository;

    @Autowired
    private IdentityProofTypeMapper identityProofTypeMapper;

    @Autowired
    private IdentityProofTypeService identityProofTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIdentityProofTypeMockMvc;

    private IdentityProofType identityProofType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IdentityProofTypeResource identityProofTypeResource = new IdentityProofTypeResource(identityProofTypeService);
        this.restIdentityProofTypeMockMvc = MockMvcBuilders.standaloneSetup(identityProofTypeResource)
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
    public static IdentityProofType createEntity(EntityManager em) {
        IdentityProofType identityProofType = new IdentityProofType()
            .type(DEFAULT_TYPE);
        return identityProofType;
    }

    @Before
    public void initTest() {
        identityProofType = createEntity(em);
    }

    @Test
    @Transactional
    public void createIdentityProofType() throws Exception {
        int databaseSizeBeforeCreate = identityProofTypeRepository.findAll().size();

        // Create the IdentityProofType
        IdentityProofTypeDTO identityProofTypeDTO = identityProofTypeMapper.toDto(identityProofType);
        restIdentityProofTypeMockMvc.perform(post("/api/identity-proof-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the IdentityProofType in the database
        List<IdentityProofType> identityProofTypeList = identityProofTypeRepository.findAll();
        assertThat(identityProofTypeList).hasSize(databaseSizeBeforeCreate + 1);
        IdentityProofType testIdentityProofType = identityProofTypeList.get(identityProofTypeList.size() - 1);
        assertThat(testIdentityProofType.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createIdentityProofTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = identityProofTypeRepository.findAll().size();

        // Create the IdentityProofType with an existing ID
        identityProofType.setId(1L);
        IdentityProofTypeDTO identityProofTypeDTO = identityProofTypeMapper.toDto(identityProofType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIdentityProofTypeMockMvc.perform(post("/api/identity-proof-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdentityProofType in the database
        List<IdentityProofType> identityProofTypeList = identityProofTypeRepository.findAll();
        assertThat(identityProofTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIdentityProofTypes() throws Exception {
        // Initialize the database
        identityProofTypeRepository.saveAndFlush(identityProofType);

        // Get all the identityProofTypeList
        restIdentityProofTypeMockMvc.perform(get("/api/identity-proof-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(identityProofType.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getIdentityProofType() throws Exception {
        // Initialize the database
        identityProofTypeRepository.saveAndFlush(identityProofType);

        // Get the identityProofType
        restIdentityProofTypeMockMvc.perform(get("/api/identity-proof-types/{id}", identityProofType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(identityProofType.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIdentityProofType() throws Exception {
        // Get the identityProofType
        restIdentityProofTypeMockMvc.perform(get("/api/identity-proof-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdentityProofType() throws Exception {
        // Initialize the database
        identityProofTypeRepository.saveAndFlush(identityProofType);

        int databaseSizeBeforeUpdate = identityProofTypeRepository.findAll().size();

        // Update the identityProofType
        IdentityProofType updatedIdentityProofType = identityProofTypeRepository.findById(identityProofType.getId()).get();
        // Disconnect from session so that the updates on updatedIdentityProofType are not directly saved in db
        em.detach(updatedIdentityProofType);
        updatedIdentityProofType
            .type(UPDATED_TYPE);
        IdentityProofTypeDTO identityProofTypeDTO = identityProofTypeMapper.toDto(updatedIdentityProofType);

        restIdentityProofTypeMockMvc.perform(put("/api/identity-proof-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofTypeDTO)))
            .andExpect(status().isOk());

        // Validate the IdentityProofType in the database
        List<IdentityProofType> identityProofTypeList = identityProofTypeRepository.findAll();
        assertThat(identityProofTypeList).hasSize(databaseSizeBeforeUpdate);
        IdentityProofType testIdentityProofType = identityProofTypeList.get(identityProofTypeList.size() - 1);
        assertThat(testIdentityProofType.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingIdentityProofType() throws Exception {
        int databaseSizeBeforeUpdate = identityProofTypeRepository.findAll().size();

        // Create the IdentityProofType
        IdentityProofTypeDTO identityProofTypeDTO = identityProofTypeMapper.toDto(identityProofType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIdentityProofTypeMockMvc.perform(put("/api/identity-proof-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdentityProofType in the database
        List<IdentityProofType> identityProofTypeList = identityProofTypeRepository.findAll();
        assertThat(identityProofTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIdentityProofType() throws Exception {
        // Initialize the database
        identityProofTypeRepository.saveAndFlush(identityProofType);

        int databaseSizeBeforeDelete = identityProofTypeRepository.findAll().size();

        // Get the identityProofType
        restIdentityProofTypeMockMvc.perform(delete("/api/identity-proof-types/{id}", identityProofType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IdentityProofType> identityProofTypeList = identityProofTypeRepository.findAll();
        assertThat(identityProofTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdentityProofType.class);
        IdentityProofType identityProofType1 = new IdentityProofType();
        identityProofType1.setId(1L);
        IdentityProofType identityProofType2 = new IdentityProofType();
        identityProofType2.setId(identityProofType1.getId());
        assertThat(identityProofType1).isEqualTo(identityProofType2);
        identityProofType2.setId(2L);
        assertThat(identityProofType1).isNotEqualTo(identityProofType2);
        identityProofType1.setId(null);
        assertThat(identityProofType1).isNotEqualTo(identityProofType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdentityProofTypeDTO.class);
        IdentityProofTypeDTO identityProofTypeDTO1 = new IdentityProofTypeDTO();
        identityProofTypeDTO1.setId(1L);
        IdentityProofTypeDTO identityProofTypeDTO2 = new IdentityProofTypeDTO();
        assertThat(identityProofTypeDTO1).isNotEqualTo(identityProofTypeDTO2);
        identityProofTypeDTO2.setId(identityProofTypeDTO1.getId());
        assertThat(identityProofTypeDTO1).isEqualTo(identityProofTypeDTO2);
        identityProofTypeDTO2.setId(2L);
        assertThat(identityProofTypeDTO1).isNotEqualTo(identityProofTypeDTO2);
        identityProofTypeDTO1.setId(null);
        assertThat(identityProofTypeDTO1).isNotEqualTo(identityProofTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(identityProofTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(identityProofTypeMapper.fromId(null)).isNull();
    }
}
