package com.lxisoft.service.impl;

import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.NeedService;
import com.lxisoft.domain.Need;
import com.lxisoft.repository.NeedRepository;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.mapper.NeedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * Service Implementation for managing Need.
 */
@Service
@Transactional
public class NeedServiceImpl implements NeedService {

    private final Logger log = LoggerFactory.getLogger(NeedServiceImpl.class);

    private final NeedRepository needRepository;

    private final NeedMapper needMapper;
    
    @Autowired
    ApprovalStatusService approvalStatusService;
    
    @Value("${upload.path}")
    private String path;
    

    public NeedServiceImpl(NeedRepository needRepository, NeedMapper needMapper) {
        this.needRepository = needRepository;
        this.needMapper = needMapper;
    }

    /**
     * Save a need.
     *
     * @param needDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
    @Override
    public NeedDTO save(NeedDTO needDTO) throws IOException {
        log.debug("Request to save Need : {}", needDTO);
        
        if (needDTO.getFiles() != null && needDTO.getFiles().length > 0) {
            for (MultipartFile aFile : needDTO.getFiles()){
                 
                System.out.println("Saving file: " + aFile.getOriginalFilename());
               	
                	if (!aFile.isEmpty()) {
                    	
                        String fileName = aFile.getOriginalFilename();
                        InputStream is = aFile.getInputStream();
                        
                        Files.copy(is, Paths.get(path+fileName),
                               StandardCopyOption.REPLACE_EXISTING);                   
                    }    
                
            }
        }

        Need need = needMapper.toEntity(needDTO);
        need = needRepository.save(need);
        return needMapper.toDto(need);
    }

    /**
     * Get all the needs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NeedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Needs");
        return needRepository.findAll(pageable)
            .map(needMapper::toDto);
    }

    /**
     * Get all the Need with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<NeedDTO> findAllWithEagerRelationships(Pageable pageable) {
        return needRepository.findAllWithEagerRelationships(pageable).map(needMapper::toDto);
    }
    

    /**
     * Get one need by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NeedDTO> findOne(Long id) {
        log.debug("Request to get Need : {}", id);
        return needRepository.findOneWithEagerRelationships(id)
            .map(needMapper::toDto);
    }

    /**
     * Delete the need by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Need : {}", id);
        needRepository.deleteById(id);
    }
    
    @Override
	public Page<NeedDTO> findAllNeedsByApprovedStatus(Pageable pageable, String approvalStatus) {
		// TODO Auto-generated method stub
		
		    log.debug("Request to get all Needs by approval status");
		    
		    long approvalStatusId=approvalStatusService.findByStatus(approvalStatus).get().getId();
	         	
	        Page<NeedDTO> needs=findAllNeedsByApprovalStatusId(pageable,approvalStatusId);
	         	
	   	    return needs;
	}
    
   
    
    public Page<NeedDTO> findAllNeedsByApprovalStatusId(Pageable pageable,Long approvalStatusId){
    	
    	 log.debug("Request to get all Needs by approval status id{}",approvalStatusId);
    	
         return needRepository.findAllNeedsByApprovalStatusId(pageable,approvalStatusId)
             .map(needMapper::toDto);
    
    }
    
	public Page<NeedDTO> findAllNeedsBySeverity(Pageable pageable,Long severityId){
		
		 log.debug("Request to get all Needs by severity id{}",severityId);
	    	
		 return needRepository.findAllNeedsBySeverity(pageable, severityId)
				 .map(needMapper::toDto);
		
	}
}
