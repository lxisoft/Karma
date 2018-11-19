package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.UserCheck;
import com.lxisoft.repository.UserCheckRepository;
import com.lxisoft.service.UserCheckService;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.service.mapper.UserCheckMapper;
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
 * Test class for the UserCheckResource REST controller.
 *
 * @see UserCheckResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class UserCheckResourceIntTest {

    private static final String DEFAULT_VOTE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VOTE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    @Autowired
    private UserCheckRepository userCheckRepository;

    @Autowired
    private UserCheckMapper userCheckMapper;

    @Autowired
    private UserCheckService userCheckService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUserCheckMockMvc;

    private UserCheck userCheck;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserCheckResource userCheckResource = new UserCheckResource(userCheckService);
        this.restUserCheckMockMvc = MockMvcBuilders.standaloneSetup(userCheckResource)
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
    public static UserCheck createEntity(EntityManager em) {
        UserCheck userCheck = new UserCheck()
            .voteType(DEFAULT_VOTE_TYPE)
            .category(DEFAULT_CATEGORY);
        return userCheck;
    }

    @Before
    public void initTest() {
        userCheck = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserCheck() throws Exception {
        int databaseSizeBeforeCreate = userCheckRepository.findAll().size();

        // Create the UserCheck
        UserCheckDTO userCheckDTO = userCheckMapper.toDto(userCheck);
        restUserCheckMockMvc.perform(post("/api/user-checks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCheckDTO)))
            .andExpect(status().isCreated());

        // Validate the UserCheck in the database
        List<UserCheck> userCheckList = userCheckRepository.findAll();
        assertThat(userCheckList).hasSize(databaseSizeBeforeCreate + 1);
        UserCheck testUserCheck = userCheckList.get(userCheckList.size() - 1);
        assertThat(testUserCheck.getVoteType()).isEqualTo(DEFAULT_VOTE_TYPE);
        assertThat(testUserCheck.getCategory()).isEqualTo(DEFAULT_CATEGORY);
    }

    @Test
    @Transactional
    public void createUserCheckWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userCheckRepository.findAll().size();

        // Create the UserCheck with an existing ID
        userCheck.setId(1L);
        UserCheckDTO userCheckDTO = userCheckMapper.toDto(userCheck);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserCheckMockMvc.perform(post("/api/user-checks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCheckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserCheck in the database
        List<UserCheck> userCheckList = userCheckRepository.findAll();
        assertThat(userCheckList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUserChecks() throws Exception {
        // Initialize the database
        userCheckRepository.saveAndFlush(userCheck);

        // Get all the userCheckList
        restUserCheckMockMvc.perform(get("/api/user-checks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userCheck.getId().intValue())))
            .andExpect(jsonPath("$.[*].voteType").value(hasItem(DEFAULT_VOTE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())));
    }
    
    @Test
    @Transactional
    public void getUserCheck() throws Exception {
        // Initialize the database
        userCheckRepository.saveAndFlush(userCheck);

        // Get the userCheck
        restUserCheckMockMvc.perform(get("/api/user-checks/{id}", userCheck.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userCheck.getId().intValue()))
            .andExpect(jsonPath("$.voteType").value(DEFAULT_VOTE_TYPE.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserCheck() throws Exception {
        // Get the userCheck
        restUserCheckMockMvc.perform(get("/api/user-checks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserCheck() throws Exception {
        // Initialize the database
        userCheckRepository.saveAndFlush(userCheck);

        int databaseSizeBeforeUpdate = userCheckRepository.findAll().size();

        // Update the userCheck
        UserCheck updatedUserCheck = userCheckRepository.findById(userCheck.getId()).get();
        // Disconnect from session so that the updates on updatedUserCheck are not directly saved in db
        em.detach(updatedUserCheck);
        updatedUserCheck
            .voteType(UPDATED_VOTE_TYPE)
            .category(UPDATED_CATEGORY);
        UserCheckDTO userCheckDTO = userCheckMapper.toDto(updatedUserCheck);

        restUserCheckMockMvc.perform(put("/api/user-checks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCheckDTO)))
            .andExpect(status().isOk());

        // Validate the UserCheck in the database
        List<UserCheck> userCheckList = userCheckRepository.findAll();
        assertThat(userCheckList).hasSize(databaseSizeBeforeUpdate);
        UserCheck testUserCheck = userCheckList.get(userCheckList.size() - 1);
        assertThat(testUserCheck.getVoteType()).isEqualTo(UPDATED_VOTE_TYPE);
        assertThat(testUserCheck.getCategory()).isEqualTo(UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    public void updateNonExistingUserCheck() throws Exception {
        int databaseSizeBeforeUpdate = userCheckRepository.findAll().size();

        // Create the UserCheck
        UserCheckDTO userCheckDTO = userCheckMapper.toDto(userCheck);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserCheckMockMvc.perform(put("/api/user-checks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userCheckDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UserCheck in the database
        List<UserCheck> userCheckList = userCheckRepository.findAll();
        assertThat(userCheckList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserCheck() throws Exception {
        // Initialize the database
        userCheckRepository.saveAndFlush(userCheck);

        int databaseSizeBeforeDelete = userCheckRepository.findAll().size();

        // Get the userCheck
        restUserCheckMockMvc.perform(delete("/api/user-checks/{id}", userCheck.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UserCheck> userCheckList = userCheckRepository.findAll();
        assertThat(userCheckList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCheck.class);
        UserCheck userCheck1 = new UserCheck();
        userCheck1.setId(1L);
        UserCheck userCheck2 = new UserCheck();
        userCheck2.setId(userCheck1.getId());
        assertThat(userCheck1).isEqualTo(userCheck2);
        userCheck2.setId(2L);
        assertThat(userCheck1).isNotEqualTo(userCheck2);
        userCheck1.setId(null);
        assertThat(userCheck1).isNotEqualTo(userCheck2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserCheckDTO.class);
        UserCheckDTO userCheckDTO1 = new UserCheckDTO();
        userCheckDTO1.setId(1L);
        UserCheckDTO userCheckDTO2 = new UserCheckDTO();
        assertThat(userCheckDTO1).isNotEqualTo(userCheckDTO2);
        userCheckDTO2.setId(userCheckDTO1.getId());
        assertThat(userCheckDTO1).isEqualTo(userCheckDTO2);
        userCheckDTO2.setId(2L);
        assertThat(userCheckDTO1).isNotEqualTo(userCheckDTO2);
        userCheckDTO1.setId(null);
        assertThat(userCheckDTO1).isNotEqualTo(userCheckDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(userCheckMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(userCheckMapper.fromId(null)).isNull();
    }
}
