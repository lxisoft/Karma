package com.lxisoft.service.impl;

import com.lxisoft.service.RegisteredUserService;
import com.lxisoft.domain.RegisteredUser;
import com.lxisoft.repository.RegisteredUserRepository;
import com.lxisoft.service.dto.RegisteredUserDTO;
import com.lxisoft.service.mapper.RegisteredUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RegisteredUser.
 */
@Service
@Transactional
public class RegisteredUserServiceImpl implements RegisteredUserService {

    private final Logger log = LoggerFactory.getLogger(RegisteredUserServiceImpl.class);

    private final RegisteredUserRepository registeredUserRepository;

    private final RegisteredUserMapper registeredUserMapper;

    public RegisteredUserServiceImpl(RegisteredUserRepository registeredUserRepository, RegisteredUserMapper registeredUserMapper) {
        this.registeredUserRepository = registeredUserRepository;
        this.registeredUserMapper = registeredUserMapper;
    }

    /**
     * Save a registeredUser.
     *
     * @param registeredUserDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RegisteredUserDTO save(RegisteredUserDTO registeredUserDTO) {
        log.debug("Request to save RegisteredUser : {}", registeredUserDTO);

        RegisteredUser registeredUser = registeredUserMapper.toEntity(registeredUserDTO);
        registeredUser = registeredUserRepository.save(registeredUser);
        return registeredUserMapper.toDto(registeredUser);
    }

    /**
     * Get all the registeredUsers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RegisteredUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RegisteredUsers");
        return registeredUserRepository.findAll(pageable)
            .map(registeredUserMapper::toDto);
    }


    /**
     * Get one registeredUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RegisteredUserDTO> findOne(Long id) {
        log.debug("Request to get RegisteredUser : {}", id);
        return registeredUserRepository.findById(id)
            .map(registeredUserMapper::toDto);
    }

    /**
     * Delete the registeredUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RegisteredUser : {}", id);
        registeredUserRepository.deleteById(id);
    }
}
