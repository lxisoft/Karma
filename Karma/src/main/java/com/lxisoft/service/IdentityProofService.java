package com.lxisoft.service;

import com.lxisoft.service.dto.IdentityProofDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing IdentityProof.
 */
public interface IdentityProofService {

    /**
     * Save a identityProof.
     *
     * @param identityProofDTO the entity to save
     * @return the persisted entity
     */
    IdentityProofDTO save(IdentityProofDTO identityProofDTO);

    /**
     * Get all the identityProofs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IdentityProofDTO> findAll(Pageable pageable);
    /**
     * Get all the IdentityProofDTO where Owner is null.
     *
     * @return the list of entities
     */
    List<IdentityProofDTO> findAllWhereOwnerIsNull();


    /**
     * Get the "id" identityProof.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IdentityProofDTO> findOne(Long id);

    /**
     * Delete the "id" identityProof.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
