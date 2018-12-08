package com.lxisoft.service.impl;

import com.lxisoft.service.VerificationTeamService;
import com.lxisoft.domain.VerificationTeam;
import com.lxisoft.repository.VerificationTeamRepository;
import com.lxisoft.service.dto.VerificationTeamDTO;
import com.lxisoft.service.mapper.VerificationTeamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing VerificationTeam.
 */
@Service
@Transactional
public class VerificationTeamServiceImpl implements VerificationTeamService {

    private final Logger log = LoggerFactory.getLogger(VerificationTeamServiceImpl.class);

    private final VerificationTeamRepository verificationTeamRepository;

    private final VerificationTeamMapper verificationTeamMapper;

    public VerificationTeamServiceImpl(VerificationTeamRepository verificationTeamRepository, VerificationTeamMapper verificationTeamMapper) {
        this.verificationTeamRepository = verificationTeamRepository;
        this.verificationTeamMapper = verificationTeamMapper;
    }

    /**
     * Save a verificationTeam.
     *
     * @param verificationTeamDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VerificationTeamDTO save(VerificationTeamDTO verificationTeamDTO) {
        log.debug("Request to save VerificationTeam : {}", verificationTeamDTO);

        VerificationTeam verificationTeam = verificationTeamMapper.toEntity(verificationTeamDTO);
        verificationTeam = verificationTeamRepository.save(verificationTeam);
        return verificationTeamMapper.toDto(verificationTeam);
    }

    /**
     * Get all the verificationTeams.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VerificationTeamDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VerificationTeams");
        return verificationTeamRepository.findAll(pageable)
            .map(verificationTeamMapper::toDto);
    }

    /**
     * Get all the VerificationTeam with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<VerificationTeamDTO> findAllWithEagerRelationships(Pageable pageable) {
        return verificationTeamRepository.findAllWithEagerRelationships(pageable).map(verificationTeamMapper::toDto);
    }
    

    /**
     * Get one verificationTeam by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VerificationTeamDTO> findOne(Long id) {
        log.debug("Request to get VerificationTeam : {}", id);
        return verificationTeamRepository.findOneWithEagerRelationships(id)
            .map(verificationTeamMapper::toDto);
    }

    /**
     * Delete the verificationTeam by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VerificationTeam : {}", id);
        verificationTeamRepository.deleteById(id);
    }
}
