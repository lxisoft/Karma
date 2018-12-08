package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.RegisteredUser;
import com.lxisoft.repository.RegisteredUserRepository;
import com.lxisoft.service.RegisteredUserService;
import com.lxisoft.service.dto.RegisteredUserDTO;
import com.lxisoft.service.mapper.RegisteredUserMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.lxisoft.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RegisteredUserResource REST controller.
 *
 * @see RegisteredUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class RegisteredUserResourceIntTest {

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

    private static final Long DEFAULT_EMOTIONAL_QUOTIENT = 1L;
    private static final Long UPDATED_EMOTIONAL_QUOTIENT = 2L;

    private static final Long DEFAULT_SOCIAL_QUOTIENT = 1L;
    private static final Long UPDATED_SOCIAL_QUOTIENT = 2L;

    private static final Long DEFAULT_HAPPINESS_INDEX = 1L;
    private static final Long UPDATED_HAPPINESS_INDEX = 2L;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private RegisteredUserMapper registeredUserMapper;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRegisteredUserMockMvc;

    private RegisteredUser registeredUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RegisteredUserResource registeredUserResource = new RegisteredUserResource(registeredUserService);
        this.restRegisteredUserMockMvc = MockMvcBuilders.standaloneSetup(registeredUserResource)
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
    public static RegisteredUser createEntity(EntityManager em) {
        RegisteredUser registeredUser = new RegisteredUser()
            .email(DEFAULT_EMAIL)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .rating(DEFAULT_RATING)
            .description(DEFAULT_DESCRIPTION)
            .profession(DEFAULT_PROFESSION)
            .gender(DEFAULT_GENDER)
            .dob(DEFAULT_DOB)
            .bloodGroup(DEFAULT_BLOOD_GROUP)
            .emotionalQuotient(DEFAULT_EMOTIONAL_QUOTIENT)
            .socialQuotient(DEFAULT_SOCIAL_QUOTIENT)
            .happinessIndex(DEFAULT_HAPPINESS_INDEX);
        return registeredUser;
    }

    @Before
    public void initTest() {
        registeredUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createRegisteredUser() throws Exception {
        int databaseSizeBeforeCreate = registeredUserRepository.findAll().size();

        // Create the RegisteredUser
        RegisteredUserDTO registeredUserDTO = registeredUserMapper.toDto(registeredUser);
        restRegisteredUserMockMvc.perform(post("/api/registered-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registeredUserDTO)))
            .andExpect(status().isCreated());

        // Validate the RegisteredUser in the database
        List<RegisteredUser> registeredUserList = registeredUserRepository.findAll();
        assertThat(registeredUserList).hasSize(databaseSizeBeforeCreate + 1);
        RegisteredUser testRegisteredUser = registeredUserList.get(registeredUserList.size() - 1);
        assertThat(testRegisteredUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testRegisteredUser.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testRegisteredUser.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testRegisteredUser.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testRegisteredUser.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRegisteredUser.getProfession()).isEqualTo(DEFAULT_PROFESSION);
        assertThat(testRegisteredUser.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testRegisteredUser.getDob()).isEqualTo(DEFAULT_DOB);
        assertThat(testRegisteredUser.getBloodGroup()).isEqualTo(DEFAULT_BLOOD_GROUP);
        assertThat(testRegisteredUser.getEmotionalQuotient()).isEqualTo(DEFAULT_EMOTIONAL_QUOTIENT);
        assertThat(testRegisteredUser.getSocialQuotient()).isEqualTo(DEFAULT_SOCIAL_QUOTIENT);
        assertThat(testRegisteredUser.getHappinessIndex()).isEqualTo(DEFAULT_HAPPINESS_INDEX);
    }

    @Test
    @Transactional
    public void createRegisteredUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = registeredUserRepository.findAll().size();

        // Create the RegisteredUser with an existing ID
        registeredUser.setId(1L);
        RegisteredUserDTO registeredUserDTO = registeredUserMapper.toDto(registeredUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegisteredUserMockMvc.perform(post("/api/registered-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registeredUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegisteredUser in the database
        List<RegisteredUser> registeredUserList = registeredUserRepository.findAll();
        assertThat(registeredUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRegisteredUsers() throws Exception {
        // Initialize the database
        registeredUserRepository.saveAndFlush(registeredUser);

        // Get all the registeredUserList
        restRegisteredUserMockMvc.perform(get("/api/registered-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(registeredUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].bloodGroup").value(hasItem(DEFAULT_BLOOD_GROUP.toString())))
            .andExpect(jsonPath("$.[*].emotionalQuotient").value(hasItem(DEFAULT_EMOTIONAL_QUOTIENT.intValue())))
            .andExpect(jsonPath("$.[*].socialQuotient").value(hasItem(DEFAULT_SOCIAL_QUOTIENT.intValue())))
            .andExpect(jsonPath("$.[*].happinessIndex").value(hasItem(DEFAULT_HAPPINESS_INDEX.intValue())));
    }
    
    @Test
    @Transactional
    public void getRegisteredUser() throws Exception {
        // Initialize the database
        registeredUserRepository.saveAndFlush(registeredUser);

        // Get the registeredUser
        restRegisteredUserMockMvc.perform(get("/api/registered-users/{id}", registeredUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(registeredUser.getId().intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.bloodGroup").value(DEFAULT_BLOOD_GROUP.toString()))
            .andExpect(jsonPath("$.emotionalQuotient").value(DEFAULT_EMOTIONAL_QUOTIENT.intValue()))
            .andExpect(jsonPath("$.socialQuotient").value(DEFAULT_SOCIAL_QUOTIENT.intValue()))
            .andExpect(jsonPath("$.happinessIndex").value(DEFAULT_HAPPINESS_INDEX.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRegisteredUser() throws Exception {
        // Get the registeredUser
        restRegisteredUserMockMvc.perform(get("/api/registered-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRegisteredUser() throws Exception {
        // Initialize the database
        registeredUserRepository.saveAndFlush(registeredUser);

        int databaseSizeBeforeUpdate = registeredUserRepository.findAll().size();

        // Update the registeredUser
        RegisteredUser updatedRegisteredUser = registeredUserRepository.findById(registeredUser.getId()).get();
        // Disconnect from session so that the updates on updatedRegisteredUser are not directly saved in db
        em.detach(updatedRegisteredUser);
        updatedRegisteredUser
            .email(UPDATED_EMAIL)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .rating(UPDATED_RATING)
            .description(UPDATED_DESCRIPTION)
            .profession(UPDATED_PROFESSION)
            .gender(UPDATED_GENDER)
            .dob(UPDATED_DOB)
            .bloodGroup(UPDATED_BLOOD_GROUP)
            .emotionalQuotient(UPDATED_EMOTIONAL_QUOTIENT)
            .socialQuotient(UPDATED_SOCIAL_QUOTIENT)
            .happinessIndex(UPDATED_HAPPINESS_INDEX);
        RegisteredUserDTO registeredUserDTO = registeredUserMapper.toDto(updatedRegisteredUser);

        restRegisteredUserMockMvc.perform(put("/api/registered-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registeredUserDTO)))
            .andExpect(status().isOk());

        // Validate the RegisteredUser in the database
        List<RegisteredUser> registeredUserList = registeredUserRepository.findAll();
        assertThat(registeredUserList).hasSize(databaseSizeBeforeUpdate);
        RegisteredUser testRegisteredUser = registeredUserList.get(registeredUserList.size() - 1);
        assertThat(testRegisteredUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testRegisteredUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testRegisteredUser.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testRegisteredUser.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testRegisteredUser.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRegisteredUser.getProfession()).isEqualTo(UPDATED_PROFESSION);
        assertThat(testRegisteredUser.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testRegisteredUser.getDob()).isEqualTo(UPDATED_DOB);
        assertThat(testRegisteredUser.getBloodGroup()).isEqualTo(UPDATED_BLOOD_GROUP);
        assertThat(testRegisteredUser.getEmotionalQuotient()).isEqualTo(UPDATED_EMOTIONAL_QUOTIENT);
        assertThat(testRegisteredUser.getSocialQuotient()).isEqualTo(UPDATED_SOCIAL_QUOTIENT);
        assertThat(testRegisteredUser.getHappinessIndex()).isEqualTo(UPDATED_HAPPINESS_INDEX);
    }

    @Test
    @Transactional
    public void updateNonExistingRegisteredUser() throws Exception {
        int databaseSizeBeforeUpdate = registeredUserRepository.findAll().size();

        // Create the RegisteredUser
        RegisteredUserDTO registeredUserDTO = registeredUserMapper.toDto(registeredUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegisteredUserMockMvc.perform(put("/api/registered-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(registeredUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RegisteredUser in the database
        List<RegisteredUser> registeredUserList = registeredUserRepository.findAll();
        assertThat(registeredUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRegisteredUser() throws Exception {
        // Initialize the database
        registeredUserRepository.saveAndFlush(registeredUser);

        int databaseSizeBeforeDelete = registeredUserRepository.findAll().size();

        // Get the registeredUser
        restRegisteredUserMockMvc.perform(delete("/api/registered-users/{id}", registeredUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RegisteredUser> registeredUserList = registeredUserRepository.findAll();
        assertThat(registeredUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisteredUser.class);
        RegisteredUser registeredUser1 = new RegisteredUser();
        registeredUser1.setId(1L);
        RegisteredUser registeredUser2 = new RegisteredUser();
        registeredUser2.setId(registeredUser1.getId());
        assertThat(registeredUser1).isEqualTo(registeredUser2);
        registeredUser2.setId(2L);
        assertThat(registeredUser1).isNotEqualTo(registeredUser2);
        registeredUser1.setId(null);
        assertThat(registeredUser1).isNotEqualTo(registeredUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RegisteredUserDTO.class);
        RegisteredUserDTO registeredUserDTO1 = new RegisteredUserDTO();
        registeredUserDTO1.setId(1L);
        RegisteredUserDTO registeredUserDTO2 = new RegisteredUserDTO();
        assertThat(registeredUserDTO1).isNotEqualTo(registeredUserDTO2);
        registeredUserDTO2.setId(registeredUserDTO1.getId());
        assertThat(registeredUserDTO1).isEqualTo(registeredUserDTO2);
        registeredUserDTO2.setId(2L);
        assertThat(registeredUserDTO1).isNotEqualTo(registeredUserDTO2);
        registeredUserDTO1.setId(null);
        assertThat(registeredUserDTO1).isNotEqualTo(registeredUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(registeredUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(registeredUserMapper.fromId(null)).isNull();
    }
}
