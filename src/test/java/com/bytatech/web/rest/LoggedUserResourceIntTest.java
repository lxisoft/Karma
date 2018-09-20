package com.bytatech.web.rest;

import com.bytatech.KarmaApp;

import com.bytatech.domain.LoggedUser;
import com.bytatech.repository.LoggedUserRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.bytatech.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LoggedUserResource REST controller.
 *
 * @see LoggedUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class LoggedUserResourceIntTest {

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_RATING = 1L;
    private static final Long UPDATED_RATING = 2L;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BLOOD_GROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOOD_GROUP = "BBBBBBBBBB";

    @Autowired
    private LoggedUserRepository loggedUserRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLoggedUserMockMvc;

    private LoggedUser loggedUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LoggedUserResource loggedUserResource = new LoggedUserResource(loggedUserRepository);
        this.restLoggedUserMockMvc = MockMvcBuilders.standaloneSetup(loggedUserResource)
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
    public static LoggedUser createEntity(EntityManager em) {
        LoggedUser loggedUser = new LoggedUser()
            .email(DEFAULT_EMAIL)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .rating(DEFAULT_RATING)
            .description(DEFAULT_DESCRIPTION)
            .profession(DEFAULT_PROFESSION)
            .gender(DEFAULT_GENDER)
            .dob(DEFAULT_DOB)
            .bloodGroup(DEFAULT_BLOOD_GROUP);
        return loggedUser;
    }

    @Before
    public void initTest() {
        loggedUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoggedUser() throws Exception {
        int databaseSizeBeforeCreate = loggedUserRepository.findAll().size();

        // Create the LoggedUser
        restLoggedUserMockMvc.perform(post("/api/logged-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loggedUser)))
            .andExpect(status().isCreated());

        // Validate the LoggedUser in the database
        List<LoggedUser> loggedUserList = loggedUserRepository.findAll();
        assertThat(loggedUserList).hasSize(databaseSizeBeforeCreate + 1);
        LoggedUser testLoggedUser = loggedUserList.get(loggedUserList.size() - 1);
        assertThat(testLoggedUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testLoggedUser.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testLoggedUser.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testLoggedUser.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testLoggedUser.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLoggedUser.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testLoggedUser.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testLoggedUser.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testLoggedUser.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void createLoggedUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loggedUserRepository.findAll().size();

        // Create the LoggedUser with an existing ID
        loggedUser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoggedUserMockMvc.perform(post("/api/logged-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loggedUser)))
            .andExpect(status().isBadRequest());

        // Validate the LoggedUser in the database
        List<LoggedUser> loggedUserList = loggedUserRepository.findAll();
        assertThat(loggedUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLoggedUsers() throws Exception {
        // Initialize the database
        loggedUserRepository.saveAndFlush(loggedUser);

        // Get all the loggedUserList
        restLoggedUserMockMvc.perform(get("/api/logged-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loggedUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())));
    }

    @Test
    @Transactional
    public void getLoggedUser() throws Exception {
        // Initialize the database
        loggedUserRepository.saveAndFlush(loggedUser);

        // Get the loggedUser
        restLoggedUserMockMvc.perform(get("/api/logged-users/{id}", loggedUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loggedUser.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLoggedUser() throws Exception {
        // Get the loggedUser
        restLoggedUserMockMvc.perform(get("/api/logged-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoggedUser() throws Exception {
        // Initialize the database
        loggedUserRepository.saveAndFlush(loggedUser);
        int databaseSizeBeforeUpdate = loggedUserRepository.findAll().size();

        // Update the loggedUser
        LoggedUser updatedLoggedUser = loggedUserRepository.findOne(loggedUser.getId());
        // Disconnect from session so that the updates on updatedLoggedUser are not directly saved in db
        em.detach(updatedLoggedUser);
        updatedLoggedUser
            .email(UPDATED_EMAIL)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .rating(UPDATED_RATING)
            .description(UPDATED_DESCRIPTION)
            .profession(UPDATED_PROFESSION)
            .gender(UPDATED_GENDER)
            .dob(UPDATED_DOB)
            .bloodGroup(UPDATED_BLOOD_GROUP);

        restLoggedUserMockMvc.perform(put("/api/logged-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoggedUser)))
            .andExpect(status().isOk());

        // Validate the LoggedUser in the database
        List<LoggedUser> loggedUserList = loggedUserRepository.findAll();
        assertThat(loggedUserList).hasSize(databaseSizeBeforeUpdate);
        LoggedUser testLoggedUser = loggedUserList.get(loggedUserList.size() - 1);
        assertThat(testLoggedUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testLoggedUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testLoggedUser.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testLoggedUser.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testLoggedUser.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLoggedUser.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testLoggedUser.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testLoggedUser.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testLoggedUser.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
    }

    @Test
    @Transactional
    public void updateNonExistingLoggedUser() throws Exception {
        int databaseSizeBeforeUpdate = loggedUserRepository.findAll().size();

        // Create the LoggedUser

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLoggedUserMockMvc.perform(put("/api/logged-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loggedUser)))
            .andExpect(status().isCreated());

        // Validate the LoggedUser in the database
        List<LoggedUser> loggedUserList = loggedUserRepository.findAll();
        assertThat(loggedUserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLoggedUser() throws Exception {
        // Initialize the database
        loggedUserRepository.saveAndFlush(loggedUser);
        int databaseSizeBeforeDelete = loggedUserRepository.findAll().size();

        // Get the loggedUser
        restLoggedUserMockMvc.perform(delete("/api/logged-users/{id}", loggedUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LoggedUser> loggedUserList = loggedUserRepository.findAll();
        assertThat(loggedUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LoggedUser.class);
        LoggedUser loggedUser1 = new LoggedUser();
        loggedUser1.setId(1L);
        LoggedUser loggedUser2 = new LoggedUser();
        loggedUser2.setId(loggedUser1.getId());
        assertThat(loggedUser1).isEqualTo(loggedUser2);
        loggedUser2.setId(2L);
        assertThat(loggedUser1).isNotEqualTo(loggedUser2);
        loggedUser1.setId(null);
        assertThat(loggedUser1).isNotEqualTo(loggedUser2);
    }
}
