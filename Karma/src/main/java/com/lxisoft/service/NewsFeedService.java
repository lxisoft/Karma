package com.lxisoft.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.service.dto.NewsFeedDTO;

/**
 * Service Interface for managing NewsFeed.
 */
public interface NewsFeedService {

	/**
	 * Save a newsFeed.
	 *
	 * @param newsFeedDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	NewsFeedDTO save(NewsFeedDTO newsFeedDTO);

	/**
	 * Get all the newsFeeds.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<NewsFeedDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" newsFeed.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	Optional<NewsFeedDTO> findOne(Long id);

	/**
	 * Delete the "id" newsFeed.
	 *
	 * @param id
	 *            the id of the entity
	 */
	void delete(Long id);

	public Page<NewsFeedDTO> findAllNewsFeeds(Pageable pageable);

	public NewsFeedDTO saveNewsFeed(NewsFeedDTO newsFeedDTO)
			throws URISyntaxException, IllegalStateException, IOException;
}
