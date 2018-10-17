package com.lxisoft.service.impl;

import com.lxisoft.service.UserCheckService;
import com.lxisoft.domain.UserCheck;
import com.lxisoft.repository.UserCheckRepository;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.service.mapper.UserCheckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UserCheck.
 */
@Service
@Transactional
public class UserCheckServiceImpl implements UserCheckService {

    private final Logger log = LoggerFactory.getLogger(UserCheckServiceImpl.class);

    private final UserCheckRepository userCheckRepository;

    private final UserCheckMapper userCheckMapper;

    public UserCheckServiceImpl(UserCheckRepository userCheckRepository, UserCheckMapper userCheckMapper) {
        this.userCheckRepository = userCheckRepository;
        this.userCheckMapper = userCheckMapper;
    }

    /**
     * Save a userCheck.
     *
     * @param userCheckDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserCheckDTO save(UserCheckDTO userCheckDTO) {
        log.debug("Request to save UserCheck : {}", userCheckDTO);
        UserCheck userCheck = userCheckMapper.toEntity(userCheckDTO);
        userCheck = userCheckRepository.save(userCheck);
        return userCheckMapper.toDto(userCheck);
    }

    /**
     * Get all the userChecks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserCheckDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserChecks");
        return userCheckRepository.findAll(pageable)
            .map(userCheckMapper::toDto);
    }


    /**
     * Get one userCheck by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserCheckDTO> findOne(Long id) {
        log.debug("Request to get UserCheck : {}", id);
        return userCheckRepository.findById(id)
            .map(userCheckMapper::toDto);
    }

    /**
     * Delete the userCheck by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserCheck : {}", id);
        userCheckRepository.deleteById(id);
    }
}
