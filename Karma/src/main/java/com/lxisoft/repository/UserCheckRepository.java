package com.lxisoft.repository;

import com.lxisoft.domain.UserCheck;
import com.lxisoft.service.dto.UserCheckDTO;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCheckRepository extends JpaRepository<UserCheck, Long> {
	
	Optional<UserCheck> findByCategoryAndCheckedNeedIdAndCheckedUserId(String category,Long checkedNeedId,Long checkedUserId);

	/**
	 * @param pageable
	 * @return
	 */
	Page<UserCheck> findAllUserChecksByCheckedNeedId(Pageable pageable,Long checkedNeedId);
	
	Page<UserCheck> findByCommentIdIs(Long commentId, Pageable pageable);

	Page<UserCheck> findAllUserCheckByCategory(Pageable pageable, String category);

	Page<UserCheck> findAllUserCheckByViolationId(Pageable pageable, Long violationId);

	Page<UserCheck> findAllUserCheckByViolationIdAndCheckedUserId(Pageable pageable, Long violationId, Long checkedUserId);
	
	/**
	 * method to get All user checks with the given comment id
	 * @param pageable,comment id
	 * @return page of entity
	 */
	Page<UserCheck> findAllUserCheckByCommentId(Pageable pageable, Long commentId);
	
	/**
	 * method to get All user checks with the given reply id
	 * @param pageable,reply id
	 * @return page of entity
	 */
	Page<UserCheck> findAllByReplyIdIs(Long replyId,Pageable pageable);

	


}
