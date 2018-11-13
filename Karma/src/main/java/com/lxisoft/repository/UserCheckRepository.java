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
	
	/**
	 * 
	 * @param commentId
	 * @param pageable
	 * @return userCheck entities
	 */
	Page<UserCheck> findByCommentIdIs(Long commentId, Pageable pageable);

	/**
	 * 
	 * @param pageable
	 * @param category
	 * @return user check entities
	 */
	
	Page<UserCheck> findAllUserCheckByCategory(Pageable pageable, String category);

	/**
	 * 
	 * @param pageable
	 * @param violationId
	 * @return user check entities
	 */
	Page<UserCheck> findAllUserCheckByViolationId(Pageable pageable, Long violationId);

	/**
	 * 
	 * @param pageable
	 * @param violationId
	 * @param checkedUserId
	 * @return user check entities
	 */
	Page<UserCheck> findAllUserCheckByViolationIdAndCheckedUserId(Pageable pageable, Long violationId, Long checkedUserId);

	/**
	 * 
	 * @param pageable
	 * @param commentId
	 * @return user check entities
	 */
	Page<UserCheck> findAllUserCheckByCommentId(Pageable pageable, Long commentId);

	/**
	 * 
	 * @param pageable
	 * @param voteType
	 * @return user check entities
	 */
	Page<UserCheck> findAllUserCheckByVoteType(Pageable pageable, String voteType);

	/**
	 * 
	 * @param replyId
	 * @param pageable
	 * @return user check entities
	 */
	
	Page<UserCheck> findAllByReplyIdIs(Long replyId, Pageable pageable);

	/**
	 * 
	 * @param helpId
	 * @param pageable
	 * @return userCheck entities
	 */
	Page<UserCheck> findAllByCheckedHelpId(Long helpId, Pageable pageable);

	/**
	 * 
	 * @param needId
	 * @param pageable
	 * @return
	 */
	Page<UserCheck> findAllByCheckedNeedId(Long needId, Pageable pageable);


}
