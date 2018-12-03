package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.IdentityProof;
import com.lxisoft.repository.IdentityProofRepository;
import com.lxisoft.service.IdentityProofService;
import com.lxisoft.service.dto.IdentityProofDTO;
import com.lxisoft.service.mapper.IdentityProofMapper;
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
 * Test class for the IdentityProofResource REST controller.
 *
 * @see IdentityProofResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class IdentityProofResourceIntTest {

    private static final String DEFAULT_ID_NO = "AAAAAAAAAA";
    private static final String UPDATED_ID_NO = "BBBBBBBBBB";

    @Autowired
    private IdentityProofRepository identityProofRepository;

    @Autowired
    private IdentityProofMapper identityProofMapper;

    @Autowired
    private IdentityProofService identityProofService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIdentityProofMockMvc;

    private IdentityProof identityProof;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IdentityProofResource identityProofResource = new IdentityProofResource(identityProofService);
        this.restIdentityProofMockMvc = MockMvcBuilders.standaloneSetup(identityProofResource)
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
    public static IdentityProof createEntity(EntityManager em) {
        IdentityProof identityProof = new IdentityProof()
            .idNo(DEFAULT_ID_NO);
        return identityProof;
    }

    @Before
    public void initTest() {
        identityProof = createEntity(em);
    }

    @Test
    @Transactional
    public void createIdentityProof() throws Exception {
        int databaseSizeBeforeCreate = identityProofRepository.findAll().size();

        // Create the IdentityProof
        IdentityProofDTO identityProofDTO = identityProofMapper.toDto(identityProof);
        restIdentityProofMockMvc.perform(post("/api/identity-proofs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofDTO)))
            .andExpect(status().isCreated());

        // Validate the IdentityProof in the database
        List<IdentityProof> identityProofList = identityProofRepository.findAll();
        assertThat(identityProofList).hasSize(databaseSizeBeforeCreate + 1);
        IdentityProof testIdentityProof = identityProofList.get(identityProofList.size() - 1);
        assertThat(testIdentityProof.getIdNo()).isEqualTo(DEFAULT_ID_NO);
    }

    @Test
    @Transactional
    public void createIdentityProofWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = identityProofRepository.findAll().size();

        // Create the IdentityProof with an existing ID
        identityProof.setId(1L);
        IdentityProofDTO identityProofDTO = identityProofMapper.toDto(identityProof);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIdentityProofMockMvc.perform(post("/api/identity-proofs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdentityProof in the database
        List<IdentityProof> identityProofList = identityProofRepository.findAll();
        assertThat(identityProofList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIdentityProofs() throws Exception {
        // Initialize the database
        identityProofRepository.saveAndFlush(identityProof);

        // Get all the identityProofList
        restIdentityProofMockMvc.perform(get("/api/identity-proofs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(identityProof.getId().intValue())))
            .andExpect(jsonPath("$.[*].idNo").value(hasItem(DEFAULT_ID_NO.toString())));
    }
    
    @Test
    @Transactional
    public void getIdentityProof() throws Exception {
        // Initialize the database
        identityProofRepository.saveAndFlush(identityProof);

        // Get the identityProof
        restIdentityProofMockMvc.perform(get("/api/identity-proofs/{id}", identityProof.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(identityProof.getId().intValue()))
            .andExpect(jsonPath("$.idNo").value(DEFAULT_ID_NO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIdentityProof() throws Exception {
        // Get the identityProof
        restIdentityProofMockMvc.perform(get("/api/identity-proofs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdentityProof() throws Exception {
        // Initialize the database
        identityProofRepository.saveAndFlush(identityProof);

        int databaseSizeBeforeUpdate = identityProofRepository.findAll().size();

        // Update the identityProof
        IdentityProof updatedIdentityProof = identityProofRepository.findById(identityProof.getId()).get();
        // Disconnect from session so that the updates on updatedIdentityProof are not directly saved in db
        em.detach(updatedIdentityProof);
        updatedIdentityProof
            .idNo(UPDATED_ID_NO);
        IdentityProofDTO identityProofDTO = identityProofMapper.toDto(updatedIdentityProof);

        restIdentityProofMockMvc.perform(put("/api/identity-proofs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofDTO)))
            .andExpect(status().isOk());

        // Validate the IdentityProof in the database
        List<IdentityProof> identityProofList = identityProofRepository.findAll();
        assertThat(identityProofList).hasSize(databaseSizeBeforeUpdate);
        IdentityProof testIdentityProof = identityProofList.get(identityProofList.size() - 1);
        assertThat(testIdentityProof.getIdNo()).isEqualTo(UPDATED_ID_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingIdentityProof() throws Exception {
        int databaseSizeBeforeUpdate = identityProofRepository.findAll().size();

        // Create the IdentityProof
        IdentityProofDTO identityProofDTO = identityProofMapper.toDto(identityProof);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIdentityProofMockMvc.perform(put("/api/identity-proofs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(identityProofDTO)))
            .andExpect(status().isBadRequest());

        // Validate the IdentityProof in the database
        List<IdentityProof> identityProofList = identityProofRepository.findAll();
        assertThat(identityProofList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIdentityProof() throws Exception {
        // Initialize the database
        identityProofRepository.saveAndFlush(identityProof);

        int databaseSizeBeforeDelete = identityProofRepository.findAll().size();

        // Get the identityProof
        restIdentityProofMockMvc.perform(delete("/api/identity-proofs/{id}", identityProof.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IdentityProof> identityProofList = identityProofRepository.findAll();
        assertThat(identityProofList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdentityProof.class);
        IdentityProof identityProof1 = new IdentityProof();
        identityProof1.setId(1L);
        IdentityProof identityProof2 = new IdentityProof();
        identityProof2.setId(identityProof1.getId());
        assertThat(identityProof1).isEqualTo(identityProof2);
        identityProof2.setId(2L);
        assertThat(identityProof1).isNotEqualTo(identityProof2);
        identityProof1.setId(null);
        assertThat(identityProof1).isNotEqualTo(identityProof2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IdentityProofDTO.class);
        IdentityProofDTO identityProofDTO1 = new IdentityProofDTO();
        identityProofDTO1.setId(1L);
        IdentityProofDTO identityProofDTO2 = new IdentityProofDTO();
        assertThat(identityProofDTO1).isNotEqualTo(identityProofDTO2);
        identityProofDTO2.setId(identityProofDTO1.getId());
        assertThat(identityProofDTO1).isEqualTo(identityProofDTO2);
        identityProofDTO2.setId(2L);
        assertThat(identityProofDTO1).isNotEqualTo(identityProofDTO2);
        identityProofDTO1.setId(null);
        assertThat(identityProofDTO1).isNotEqualTo(identityProofDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(identityProofMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(identityProofMapper.fromId(null)).isNull();
    }
}
