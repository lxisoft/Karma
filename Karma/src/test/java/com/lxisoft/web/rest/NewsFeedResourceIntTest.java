package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.NewsFeed;
import com.lxisoft.repository.NewsFeedRepository;
import com.lxisoft.service.NewsFeedService;
import com.lxisoft.service.dto.NewsFeedDTO;
import com.lxisoft.service.mapper.NewsFeedMapper;
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
 * Test class for the NewsFeedResource REST controller.
 *
 * @see NewsFeedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class NewsFeedResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private NewsFeedRepository newsFeedRepository;

    @Autowired
    private NewsFeedMapper newsFeedMapper;
    
    @Autowired
    private NewsFeedService newsFeedService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNewsFeedMockMvc;

    private NewsFeed newsFeed;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NewsFeedResource newsFeedResource = new NewsFeedResource(newsFeedService);
        this.restNewsFeedMockMvc = MockMvcBuilders.standaloneSetup(newsFeedResource)
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
    public static NewsFeed createEntity(EntityManager em) {
        NewsFeed newsFeed = new NewsFeed()
            .description(DEFAULT_DESCRIPTION)
            .date(DEFAULT_DATE);
        return newsFeed;
    }

    @Before
    public void initTest() {
        newsFeed = createEntity(em);
    }

    @Test
    @Transactional
    public void createNewsFeed() throws Exception {
        int databaseSizeBeforeCreate = newsFeedRepository.findAll().size();

        // Create the NewsFeed
        NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);
        restNewsFeedMockMvc.perform(post("/api/news-feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
            .andExpect(status().isCreated());

        // Validate the NewsFeed in the database
        List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
        assertThat(newsFeedList).hasSize(databaseSizeBeforeCreate + 1);
        NewsFeed testNewsFeed = newsFeedList.get(newsFeedList.size() - 1);
        assertThat(testNewsFeed.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testNewsFeed.getDate()).isEqualTo(DEFAULT_DATE);
    }

    @Test
    @Transactional
    public void createNewsFeedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = newsFeedRepository.findAll().size();

        // Create the NewsFeed with an existing ID
        newsFeed.setId(1L);
        NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNewsFeedMockMvc.perform(post("/api/news-feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NewsFeed in the database
        List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
        assertThat(newsFeedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNewsFeeds() throws Exception {
        // Initialize the database
        newsFeedRepository.saveAndFlush(newsFeed);

        // Get all the newsFeedList
        restNewsFeedMockMvc.perform(get("/api/news-feeds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newsFeed.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getNewsFeed() throws Exception {
        // Initialize the database
        newsFeedRepository.saveAndFlush(newsFeed);

        // Get the newsFeed
        restNewsFeedMockMvc.perform(get("/api/news-feeds/{id}", newsFeed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(newsFeed.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNewsFeed() throws Exception {
        // Get the newsFeed
        restNewsFeedMockMvc.perform(get("/api/news-feeds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNewsFeed() throws Exception {
        // Initialize the database
        newsFeedRepository.saveAndFlush(newsFeed);

        int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();

        // Update the newsFeed
        NewsFeed updatedNewsFeed = newsFeedRepository.findById(newsFeed.getId()).get();
        // Disconnect from session so that the updates on updatedNewsFeed are not directly saved in db
        em.detach(updatedNewsFeed);
        updatedNewsFeed
            .description(UPDATED_DESCRIPTION)
            .date(UPDATED_DATE);
        NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(updatedNewsFeed);

        restNewsFeedMockMvc.perform(put("/api/news-feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
            .andExpect(status().isOk());

        // Validate the NewsFeed in the database
        List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
        assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);
        NewsFeed testNewsFeed = newsFeedList.get(newsFeedList.size() - 1);
        assertThat(testNewsFeed.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testNewsFeed.getDate()).isEqualTo(UPDATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingNewsFeed() throws Exception {
        int databaseSizeBeforeUpdate = newsFeedRepository.findAll().size();

        // Create the NewsFeed
        NewsFeedDTO newsFeedDTO = newsFeedMapper.toDto(newsFeed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNewsFeedMockMvc.perform(put("/api/news-feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newsFeedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NewsFeed in the database
        List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
        assertThat(newsFeedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNewsFeed() throws Exception {
        // Initialize the database
        newsFeedRepository.saveAndFlush(newsFeed);

        int databaseSizeBeforeDelete = newsFeedRepository.findAll().size();

        // Get the newsFeed
        restNewsFeedMockMvc.perform(delete("/api/news-feeds/{id}", newsFeed.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NewsFeed> newsFeedList = newsFeedRepository.findAll();
        assertThat(newsFeedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewsFeed.class);
        NewsFeed newsFeed1 = new NewsFeed();
        newsFeed1.setId(1L);
        NewsFeed newsFeed2 = new NewsFeed();
        newsFeed2.setId(newsFeed1.getId());
        assertThat(newsFeed1).isEqualTo(newsFeed2);
        newsFeed2.setId(2L);
        assertThat(newsFeed1).isNotEqualTo(newsFeed2);
        newsFeed1.setId(null);
        assertThat(newsFeed1).isNotEqualTo(newsFeed2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewsFeedDTO.class);
        NewsFeedDTO newsFeedDTO1 = new NewsFeedDTO();
        newsFeedDTO1.setId(1L);
        NewsFeedDTO newsFeedDTO2 = new NewsFeedDTO();
        assertThat(newsFeedDTO1).isNotEqualTo(newsFeedDTO2);
        newsFeedDTO2.setId(newsFeedDTO1.getId());
        assertThat(newsFeedDTO1).isEqualTo(newsFeedDTO2);
        newsFeedDTO2.setId(2L);
        assertThat(newsFeedDTO1).isNotEqualTo(newsFeedDTO2);
        newsFeedDTO1.setId(null);
        assertThat(newsFeedDTO1).isNotEqualTo(newsFeedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(newsFeedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(newsFeedMapper.fromId(null)).isNull();
    }
}
