package com.lxisoft.service.impl;

import com.lxisoft.service.FeedService;
import com.lxisoft.domain.Feed;
import com.lxisoft.repository.FeedRepository;
import com.lxisoft.service.dto.FeedDTO;
import com.lxisoft.service.mapper.FeedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Feed.
 */
@Service
@Transactional
public class FeedServiceImpl implements FeedService {

    private final Logger log = LoggerFactory.getLogger(FeedServiceImpl.class);

    private final FeedRepository feedRepository;

    private final FeedMapper feedMapper;

    public FeedServiceImpl(FeedRepository feedRepository, FeedMapper feedMapper) {
        this.feedRepository = feedRepository;
        this.feedMapper = feedMapper;
    }

    /**
     * Save a feed.
     *
     * @param feedDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FeedDTO save(FeedDTO feedDTO) {
        log.debug("Request to save Feed : {}", feedDTO);

        Feed feed = feedMapper.toEntity(feedDTO);
        feed = feedRepository.save(feed);
        return feedMapper.toDto(feed);
    }

    /**
     * Get all the feeds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FeedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Feeds");
        return feedRepository.findAll(pageable)
            .map(feedMapper::toDto);
    }


    /**
     * Get one feed by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FeedDTO> findOne(Long id) {
        log.debug("Request to get Feed : {}", id);
        return feedRepository.findById(id)
            .map(feedMapper::toDto);
    }

    /**
     * Delete the feed by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Feed : {}", id);
        feedRepository.deleteById(id);
    }
}
