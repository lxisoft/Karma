package com.lxisoft.service;

import com.lxisoft.service.dto.ViolationDTO;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
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
     * @throws IOException 
     */
    ViolationDTO save(ViolationDTO violationDTO) throws IOException;

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
    
    /**
     * Get all the violations by anonymous user type.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ViolationDTO> findViolationByIsAnonymous(Pageable pageable,Boolean isAnonymous);

    /**
     * Get all the violations by after date.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ViolationDTO> findViolationByDateAfter(Pageable pageable,Instant date);

	Page<ViolationDTO> findViolationByDateBefore(Pageable pageable, Instant date);

	Page<ViolationDTO> findViolationByDateBetween(Pageable pageable, Instant startDate, Instant endDate);

   
}