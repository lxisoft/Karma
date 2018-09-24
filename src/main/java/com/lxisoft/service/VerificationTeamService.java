package com.lxisoft.service;

import com.lxisoft.service.dto.VerificationTeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing VerificationTeam.
 */
public interface VerificationTeamService {

    /**
     * Save a verificationTeam.
     *
     * @param verificationTeamDTO the entity to save
     * @return the persisted entity
     */
    VerificationTeamDTO save(VerificationTeamDTO verificationTeamDTO);

    /**
     * Get all the verificationTeams.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VerificationTeamDTO> findAll(Pageable pageable);

    /**
     * Get the "id" verificationTeam.
     *
     * @param id the id of the entity
     * @return the entity
     */
    VerificationTeamDTO findOne(Long id);

    /**
     * Delete the "id" verificationTeam.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
