package com.lxisoft.service.impl;

import com.lxisoft.service.LoggedUserService;
import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.Media;
import com.lxisoft.repository.LoggedUserRepository;
import com.lxisoft.service.dto.LoggedUserDTO;
import com.lxisoft.service.mapper.LoggedUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LoggedUser.
 */
@Service
@Transactional
public class LoggedUserServiceImpl implements LoggedUserService {

    private final Logger log = LoggerFactory.getLogger(LoggedUserServiceImpl.class);

    private final LoggedUserRepository loggedUserRepository;

    private final LoggedUserMapper loggedUserMapper;

    public LoggedUserServiceImpl(LoggedUserRepository loggedUserRepository, LoggedUserMapper loggedUserMapper) {
        this.loggedUserRepository = loggedUserRepository;
        this.loggedUserMapper = loggedUserMapper;
    }

    /**
     * Save a loggedUser.
     *
     * @param loggedUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LoggedUserDTO save(LoggedUserDTO loggedUserDTO) {
        log.debug("Request to save LoggedUser : {}", loggedUserDTO);
        LoggedUser loggedUser = loggedUserMapper.toEntity(loggedUserDTO);
        
        loggedUser = loggedUserRepository.save(loggedUser);
        return loggedUserMapper.toDto(loggedUser);
    }

    /**
     * Get all the loggedUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LoggedUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LoggedUsers");
        return loggedUserRepository.findAll(pageable)
            .map(loggedUserMapper::toDto);
    }


    /**
     * Get one loggedUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LoggedUserDTO> findOne(Long id) {
        log.debug("Request to get LoggedUser : {}", id);
        return loggedUserRepository.findById(id)
            .map(loggedUserMapper::toDto);
    }

    /**
     * Delete the loggedUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LoggedUser : {}", id);
        loggedUserRepository.deleteById(id);
    }
}
