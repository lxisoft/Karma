package com.lxisoft.service.impl;

import com.lxisoft.service.ViolationService;
import com.lxisoft.domain.Violation;
import com.lxisoft.repository.ViolationRepository;
import com.lxisoft.service.dto.ViolationDTO;
import com.lxisoft.service.mapper.ViolationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Violation.
 */
@Service
@Transactional
public class ViolationServiceImpl implements ViolationService {

    private final Logger log = LoggerFactory.getLogger(ViolationServiceImpl.class);

    private final ViolationRepository violationRepository;

    private final ViolationMapper violationMapper;

    public ViolationServiceImpl(ViolationRepository violationRepository, ViolationMapper violationMapper) {
        this.violationRepository = violationRepository;
        this.violationMapper = violationMapper;
    }

    /**
     * Save a violation.
     *
     * @param violationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ViolationDTO save(ViolationDTO violationDTO) {
        log.debug("Request to save Violation : {}", violationDTO);
        Violation violation = violationMapper.toEntity(violationDTO);
        violation = violationRepository.save(violation);
        return violationMapper.toDto(violation);
    }

    /**
     * Get all the violations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ViolationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Violations");
        return violationRepository.findAll(pageable)
            .map(violationMapper::toDto);
    }


    /**
     * Get one violation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ViolationDTO> findOne(Long id) {
        log.debug("Request to get Violation : {}", id);
        return violationRepository.findById(id)
            .map(violationMapper::toDto);
    }

    /**
     * Delete the violation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Violation : {}", id);
        violationRepository.deleteById(id);
    }
}
