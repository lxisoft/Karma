package com.lxisoft.service;

import com.lxisoft.service.dto.UserCheckDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UserCheck.
 */
public interface UserCheckService {

    /**
     * Save a userCheck.
     *
     * @param userCheckDTO the entity to save
     * @return the persisted entity
     */
    UserCheckDTO save(UserCheckDTO userCheckDTO);

    /**
     * Get all the userChecks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UserCheckDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userCheck.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UserCheckDTO> findOne(Long id);

    /**
     * Delete the "id" userCheck.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get one userCheck by Category,CheckedNeedId,CheckedUserId.
     *
     * @param Category the Category of the entity
     * 
     * @param CheckedNeedId the CheckedNeedId of the entity
     * 
     * @param CheckedUserId the CheckedUserId of the entity
     * 
     * @return the entity
     */
    Optional<UserCheckDTO> findByCategoryAndCheckedNeedIdAndCheckedUserId(String category,Long checkedNeedId,Long checkedUserId);
}
