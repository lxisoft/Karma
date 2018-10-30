package com.lxisoft.service;

import com.lxisoft.service.dto.ViolationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Violation.
 */
public interface ViolationService {

    /**
     * Save a violation.
     *
     * @param violationDTO the entity to save
     * @return the persisted entity
     */
    ViolationDTO save(ViolationDTO violationDTO);

    /**
     * Get all the violations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ViolationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" violation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ViolationDTO> findOne(Long id);

    /**
     * Delete the "id" violation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
