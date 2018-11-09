package com.lxisoft.service.impl;

import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.HelpService;
import com.lxisoft.domain.Help;
import com.lxisoft.repository.HelpRepository;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.mapper.HelpMapper;
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
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Help.
 */
@Service
@Transactional
public class HelpServiceImpl implements HelpService {

    private final Logger log = LoggerFactory.getLogger(HelpServiceImpl.class);

    private final HelpRepository helpRepository;

    private final HelpMapper helpMapper;
    
    @Autowired
    ApprovalStatusService approvalStatusService;

    @Value("${upload.path}")
    private String path;
   
    public HelpServiceImpl(HelpRepository helpRepository, HelpMapper helpMapper) {
        this.helpRepository = helpRepository;
        this.helpMapper = helpMapper;
    }

    /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
    @Override
    public HelpDTO save(HelpDTO helpDTO) throws IOException {
        log.debug("Request to save Help : {}", helpDTO);
        if (helpDTO.getFiles() != null && helpDTO.getFiles().length > 0) {
            for (MultipartFile aFile : helpDTO.getFiles()){
                 
                System.out.println("Saving file: " + aFile.getOriginalFilename());
               	
                	if (!aFile.isEmpty()) {
                    	
                        String fileName = aFile.getOriginalFilename();
                        InputStream is = aFile.getInputStream();
                        
                        log.info("*******fileee{}",fileName);
                        
                        Files.copy(is, Paths.get(path+fileName),
                               StandardCopyOption.REPLACE_EXISTING);                   
                    }           
            }
		}
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
    public Optional<HelpDTO> findOne(Long id) {
        log.debug("Request to get Help : {}", id);
        return helpRepository.findById(id)
            .map(helpMapper::toDto);
    }

    /**
     * Delete the help by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Help : {}", id);
        helpRepository.deleteById(id);
    }
    
    /**
     * Get all the helpsByApprovedStatus.
     *
     * @param pageable the pagination information
     * @param approvedStatus the approvedStatus of the entity
     * @return the list of entities
     */
	@Override
	public Page<HelpDTO> findAllHelpsByApprovedStatus(Pageable pageable, String approvalStatus) {
				
		log.debug("Request to get all Helps by approval status");
	    
	    Long approvalStatusId=approvalStatusService.findByStatus(approvalStatus).get().getId();
         	
        Page<HelpDTO> helps=findAllHelpsByApprovedStatusId(pageable,approvalStatusId);
         	
   	    return helps;
		        
	}

	/**
	 * @param pageable
	 * @param approvalStatusId
	 * @return
	 */
	public Page<HelpDTO> findAllHelpsByApprovedStatusId(Pageable pageable, Long approvalStatusId) {
				
		log.debug("Request to get all Needs by approval status id{}",approvalStatusId);
    	
        return helpRepository.findAllHelpsByApprovalStatusId(pageable,approvalStatusId)
            .map(helpMapper::toDto);
	}
	
	
	
	/**
	 * @param pageable
	 * 
	 * @return
	 */
	
	@Override
	@Transactional(readOnly = true)
	public Page<HelpDTO> findAllHelps(Pageable pageable) {
		log.debug("Request to get all NewsFeeds");
		Page<HelpDTO> helpPage = findAll(pageable);
		List<HelpDTO> helpList = helpPage.getContent();
		for (HelpDTO helpDto : helpList) {
			Instant instant = Instant.now();
			Date helpedTime = null;
			if (helpDto.getTime() != null) {
				helpedTime = Date.from(helpDto.getTime());
			}
			Date current = Date.from(instant);
			long diffInSecond = 0l;
			if (helpedTime != null) {
				diffInSecond = (current.getTime() - helpedTime.getTime()) / 1000l;
			}
			long postedBefore = 0l;
			if (diffInSecond < 60l) {
				helpDto.setTimeElapsed(diffInSecond + " seconds ago");
			} else if (diffInSecond < 3600l) {
				postedBefore = diffInSecond / 60l;
				helpDto.setTimeElapsed(postedBefore + " minutes ago");
			} else if (diffInSecond < 86400l) {
				postedBefore = diffInSecond / 3600l;
				helpDto.setTimeElapsed(postedBefore + " hours ago");
			} else if (diffInSecond < 2592000l) {
				postedBefore = diffInSecond / 86400l;
				helpDto.setTimeElapsed(postedBefore + " days ago");
			} else if (diffInSecond < 31104000l) {
				postedBefore = diffInSecond / 2592000l;
				helpDto.setTimeElapsed(postedBefore + " months ago");
			} else {
				postedBefore = diffInSecond / 31104000l;
				helpDto.setTimeElapsed(postedBefore + " years ago");
			}
			System.out.println("how many ago " + helpDto.getTimeElapsed());

		}
		return helpRepository.findAllHelps(pageable)
	            .map(helpMapper::toDto);
	}
}
