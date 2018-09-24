package com.lxisoft.service.impl;

import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.repository.ApprovalStatusRepository;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.mapper.ApprovalStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApprovalStatus.
 */
@Service
@Transactional
public class ApprovalStatusServiceImpl implements ApprovalStatusService {

    private final Logger log = LoggerFactory.getLogger(ApprovalStatusServiceImpl.class);

    private final ApprovalStatusRepository approvalStatusRepository;

    private final ApprovalStatusMapper approvalStatusMapper;

    public ApprovalStatusServiceImpl(ApprovalStatusRepository approvalStatusRepository, ApprovalStatusMapper approvalStatusMapper) {
        this.approvalStatusRepository = approvalStatusRepository;
        this.approvalStatusMapper = approvalStatusMapper;
    }

    /**
     * Save a approvalStatus.
     *
     * @param approvalStatusDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApprovalStatusDTO save(ApprovalStatusDTO approvalStatusDTO) {
        log.debug("Request to save ApprovalStatus : {}", approvalStatusDTO);
        ApprovalStatus approvalStatus = approvalStatusMapper.toEntity(approvalStatusDTO);
        approvalStatus = approvalStatusRepository.save(approvalStatus);
        return approvalStatusMapper.toDto(approvalStatus);
    }

    /**
     * Get all the approvalStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApprovalStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApprovalStatuses");
        return approvalStatusRepository.findAll(pageable)
            .map(approvalStatusMapper::toDto);
    }


    /**
     * Get one approvalStatus by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApprovalStatusDTO> findOne(Long id) {
        log.debug("Request to get ApprovalStatus : {}", id);
        return approvalStatusRepository.findById(id)
            .map(approvalStatusMapper::toDto);
    }

    /**
     * Delete the approvalStatus by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApprovalStatus : {}", id);
        approvalStatusRepository.deleteById(id);
    }
}
