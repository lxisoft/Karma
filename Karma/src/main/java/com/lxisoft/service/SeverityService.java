package com.lxisoft.service;

import com.lxisoft.service.dto.SeverityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Severity.
 */
public interface SeverityService {

    /**
     * Save a severity.
     *
     * @param severityDTO the entity to save
     * @return the persisted entity
     */
    SeverityDTO save(SeverityDTO severityDTO);

    /**
     * Get all the severities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SeverityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" severity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SeverityDTO> findOne(Long id);

    /**
     * Delete the "id" severity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
