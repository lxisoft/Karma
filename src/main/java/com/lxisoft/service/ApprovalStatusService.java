package com.lxisoft.service;

import com.lxisoft.service.dto.ApprovalStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ApprovalStatus.
 */
public interface ApprovalStatusService {

    /**
     * Save a approvalStatus.
     *
     * @param approvalStatusDTO the entity to save
     * @return the persisted entity
     */
    ApprovalStatusDTO save(ApprovalStatusDTO approvalStatusDTO);

    /**
     * Get all the approvalStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApprovalStatusDTO> findAll(Pageable pageable);

    /**
     * Get the "id" approvalStatus.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ApprovalStatusDTO findOne(Long id);

    /**
     * Delete the "id" approvalStatus.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
