package com.lxisoft.repository;

import com.lxisoft.domain.Reply;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reply entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
	
	Page<Reply> findAllRepliesByCommentId(Pageable pageable, Long id);
	
	@Query(value = "select count(c) from Reply c where c.comment.id=:commentId")
	public Integer countReplysByCommentId(@Param("commentId") Long commentId);

}
