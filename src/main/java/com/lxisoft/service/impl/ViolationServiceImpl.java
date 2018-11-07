package com.lxisoft.service.impl;

import com.lxisoft.service.MediaService;
import com.lxisoft.service.ViolationService;
import com.lxisoft.domain.Violation;
import com.lxisoft.repository.ViolationRepository;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.dto.ViolationDTO;
import com.lxisoft.service.mapper.ViolationMapper;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
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
    
    @Autowired
    MediaService mediaService;

    public ViolationServiceImpl(ViolationRepository violationRepository, ViolationMapper violationMapper) {
        this.violationRepository = violationRepository;
        this.violationMapper = violationMapper;
    }

    /**
     * Save a violation.
     *
     * @param violationDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
    @Override
    public ViolationDTO save(ViolationDTO violationDTO) throws IOException {
        log.debug("Request to save Violation : {}", violationDTO);
        
        String parseDate=violationDTO.getDateInString().replace(" ","T").concat("Z");
        
        Instant dateInstant=Instant.parse(parseDate);
        violationDTO.setDate(dateInstant);
        
   /*     for(MultipartFile file:violationDTO.getFiles()){
        	
        	MediaDTO mediaDTO=new MediaDTO();
        	
        	mediaDTO.setFile(file);
        	mediaDTO.setViolationId(violationDTO.getId());
        	mediaService.save(mediaDTO);
        	  	
        }*/
        
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


    /**
     * Get all the violations by anonymous user type.
     *
     * @param pageable the pagination information, isAnonymous the posted user is anonymous or not
     * @return the list of entities
     */
	@Override
	public Page<ViolationDTO> findViolationByIsAnonymous(Pageable pageable,Boolean isAnonymous) {
		 log.debug("Request to get all Violations");
		   	return violationRepository.findViolationByIsAnonymous(pageable, isAnonymous)
		   			.map(violationMapper::toDto);
	}

	  /**
     * Get all the violations by after date.
     *
     * @param pageable the pagination information, date to get the violations
     * @return the list of entities
     */
	@Override
	public Page<ViolationDTO> findViolationByDateAfter(Pageable pageable, Instant date) {
		 log.debug("Request to get all Violations by after date");
		   	return violationRepository.findViolationByDateAfter(pageable, date)
		   			.map(violationMapper::toDto);
	
	}

	@Override
	public Page<ViolationDTO> findViolationByDateBefore(Pageable pageable, Instant date) {
		log.debug("Request to get all Violations by before date");
	   	return violationRepository.findViolationByDateBefore(pageable, date)
	   			.map(violationMapper::toDto);

	}

	@Override
	public Page<ViolationDTO> findViolationByDateBetween(Pageable pageable, Instant startDate, Instant endDate) {
		log.debug("Request to get all Violations by before date");
	   	return violationRepository.findViolationByDateBetween(pageable, startDate,endDate)
	   			.map(violationMapper::toDto);

	}
}
