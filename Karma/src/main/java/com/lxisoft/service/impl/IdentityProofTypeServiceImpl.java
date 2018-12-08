package com.lxisoft.service.impl;

import com.lxisoft.service.IdentityProofTypeService;
import com.lxisoft.domain.IdentityProofType;
import com.lxisoft.repository.IdentityProofTypeRepository;
import com.lxisoft.service.dto.IdentityProofTypeDTO;
import com.lxisoft.service.mapper.IdentityProofTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing IdentityProofType.
 */
@Service
@Transactional
public class IdentityProofTypeServiceImpl implements IdentityProofTypeService {

    private final Logger log = LoggerFactory.getLogger(IdentityProofTypeServiceImpl.class);

    private final IdentityProofTypeRepository identityProofTypeRepository;

    private final IdentityProofTypeMapper identityProofTypeMapper;

    public IdentityProofTypeServiceImpl(IdentityProofTypeRepository identityProofTypeRepository, IdentityProofTypeMapper identityProofTypeMapper) {
        this.identityProofTypeRepository = identityProofTypeRepository;
        this.identityProofTypeMapper = identityProofTypeMapper;
    }

    /**
     * Save a identityProofType.
     *
     * @param identityProofTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IdentityProofTypeDTO save(IdentityProofTypeDTO identityProofTypeDTO) {
        log.debug("Request to save IdentityProofType : {}", identityProofTypeDTO);

        IdentityProofType identityProofType = identityProofTypeMapper.toEntity(identityProofTypeDTO);
        identityProofType = identityProofTypeRepository.save(identityProofType);
        return identityProofTypeMapper.toDto(identityProofType);
    }

    /**
     * Get all the identityProofTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IdentityProofTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IdentityProofTypes");
        return identityProofTypeRepository.findAll(pageable)
            .map(identityProofTypeMapper::toDto);
    }


    /**
     * Get one identityProofType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IdentityProofTypeDTO> findOne(Long id) {
        log.debug("Request to get IdentityProofType : {}", id);
        return identityProofTypeRepository.findById(id)
            .map(identityProofTypeMapper::toDto);
    }

    /**
     * Delete the identityProofType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IdentityProofType : {}", id);
        identityProofTypeRepository.deleteById(id);
    }
}
