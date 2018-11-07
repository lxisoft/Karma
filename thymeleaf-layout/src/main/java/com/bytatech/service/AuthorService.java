package com.bytatech.service;

import com.bytatech.service.dto.AuthorDTO;
import java.util.List;

/**
 * Service Interface for managing Author.
 */
public interface AuthorService {

    /**
     * Save a author.
     *
     * @param authorDTO the entity to save
     * @return the persisted entity
     */
    AuthorDTO save(AuthorDTO authorDTO);

    /**
     * Get all the authors.
     *
     * @return the list of entities
     */
    List<AuthorDTO> findAll();

    /**
     * Get the "id" author.
     *
     * @param id the id of the entity
     * @return the entity
     */
    AuthorDTO findOne(Long id);

    /**
     * Delete the "id" author.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
