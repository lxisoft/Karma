package com.lxisoft.repository;

import com.lxisoft.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	/* 
	 * @param needId
	 * @return comments with the given needId as page<Comment>
	 */
	Page<Comment> findAllByNeedId(Long needId, Pageable pageable);


	//Page<Comment> findByNeedIdIs(Long needId, Pageable pageable);

	
	Page<Comment> findAllCommentByViolationId(Pageable pageable, Long violationId);
	
	Page<Comment> findAllCommentByHelpId(Pageable pageable, Long helpId);

	

}
