package com.lxisoft.service.impl;

import com.lxisoft.service.SeverityService;
import com.lxisoft.domain.Severity;
import com.lxisoft.repository.SeverityRepository;
import com.lxisoft.service.dto.SeverityDTO;
import com.lxisoft.service.mapper.SeverityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Severity.
 */
@Service
@Transactional
public class SeverityServiceImpl implements SeverityService {

    private final Logger log = LoggerFactory.getLogger(SeverityServiceImpl.class);

    private final SeverityRepository severityRepository;

    private final SeverityMapper severityMapper;

    public SeverityServiceImpl(SeverityRepository severityRepository, SeverityMapper severityMapper) {
        this.severityRepository = severityRepository;
        this.severityMapper = severityMapper;
    }

    /**
     * Save a severity.
     *
     * @param severityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SeverityDTO save(SeverityDTO severityDTO) {
        log.debug("Request to save Severity : {}", severityDTO);

        Severity severity = severityMapper.toEntity(severityDTO);
        severity = severityRepository.save(severity);
        return severityMapper.toDto(severity);
    }

    /**
     * Get all the severities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SeverityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Severities");
        return severityRepository.findAll(pageable)
            .map(severityMapper::toDto);
    }


    /**
     * Get one severity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SeverityDTO> findOne(Long id) {
        log.debug("Request to get Severity : {}", id);
        return severityRepository.findById(id)
            .map(severityMapper::toDto);
    }

    /**
     * Delete the severity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Severity : {}", id);
        severityRepository.deleteById(id);
    }
}
