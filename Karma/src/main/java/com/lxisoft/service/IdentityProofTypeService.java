package com.lxisoft.service;

import com.lxisoft.service.dto.IdentityProofTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing IdentityProofType.
 */
public interface IdentityProofTypeService {

    /**
     * Save a identityProofType.
     *
     * @param identityProofTypeDTO the entity to save
     * @return the persisted entity
     */
    IdentityProofTypeDTO save(IdentityProofTypeDTO identityProofTypeDTO);

    /**
     * Get all the identityProofTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IdentityProofTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" identityProofType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IdentityProofTypeDTO> findOne(Long id);

    /**
     * Delete the "id" identityProofType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
