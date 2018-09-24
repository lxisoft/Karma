package com.lxisoft.service;

import com.lxisoft.service.dto.LoggedUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing LoggedUser.
 */
public interface LoggedUserService {

    /**
     * Save a loggedUser.
     *
     * @param loggedUserDTO the entity to save
     * @return the persisted entity
     */
    LoggedUserDTO save(LoggedUserDTO loggedUserDTO);

    /**
     * Get all the loggedUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LoggedUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" loggedUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    LoggedUserDTO findOne(Long id);

    /**
     * Delete the "id" loggedUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
