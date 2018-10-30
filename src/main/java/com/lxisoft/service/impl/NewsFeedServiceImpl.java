package com.lxisoft.service.impl;

import com.lxisoft.service.NewsFeedService;
import com.lxisoft.domain.NewsFeed;
import com.lxisoft.repository.NewsFeedRepository;
import com.lxisoft.service.dto.NewsFeedDTO;
import com.lxisoft.service.mapper.NewsFeedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NewsFeed.
 */
@Service
@Transactional
public class NewsFeedServiceImpl implements NewsFeedService {

    private final Logger log = LoggerFactory.getLogger(NewsFeedServiceImpl.class);

    private final NewsFeedRepository newsFeedRepository;

    private final NewsFeedMapper newsFeedMapper;

    public NewsFeedServiceImpl(NewsFeedRepository newsFeedRepository, NewsFeedMapper newsFeedMapper) {
        this.newsFeedRepository = newsFeedRepository;
        this.newsFeedMapper = newsFeedMapper;
    }

    /**
     * Save a newsFeed.
     *
     * @param newsFeedDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NewsFeedDTO save(NewsFeedDTO newsFeedDTO) {
        log.debug("Request to save NewsFeed : {}", newsFeedDTO);
        NewsFeed newsFeed = newsFeedMapper.toEntity(newsFeedDTO);
        newsFeed = newsFeedRepository.save(newsFeed);
        return newsFeedMapper.toDto(newsFeed);
    }

    /**
     * Get all the newsFeeds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NewsFeedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NewsFeeds");
        return newsFeedRepository.findAll(pageable)
            .map(newsFeedMapper::toDto);
    }


    /**
     * Get one newsFeed by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NewsFeedDTO> findOne(Long id) {
        log.debug("Request to get NewsFeed : {}", id);
        return newsFeedRepository.findById(id)
            .map(newsFeedMapper::toDto);
    }

    /**
     * Delete the newsFeed by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NewsFeed : {}", id);
        newsFeedRepository.deleteById(id);
    }
}
