package com.lxisoft.repository;

import com.lxisoft.domain.Comment;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value = "select count(c) from Comment c where c.need.id=:needId")
	public Integer countByNeedId(@Param("needId") Long needId); 

}
