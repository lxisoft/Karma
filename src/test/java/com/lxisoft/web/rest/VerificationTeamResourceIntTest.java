package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.VerificationTeam;
import com.lxisoft.repository.VerificationTeamRepository;
import com.lxisoft.service.VerificationTeamService;
import com.lxisoft.service.dto.VerificationTeamDTO;
import com.lxisoft.service.mapper.VerificationTeamMapper;
import com.lxisoft.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.lxisoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VerificationTeamResource REST controller.
 *
 * @see VerificationTeamResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class VerificationTeamResourceIntTest {

    private static final String DEFAULT_APPROVAL_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL_LEVEL = "BBBBBBBBBB";

    @Autowired
    private VerificationTeamRepository verificationTeamRepository;

    @Mock
    private VerificationTeamRepository verificationTeamRepositoryMock;

    @Autowired
    private VerificationTeamMapper verificationTeamMapper;
    

    @Mock
    private VerificationTeamService verificationTeamServiceMock;

    @Autowired
    private VerificationTeamService verificationTeamService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVerificationTeamMockMvc;

    private VerificationTeam verificationTeam;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VerificationTeamResource verificationTeamResource = new VerificationTeamResource(verificationTeamService);
        this.restVerificationTeamMockMvc = MockMvcBuilders.standaloneSetup(verificationTeamResource)
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
    public static VerificationTeam createEntity(EntityManager em) {
        VerificationTeam verificationTeam = new VerificationTeam()
            .approvalLevel(DEFAULT_APPROVAL_LEVEL);
        return verificationTeam;
    }

    @Before
    public void initTest() {
        verificationTeam = createEntity(em);
    }

    @Test
    @Transactional
    public void createVerificationTeam() throws Exception {
        int databaseSizeBeforeCreate = verificationTeamRepository.findAll().size();

        // Create the VerificationTeam
        VerificationTeamDTO verificationTeamDTO = verificationTeamMapper.toDto(verificationTeam);
        restVerificationTeamMockMvc.perform(post("/api/verification-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(verificationTeamDTO)))
            .andExpect(status().isCreated());

        // Validate the VerificationTeam in the database
        List<VerificationTeam> verificationTeamList = verificationTeamRepository.findAll();
        assertThat(verificationTeamList).hasSize(databaseSizeBeforeCreate + 1);
        VerificationTeam testVerificationTeam = verificationTeamList.get(verificationTeamList.size() - 1);
        assertThat(testVerificationTeam.getApprovalLevel()).isEqualTo(DEFAULT_APPROVAL_LEVEL);
    }

    @Test
    @Transactional
    public void createVerificationTeamWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = verificationTeamRepository.findAll().size();

        // Create the VerificationTeam with an existing ID
        verificationTeam.setId(1L);
        VerificationTeamDTO verificationTeamDTO = verificationTeamMapper.toDto(verificationTeam);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVerificationTeamMockMvc.perform(post("/api/verification-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(verificationTeamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VerificationTeam in the database
        List<VerificationTeam> verificationTeamList = verificationTeamRepository.findAll();
        assertThat(verificationTeamList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVerificationTeams() throws Exception {
        // Initialize the database
        verificationTeamRepository.saveAndFlush(verificationTeam);

        // Get all the verificationTeamList
        restVerificationTeamMockMvc.perform(get("/api/verification-teams?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(verificationTeam.getId().intValue())))
            .andExpect(jsonPath("$.[*].approvalLevel").value(hasItem(DEFAULT_APPROVAL_LEVEL.toString())));
    }
    
    public void getAllVerificationTeamsWithEagerRelationshipsIsEnabled() throws Exception {
        VerificationTeamResource verificationTeamResource = new VerificationTeamResource(verificationTeamServiceMock);
        when(verificationTeamServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restVerificationTeamMockMvc = MockMvcBuilders.standaloneSetup(verificationTeamResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restVerificationTeamMockMvc.perform(get("/api/verification-teams?eagerload=true"))
        .andExpect(status().isOk());

        verify(verificationTeamServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllVerificationTeamsWithEagerRelationshipsIsNotEnabled() throws Exception {
        VerificationTeamResource verificationTeamResource = new VerificationTeamResource(verificationTeamServiceMock);
            when(verificationTeamServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restVerificationTeamMockMvc = MockMvcBuilders.standaloneSetup(verificationTeamResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restVerificationTeamMockMvc.perform(get("/api/verification-teams?eagerload=true"))
        .andExpect(status().isOk());

            verify(verificationTeamServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getVerificationTeam() throws Exception {
        // Initialize the database
        verificationTeamRepository.saveAndFlush(verificationTeam);

        // Get the verificationTeam
        restVerificationTeamMockMvc.perform(get("/api/verification-teams/{id}", verificationTeam.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(verificationTeam.getId().intValue()))
            .andExpect(jsonPath("$.approvalLevel").value(DEFAULT_APPROVAL_LEVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVerificationTeam() throws Exception {
        // Get the verificationTeam
        restVerificationTeamMockMvc.perform(get("/api/verification-teams/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVerificationTeam() throws Exception {
        // Initialize the database
        verificationTeamRepository.saveAndFlush(verificationTeam);

        int databaseSizeBeforeUpdate = verificationTeamRepository.findAll().size();

        // Update the verificationTeam
        VerificationTeam updatedVerificationTeam = verificationTeamRepository.findById(verificationTeam.getId()).get();
        // Disconnect from session so that the updates on updatedVerificationTeam are not directly saved in db
        em.detach(updatedVerificationTeam);
        updatedVerificationTeam
            .approvalLevel(UPDATED_APPROVAL_LEVEL);
        VerificationTeamDTO verificationTeamDTO = verificationTeamMapper.toDto(updatedVerificationTeam);

        restVerificationTeamMockMvc.perform(put("/api/verification-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(verificationTeamDTO)))
            .andExpect(status().isOk());

        // Validate the VerificationTeam in the database
        List<VerificationTeam> verificationTeamList = verificationTeamRepository.findAll();
        assertThat(verificationTeamList).hasSize(databaseSizeBeforeUpdate);
        VerificationTeam testVerificationTeam = verificationTeamList.get(verificationTeamList.size() - 1);
        assertThat(testVerificationTeam.getApprovalLevel()).isEqualTo(UPDATED_APPROVAL_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingVerificationTeam() throws Exception {
        int databaseSizeBeforeUpdate = verificationTeamRepository.findAll().size();

        // Create the VerificationTeam
        VerificationTeamDTO verificationTeamDTO = verificationTeamMapper.toDto(verificationTeam);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVerificationTeamMockMvc.perform(put("/api/verification-teams")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(verificationTeamDTO)))
            .andExpect(status().isBadRequest());

        // Validate the VerificationTeam in the database
        List<VerificationTeam> verificationTeamList = verificationTeamRepository.findAll();
        assertThat(verificationTeamList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVerificationTeam() throws Exception {
        // Initialize the database
        verificationTeamRepository.saveAndFlush(verificationTeam);

        int databaseSizeBeforeDelete = verificationTeamRepository.findAll().size();

        // Get the verificationTeam
        restVerificationTeamMockMvc.perform(delete("/api/verification-teams/{id}", verificationTeam.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VerificationTeam> verificationTeamList = verificationTeamRepository.findAll();
        assertThat(verificationTeamList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VerificationTeam.class);
        VerificationTeam verificationTeam1 = new VerificationTeam();
        verificationTeam1.setId(1L);
        VerificationTeam verificationTeam2 = new VerificationTeam();
        verificationTeam2.setId(verificationTeam1.getId());
        assertThat(verificationTeam1).isEqualTo(verificationTeam2);
        verificationTeam2.setId(2L);
        assertThat(verificationTeam1).isNotEqualTo(verificationTeam2);
        verificationTeam1.setId(null);
        assertThat(verificationTeam1).isNotEqualTo(verificationTeam2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VerificationTeamDTO.class);
        VerificationTeamDTO verificationTeamDTO1 = new VerificationTeamDTO();
        verificationTeamDTO1.setId(1L);
        VerificationTeamDTO verificationTeamDTO2 = new VerificationTeamDTO();
        assertThat(verificationTeamDTO1).isNotEqualTo(verificationTeamDTO2);
        verificationTeamDTO2.setId(verificationTeamDTO1.getId());
        assertThat(verificationTeamDTO1).isEqualTo(verificationTeamDTO2);
        verificationTeamDTO2.setId(2L);
        assertThat(verificationTeamDTO1).isNotEqualTo(verificationTeamDTO2);
        verificationTeamDTO1.setId(null);
        assertThat(verificationTeamDTO1).isNotEqualTo(verificationTeamDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(verificationTeamMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(verificationTeamMapper.fromId(null)).isNull();
    }
}
