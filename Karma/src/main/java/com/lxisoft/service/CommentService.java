package com.lxisoft.service;

import com.lxisoft.service.dto.CommentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Comment.
 */
public interface CommentService {

    /**
     * Save a comment.
     *
     * @param commentDTO the entity to save
     * @return the persisted entity
     */
    CommentDTO save(CommentDTO commentDTO);

    /**
     * Get all the comments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CommentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CommentDTO> findOne(Long id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    

    /**
     * get the "Need id" comment.
     *
     * @param id the needId of comment
     */
    
    Page<CommentDTO> findByNeedId(Long id,Pageable pageable);
    
    


    /**
     * Get all the comments by violation id.
     *
     * @param pageable the pagination information, violationId
     * @return the list of entities
     */
    Page<CommentDTO> findAllCommentByViolationId(Pageable pageable,Long violationId);
    
    /**
     * Get all the comments by help id.
     *
     * @param pageable the pagination information, helpId
     * @return the list of entities
     */
	Page<CommentDTO> findAllCommentByHelpId(Pageable pageable, Long helpId);
	
	/**
	 * method to count how many replay get for the comment 
	 * @Param commentDTO
	 */
	void countReplys(CommentDTO commentDTO);
	
	/**
	 * method to count how many positive vote and negative vote
	 * @Param commentDTO
	 */
	void countVotes(CommentDTO commentDTO);
	


	/**
	 * method to count how many positive vote and negative vote
	 * @Param commentDTO
	 */
	void countVotes(List<CommentDTO>commentDTO);
	
	
	

	
	 

	
	/**
     * Get all the comments along with time.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
	Page<CommentDTO> findAllComments(Pageable pageable);

	

}
