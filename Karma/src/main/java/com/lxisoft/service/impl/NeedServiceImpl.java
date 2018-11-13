package com.lxisoft.service.impl;

import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.CommentService;
import com.lxisoft.service.NeedService;
import com.lxisoft.service.UserCheckService;
import com.lxisoft.domain.Need;
import com.lxisoft.repository.NeedRepository;
import com.lxisoft.service.dto.CommentDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.UserCheckDTO;
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
import java.time.Instant;
import java.util.Date;
import java.util.List;
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
    
    @Autowired
    CommentService commentService;
    
    @Autowired
    UserCheckService userCheckService;
    
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
        
       /* if (needDTO.getFiles() != null && needDTO.getFiles().length > 0) {
            for (MultipartFile aFile : needDTO.getFiles()){
                 
                System.out.println("Saving file: " + aFile.getOriginalFilename());
               	
                	if (!aFile.isEmpty()) {
                    	
                        String fileName = aFile.getOriginalFilename();
                        InputStream is = aFile.getInputStream();
                        
                        Files.copy(is, Paths.get(path+fileName),
                               StandardCopyOption.REPLACE_EXISTING);                   
                    }    
                
            }
        }*/

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
        
        Optional<NeedDTO> optional=needRepository.findOneWithEagerRelationships(id)
        .map(needMapper::toDto);
        Pageable pageable=null;
       List<CommentDTO> commentList=commentService.findByNeedId(id,pageable).getContent();
       optional.get().setCommentList(commentList);
       countComments(optional.get());
        return  optional;
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
	
	


	 public void countComments(NeedDTO needDTO)
	    {
	    	log.debug("method call to count total comments and set it to NeedDto");
	    	Pageable pageable=null;
	    	Long needId=needDTO.getId();
	    	Page<CommentDTO> commentDTO=commentService.findByNeedId(needId,pageable);
	    	needDTO.setNoOfComments((commentDTO.getContent().size())+0l);
	   
	  
	    }
	    
	/**
	    * Get all the comments with time.
	    *
	    * @param pageable the pagination information
	    * @return the list of entities
	    */
		@Override
		public Page<NeedDTO> findAllNeeds(Pageable pageable) {
			log.debug("Request to get all comments");
			Page<NeedDTO> needPage = findAll(pageable);
			List<NeedDTO> needList = needPage.getContent();
			for (NeedDTO needDto : needList) {
				Instant instant = Instant.now();
				
				Date repliedTime = null;
				if (needDto.getDate() != null) {
					repliedTime = Date.from(needDto.getDate());
				}
				Date current = Date.from(instant);
				long diffInSecond = 0l;
				if (repliedTime != null) {
					diffInSecond = (current.getTime() - repliedTime.getTime()) / 1000l;
				}
				long postedBefore = 0l;
				if (diffInSecond < 60l) {
					needDto.setTimeElapsed(diffInSecond + " seconds ago");
				} else if (diffInSecond < 3600l) {
					postedBefore = diffInSecond / 60l;
					needDto.setTimeElapsed(postedBefore + " minutes ago");
				} else if (diffInSecond < 86400l) {
					postedBefore = diffInSecond / 3600l;
					needDto.setTimeElapsed(postedBefore + " hours ago");
				} else if (diffInSecond < 2592000l) {
					postedBefore = diffInSecond / 86400l;
					needDto.setTimeElapsed(postedBefore + " days ago");
				} else if (diffInSecond < 31104000l) {
					postedBefore = diffInSecond / 2592000l;
					needDto.setTimeElapsed(postedBefore + " months ago");
				} else {
					postedBefore = diffInSecond / 31104000l;
					needDto.setTimeElapsed(postedBefore + " years ago");
				}
				System.out.println("how many ago " + needDto.getTimeElapsed());

			}
			return needPage;
		}
		
		
		
		
}
