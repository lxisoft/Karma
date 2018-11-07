package com.bytatech.service;

import com.bytatech.service.dto.PublisherDTO;
import java.util.List;

/**
 * Service Interface for managing Publisher.
 */
public interface PublisherService {

    /**
     * Save a publisher.
     *
     * @param publisherDTO the entity to save
     * @return the persisted entity
     */
    PublisherDTO save(PublisherDTO publisherDTO);

    /**
     * Get all the publishers.
     *
     * @return the list of entities
     */
    List<PublisherDTO> findAll();

    /**
     * Get the "id" publisher.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PublisherDTO findOne(Long id);

    /**
     * Delete the "id" publisher.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
