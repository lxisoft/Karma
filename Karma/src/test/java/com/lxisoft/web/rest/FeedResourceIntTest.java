package com.lxisoft.web.rest;

import com.lxisoft.KarmaApp;

import com.lxisoft.domain.Feed;
import com.lxisoft.repository.FeedRepository;
import com.lxisoft.service.FeedService;
import com.lxisoft.service.dto.FeedDTO;
import com.lxisoft.service.mapper.FeedMapper;
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
 * Test class for the FeedResource REST controller.
 *
 * @see FeedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KarmaApp.class)
public class FeedResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_REFERENCE_ID = 1L;
    private static final Long UPDATED_REFERENCE_ID = 2L;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private FeedMapper feedMapper;

    @Autowired
    private FeedService feedService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFeedMockMvc;

    private Feed feed;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FeedResource feedResource = new FeedResource(feedService);
        this.restFeedMockMvc = MockMvcBuilders.standaloneSetup(feedResource)
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
    public static Feed createEntity(EntityManager em) {
        Feed feed = new Feed()
            .title(DEFAULT_TITLE)
            .type(DEFAULT_TYPE)
            .date(DEFAULT_DATE)
            .referenceId(DEFAULT_REFERENCE_ID);
        return feed;
    }

    @Before
    public void initTest() {
        feed = createEntity(em);
    }

    @Test
    @Transactional
    public void createFeed() throws Exception {
        int databaseSizeBeforeCreate = feedRepository.findAll().size();

        // Create the Feed
        FeedDTO feedDTO = feedMapper.toDto(feed);
        restFeedMockMvc.perform(post("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isCreated());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeCreate + 1);
        Feed testFeed = feedList.get(feedList.size() - 1);
        assertThat(testFeed.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testFeed.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFeed.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testFeed.getReferenceId()).isEqualTo(DEFAULT_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void createFeedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = feedRepository.findAll().size();

        // Create the Feed with an existing ID
        feed.setId(1L);
        FeedDTO feedDTO = feedMapper.toDto(feed);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFeedMockMvc.perform(post("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFeeds() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get all the feedList
        restFeedMockMvc.perform(get("/api/feeds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(feed.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].referenceId").value(hasItem(DEFAULT_REFERENCE_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getFeed() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        // Get the feed
        restFeedMockMvc.perform(get("/api/feeds/{id}", feed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(feed.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.referenceId").value(DEFAULT_REFERENCE_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFeed() throws Exception {
        // Get the feed
        restFeedMockMvc.perform(get("/api/feeds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeed() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        int databaseSizeBeforeUpdate = feedRepository.findAll().size();

        // Update the feed
        Feed updatedFeed = feedRepository.findById(feed.getId()).get();
        // Disconnect from session so that the updates on updatedFeed are not directly saved in db
        em.detach(updatedFeed);
        updatedFeed
            .title(UPDATED_TITLE)
            .type(UPDATED_TYPE)
            .date(UPDATED_DATE)
            .referenceId(UPDATED_REFERENCE_ID);
        FeedDTO feedDTO = feedMapper.toDto(updatedFeed);

        restFeedMockMvc.perform(put("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isOk());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeUpdate);
        Feed testFeed = feedList.get(feedList.size() - 1);
        assertThat(testFeed.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testFeed.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFeed.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testFeed.getReferenceId()).isEqualTo(UPDATED_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingFeed() throws Exception {
        int databaseSizeBeforeUpdate = feedRepository.findAll().size();

        // Create the Feed
        FeedDTO feedDTO = feedMapper.toDto(feed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFeedMockMvc.perform(put("/api/feeds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(feedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Feed in the database
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFeed() throws Exception {
        // Initialize the database
        feedRepository.saveAndFlush(feed);

        int databaseSizeBeforeDelete = feedRepository.findAll().size();

        // Get the feed
        restFeedMockMvc.perform(delete("/api/feeds/{id}", feed.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Feed> feedList = feedRepository.findAll();
        assertThat(feedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Feed.class);
        Feed feed1 = new Feed();
        feed1.setId(1L);
        Feed feed2 = new Feed();
        feed2.setId(feed1.getId());
        assertThat(feed1).isEqualTo(feed2);
        feed2.setId(2L);
        assertThat(feed1).isNotEqualTo(feed2);
        feed1.setId(null);
        assertThat(feed1).isNotEqualTo(feed2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FeedDTO.class);
        FeedDTO feedDTO1 = new FeedDTO();
        feedDTO1.setId(1L);
        FeedDTO feedDTO2 = new FeedDTO();
        assertThat(feedDTO1).isNotEqualTo(feedDTO2);
        feedDTO2.setId(feedDTO1.getId());
        assertThat(feedDTO1).isEqualTo(feedDTO2);
        feedDTO2.setId(2L);
        assertThat(feedDTO1).isNotEqualTo(feedDTO2);
        feedDTO1.setId(null);
        assertThat(feedDTO1).isNotEqualTo(feedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(feedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(feedMapper.fromId(null)).isNull();
    }
}
