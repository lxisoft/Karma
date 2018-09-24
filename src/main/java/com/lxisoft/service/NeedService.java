package com.lxisoft.service;

import com.lxisoft.service.dto.NeedDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Need.
 */
public interface NeedService {

    /**
     * Save a need.
     *
     * @param needDTO the entity to save
     * @return the persisted entity
     */
    NeedDTO save(NeedDTO needDTO);

    /**
     * Get all the needs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NeedDTO> findAll(Pageable pageable);

    /**
     * Get the "id" need.
     *
     * @param id the id of the entity
     * @return the entity
     */
    NeedDTO findOne(Long id);

    /**
     * Delete the "id" need.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
