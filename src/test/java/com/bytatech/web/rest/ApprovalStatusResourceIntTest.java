package com.bytatech.web.rest;

import com.bytatech.KarmaApp;

import com.bytatech.domain.ApprovalStatus;
import com.bytatech.repository.ApprovalStatusRepository;
import com.bytatech.web.rest.errors.ExceptionTranslator;

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

import static com.bytatech.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ApprovalStatusResource REST controller.
 *
 * @see ApprovalStatusResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class ApprovalStatusResourceIntTest {

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private ApprovalStatusRepository approvalStatusRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restApprovalStatusMockMvc;

    private ApprovalStatus approvalStatus;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ApprovalStatusResource approvalStatusResource = new ApprovalStatusResource(approvalStatusRepository);
        this.restApprovalStatusMockMvc = MockMvcBuilders.standaloneSetup(approvalStatusResource)
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
    public static ApprovalStatus createEntity(EntityManager em) {
        ApprovalStatus approvalStatus = new ApprovalStatus()
            .status(DEFAULT_STATUS);
        return approvalStatus;
    }

    @Before
    public void initTest() {
        approvalStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createApprovalStatus() throws Exception {
        int databaseSizeBeforeCreate = approvalStatusRepository.findAll().size();

        // Create the ApprovalStatus
        restApprovalStatusMockMvc.perform(post("/api/approval-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalStatus)))
            .andExpect(status().isCreated());

        // Validate the ApprovalStatus in the database
        List<ApprovalStatus> approvalStatusList = approvalStatusRepository.findAll();
        assertThat(approvalStatusList).hasSize(databaseSizeBeforeCreate + 1);
        ApprovalStatus testApprovalStatus = approvalStatusList.get(approvalStatusList.size() - 1);
        assertThat(testApprovalStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createApprovalStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = approvalStatusRepository.findAll().size();

        // Create the ApprovalStatus with an existing ID
        approvalStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restApprovalStatusMockMvc.perform(post("/api/approval-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalStatus)))
            .andExpect(status().isBadRequest());

        // Validate the ApprovalStatus in the database
        List<ApprovalStatus> approvalStatusList = approvalStatusRepository.findAll();
        assertThat(approvalStatusList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllApprovalStatuses() throws Exception {
        // Initialize the database
        approvalStatusRepository.saveAndFlush(approvalStatus);

        // Get all the approvalStatusList
        restApprovalStatusMockMvc.perform(get("/api/approval-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(approvalStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getApprovalStatus() throws Exception {
        // Initialize the database
        approvalStatusRepository.saveAndFlush(approvalStatus);

        // Get the approvalStatus
        restApprovalStatusMockMvc.perform(get("/api/approval-statuses/{id}", approvalStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(approvalStatus.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApprovalStatus() throws Exception {
        // Get the approvalStatus
        restApprovalStatusMockMvc.perform(get("/api/approval-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApprovalStatus() throws Exception {
        // Initialize the database
        approvalStatusRepository.saveAndFlush(approvalStatus);
        int databaseSizeBeforeUpdate = approvalStatusRepository.findAll().size();

        // Update the approvalStatus
        ApprovalStatus updatedApprovalStatus = approvalStatusRepository.findOne(approvalStatus.getId());
        // Disconnect from session so that the updates on updatedApprovalStatus are not directly saved in db
        em.detach(updatedApprovalStatus);
        updatedApprovalStatus
            .status(UPDATED_STATUS);

        restApprovalStatusMockMvc.perform(put("/api/approval-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedApprovalStatus)))
            .andExpect(status().isOk());

        // Validate the ApprovalStatus in the database
        List<ApprovalStatus> approvalStatusList = approvalStatusRepository.findAll();
        assertThat(approvalStatusList).hasSize(databaseSizeBeforeUpdate);
        ApprovalStatus testApprovalStatus = approvalStatusList.get(approvalStatusList.size() - 1);
        assertThat(testApprovalStatus.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingApprovalStatus() throws Exception {
        int databaseSizeBeforeUpdate = approvalStatusRepository.findAll().size();

        // Create the ApprovalStatus

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restApprovalStatusMockMvc.perform(put("/api/approval-statuses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(approvalStatus)))
            .andExpect(status().isCreated());

        // Validate the ApprovalStatus in the database
        List<ApprovalStatus> approvalStatusList = approvalStatusRepository.findAll();
        assertThat(approvalStatusList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteApprovalStatus() throws Exception {
        // Initialize the database
        approvalStatusRepository.saveAndFlush(approvalStatus);
        int databaseSizeBeforeDelete = approvalStatusRepository.findAll().size();

        // Get the approvalStatus
        restApprovalStatusMockMvc.perform(delete("/api/approval-statuses/{id}", approvalStatus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ApprovalStatus> approvalStatusList = approvalStatusRepository.findAll();
        assertThat(approvalStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ApprovalStatus.class);
        ApprovalStatus approvalStatus1 = new ApprovalStatus();
        approvalStatus1.setId(1L);
        ApprovalStatus approvalStatus2 = new ApprovalStatus();
        approvalStatus2.setId(approvalStatus1.getId());
        assertThat(approvalStatus1).isEqualTo(approvalStatus2);
        approvalStatus2.setId(2L);
        assertThat(approvalStatus1).isNotEqualTo(approvalStatus2);
        approvalStatus1.setId(null);
        assertThat(approvalStatus1).isNotEqualTo(approvalStatus2);
    }
}
