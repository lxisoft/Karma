package com.lxisoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lxisoft.domain.Comment;

/**
 * Spring Data repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	/*
	 * @param needId
	 * 
	 * @return comments with the given needId as page<Comment>
	 */
	Page<Comment> findAllByNeedId(Long needId, Pageable pageable);

	// Page<Comment> findByNeedIdIs(Long needId, Pageable pageable);

	Page<Comment> findAllCommentByViolationId(Pageable pageable, Long violationId);

	Page<Comment> findAllCommentByHelpId(Pageable pageable, Long helpId);

	/*
	 * @param newsFeedId
	 * 
	 * @return comments with the given newsFeedId as page<Comment>
	 */

	Page<Comment> findAllCommentsByNewsFeedId(Pageable pageable, Long newsFeedId);

}
