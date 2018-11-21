package com.lxisoft.repository;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.Comment;

import java.util.Optional;

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

	Page<Comment> findAllCommentsByNeedId(Pageable pageable,Long needId);
	
	Page<Comment> findAllCommentsByHelpId(Pageable pageable,Long helpId);

}
