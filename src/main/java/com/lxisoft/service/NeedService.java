package com.lxisoft.service;

import com.lxisoft.service.dto.NeedDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

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
     * Get all the Need with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NeedDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" need.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NeedDTO> findOne(Long id);

    /**
     * Delete the "id" need.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * @return the list of approvedstatus entities
     */
    
    Page<NeedDTO> findAllNeedsByApprovedStatus(Pageable pageable, String approvalStatus);
    
    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * @return the list of approvedstatus entities
     */
	Page<NeedDTO> findAllNeedsByApprovalStatusId(Pageable pageable,Long approvalStatusId);
}
