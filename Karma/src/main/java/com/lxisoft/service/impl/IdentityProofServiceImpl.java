package com.lxisoft.service.impl;

import com.lxisoft.service.IdentityProofService;
import com.lxisoft.domain.IdentityProof;
import com.lxisoft.repository.IdentityProofRepository;
import com.lxisoft.service.dto.IdentityProofDTO;
import com.lxisoft.service.mapper.IdentityProofMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing IdentityProof.
 */
@Service
@Transactional
public class IdentityProofServiceImpl implements IdentityProofService {

    private final Logger log = LoggerFactory.getLogger(IdentityProofServiceImpl.class);

    private final IdentityProofRepository identityProofRepository;

    private final IdentityProofMapper identityProofMapper;

    public IdentityProofServiceImpl(IdentityProofRepository identityProofRepository, IdentityProofMapper identityProofMapper) {
        this.identityProofRepository = identityProofRepository;
        this.identityProofMapper = identityProofMapper;
    }

    /**
     * Save a identityProof.
     *
     * @param identityProofDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IdentityProofDTO save(IdentityProofDTO identityProofDTO) {
        log.debug("Request to save IdentityProof : {}", identityProofDTO);

        IdentityProof identityProof = identityProofMapper.toEntity(identityProofDTO);
        identityProof = identityProofRepository.save(identityProof);
        return identityProofMapper.toDto(identityProof);
    }

    /**
     * Get all the identityProofs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IdentityProofDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IdentityProofs");
        return identityProofRepository.findAll(pageable)
            .map(identityProofMapper::toDto);
    }



    /**
     *  get all the identityProofs where Owner is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<IdentityProofDTO> findAllWhereOwnerIsNull() {
        log.debug("Request to get all identityProofs where Owner is null");
        return StreamSupport
            .stream(identityProofRepository.findAll().spliterator(), false)
            .filter(identityProof -> identityProof.getOwner() == null)
            .map(identityProofMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one identityProof by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IdentityProofDTO> findOne(Long id) {
        log.debug("Request to get IdentityProof : {}", id);
        return identityProofRepository.findById(id)
            .map(identityProofMapper::toDto);
    }

    /**
     * Delete the identityProof by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IdentityProof : {}", id);
        identityProofRepository.deleteById(id);
    }
}
