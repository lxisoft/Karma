package com.lxisoft.service;

import com.lxisoft.service.dto.RegisteredUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RegisteredUser.
 */
public interface RegisteredUserService {

    /**
     * Save a registeredUser.
     *
     * @param registeredUserDTO the entity to save
     * @return the persisted entity
     */
    RegisteredUserDTO save(RegisteredUserDTO registeredUserDTO);

    /**
     * Get all the registeredUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RegisteredUserDTO> findAll(Pageable pageable);


    /**
     * Get the "id" registeredUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RegisteredUserDTO> findOne(Long id);

    /**
     * Delete the "id" registeredUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
