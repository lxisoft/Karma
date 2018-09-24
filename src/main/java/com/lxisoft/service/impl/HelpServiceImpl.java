package com.lxisoft.service.impl;

import com.lxisoft.service.HelpService;
import com.lxisoft.domain.Help;
import com.lxisoft.repository.HelpRepository;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.mapper.HelpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Help.
 */
@Service
@Transactional
public class HelpServiceImpl implements HelpService {

    private final Logger log = LoggerFactory.getLogger(HelpServiceImpl.class);

    private final HelpRepository helpRepository;

    private final HelpMapper helpMapper;

    public HelpServiceImpl(HelpRepository helpRepository, HelpMapper helpMapper) {
        this.helpRepository = helpRepository;
        this.helpMapper = helpMapper;
    }

    /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HelpDTO save(HelpDTO helpDTO) {
        log.debug("Request to save Help : {}", helpDTO);
        Help help = helpMapper.toEntity(helpDTO);
        help = helpRepository.save(help);
        return helpMapper.toDto(help);
    }

    /**
     * Get all the helps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HelpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Helps");
        return helpRepository.findAll(pageable)
            .map(helpMapper::toDto);
    }

    /**
     * Get one help by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public HelpDTO findOne(Long id) {
        log.debug("Request to get Help : {}", id);
        Help help = helpRepository.findOne(id);
        return helpMapper.toDto(help);
    }

    /**
     * Delete the help by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Help : {}", id);
        helpRepository.delete(id);
    }
}
