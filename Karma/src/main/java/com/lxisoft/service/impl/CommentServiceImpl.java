package com.lxisoft.service.impl;

import com.lxisoft.service.CommentService;
import com.lxisoft.service.UserCheckService;
import com.lxisoft.domain.Comment;
import com.lxisoft.repository.CommentRepository;
import com.lxisoft.service.dto.CommentDTO;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.service.mapper.CommentMapper;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Comment.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;
    
    @Autowired
    private  UserCheckService userCheckService;

    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    /**
     * Save a comment.
     *
     * @param commentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        log.debug("Request to save Comment : {}", commentDTO);
        Comment comment = commentMapper.toEntity(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    /**
     * Get all the comments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CommentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comments");
        return commentRepository.findAll(pageable)
            .map(commentMapper::toDto);
    }


    /**
     * Get one comment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CommentDTO> findOne(Long id) {
        log.debug("Request to get Comment : {}", id);
        Optional<CommentDTO>commentDto=commentRepository.findById(id)
            .map(commentMapper::toDto);
         
       countLikes(commentDto.get());
        
        return commentDto;
    }
    
    
    
     /*
      * to  count total likes,dislikes and set to the commentDTo
      * @Param commentDTO
      */
   
    
    
    public void countLikes(CommentDTO commentDTO)
    {
    	log.debug("method call to count likes and dislikes and set it to commentDto");
    	Pageable pageable=null;
    	Long commentId=commentDTO.getId();
    	Long noOfLikes=0l;
    	Long noOfDislikes=0l;
    	
    	Page<UserCheckDTO> userCheckDTOs=userCheckService.findAllUserChecksByCommentId(commentId,pageable);
    	for(UserCheckDTO userCheckDTO:userCheckDTOs.getContent())
    	{
    		if(userCheckDTO.getVoteType().equals("positive"))
    		{
    			noOfLikes++;
    		}
    		else
    		{
    			noOfDislikes++;
    		}
    	}
    	commentDTO.setNoOfLikes(noOfLikes);
    	commentDTO.setNoOfDislikes(noOfDislikes);
    
   
    	
    }
    
    
    /*
     * to  count totel likes,dislikes of list of commentDto and set to the commentDTo
     * @Param commentDTO
     */
  
   
   
   public void countLikes(List<CommentDTO> commentDTOs)
   {
   	log.debug("method call to count likes and dislikes and set it to commentDto");
   	Pageable pageable=null;
   	
	   	for(CommentDTO commentDTO:commentDTOs)
	   	{
	   	
				   	Long commentId=commentDTO.getId();
				   	Long noOfLikes=0l;
				   	Long noOfDislikes=0l;
				   	
				   	Page<UserCheckDTO> userCheckDTOs=userCheckService.findAllUserChecksByCommentId(commentId,pageable);
				   	for(UserCheckDTO userCheckDTO:userCheckDTOs.getContent())
				   	{
				   		if(userCheckDTO.getVoteType().equals("positive"))
				   		{
				   			noOfLikes++;
				   		}
				   		else
				   		{
				   			noOfDislikes--;
				   		}
				   	}
				   	commentDTO.setNoOfLikes(noOfLikes);
				   	commentDTO.setNoOfDislikes(noOfDislikes);
	  
	   	}
   }

    /**
     * Delete the comment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comment : {}", id);
        commentRepository.deleteById(id);
    }

     /* 
     *find all comment By NeedId
     *@Param needId 
     */

	@Override
	public Page<CommentDTO> findByNeedId(Long needId,Pageable pageable) {
		log.debug("request to get all comment by needId :"+needId);
		Page<Comment> comments=commentRepository.findAllByNeedId(needId,pageable);
		Page<CommentDTO> commentDtos=comments.map(commentMapper::toDto);
		countLikes(commentDtos.getContent());
		return  commentDtos;
		
	}


    
    /**
     * Get all the comments by violationId.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	public Page<CommentDTO> findAllCommentByViolationId(Pageable pageable, Long violationId) {
		 log.debug("Request to get all Comments bu violationId");
	        return commentRepository.findAllCommentByViolationId(pageable,violationId)
	            .map(commentMapper::toDto);
	  }
	
	/**
     * Get all the comments by helpId.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	public Page<CommentDTO> findAllCommentByHelpId(Pageable pageable, Long helpId) {
		 log.debug("Request to get all Comments bu helpId");
	        return commentRepository.findAllCommentByHelpId(pageable,helpId)
	            .map(commentMapper::toDto);
	  }

	
	
	/**
    * Get all the comments with time.
    *
    * @param pageable the pagination information
    * @return the list of entities
    */
	@Override
	public Page<CommentDTO> findAllComments(Pageable pageable) {
		log.debug("Request to get all comments");
		Page<CommentDTO> commentPage = findAll(pageable);
		List<CommentDTO> commentList = commentPage.getContent();
		for (CommentDTO commentDto : commentList) {
			Instant instant = Instant.now();
			Date repliedTime = null;
			if (commentDto.getDate() != null) {
				repliedTime = Date.from(commentDto.getDate());
			}
			Date current = Date.from(instant);
			long diffInSecond = 0l;
			if (repliedTime != null) {
				diffInSecond = (current.getTime() - repliedTime.getTime()) / 1000l;
			}
			long postedBefore = 0l;
			if (diffInSecond < 60l) {
				commentDto.setTimeElapsed(diffInSecond + " seconds ago");
			} else if (diffInSecond < 3600l) {
				postedBefore = diffInSecond / 60l;
				commentDto.setTimeElapsed(postedBefore + " minutes ago");
			} else if (diffInSecond < 86400l) {
				postedBefore = diffInSecond / 3600l;
				commentDto.setTimeElapsed(postedBefore + " hours ago");
			} else if (diffInSecond < 2592000l) {
				postedBefore = diffInSecond / 86400l;
				commentDto.setTimeElapsed(postedBefore + " days ago");
			} else if (diffInSecond < 31104000l) {
				postedBefore = diffInSecond / 2592000l;
				commentDto.setTimeElapsed(postedBefore + " months ago");
			} else {
				postedBefore = diffInSecond / 31104000l;
				commentDto.setTimeElapsed(postedBefore + " years ago");
			}
			System.out.println("how many ago " + commentDto.getTimeElapsed());

		}
		return commentPage;
	}
}
