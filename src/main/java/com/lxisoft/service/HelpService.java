package com.lxisoft.service;

import com.lxisoft.service.dto.HelpDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Help.
 */
public interface HelpService {

    /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * @return the persisted entity
     */
    HelpDTO save(HelpDTO helpDTO);

    /**
     * Get all the helps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HelpDTO> findAll(Pageable pageable);

    /**
     * Get the "id" help.
     *
     * @param id the id of the entity
     * @return the entity
     */
    HelpDTO findOne(Long id);

    /**
     * Delete the "id" help.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
