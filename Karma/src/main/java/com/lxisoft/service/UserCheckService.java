package com.lxisoft.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.service.dto.UserCheckDTO;

/**
 * Service Interface for managing UserCheck.
 */
public interface UserCheckService {

	/**
	 * Save a userCheck.
	 *
	 * @param userCheckDTO
	 *            the entity to save
	 * @return the persisted entity
	 */
	UserCheckDTO save(UserCheckDTO userCheckDTO);

	/**
	 * Get all the userChecks.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the list of entities
	 */
	Page<UserCheckDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" userCheck.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	Optional<UserCheckDTO> findOne(Long id);

	/**
	 * Delete the "id" userCheck.
	 *
	 * @param id
	 *            the id of the entity
	 */
	void delete(Long id);

	/**
	 * Get one userCheck by Category,CheckedNeedId,CheckedUserId.
	 *
	 * @param Category
	 *            the Category of the entity
	 * 
	 * @param CheckedNeedId
	 *            the CheckedNeedId of the entity
	 * 
	 * @param CheckedUserId
	 *            the CheckedUserId of the entity
	 * 
	 * @return the entity
	 */
	Optional<UserCheckDTO> findByCategoryAndCheckedNeedIdAndCheckedUserId(String category, Long checkedNeedId,
			Long checkedUserId);

	/**
	 * Get userChecks by needID
	 * 
	 * @param pageable
	 * @param checkedNeedId
	 * @return
	 */

	Page<UserCheckDTO> findAllUserChecksByCheckedNeedId(Pageable pageable, Long checkedNeedId);

	/**
	 * Get userChecks by CommentID
	 * 
	 * @param pageable
	 * @param checkedNeedId
	 * @return
	 */

	Page<UserCheckDTO> findAllUserChecksByCommentId(Long commentId, Pageable pageable);

	/**
	 * Get all the userChecks by category.
	 *
	 * @param pageable
	 *            the pagination information, category to find
	 * @return the list of entities
	 */
	Page<UserCheckDTO> findAllUserCheckByCategory(Pageable pageable, String category);

	/**
	 * Get all the userChecks by violationId.
	 *
	 * @param pageable
	 *            the pagination information, category to find
	 * @return the list of entities
	 */
	Page<UserCheckDTO> findAllUserCheckByViolationId(Pageable pageable, Long violationId);

	Page<UserCheckDTO> findAllUserCheckByViolationIdAndCheckedUserId(Pageable pageable, Long violationId,
			Long checkedUserId);

	/**
	 * Get all the userChecks by commentId.
	 *
	 * @param pageable
	 *            the pagination information, commentId to find
	 * @return the page of dto
	 */

	Page<UserCheckDTO> findAllUserCheckByCommentId(Pageable pageable, Long commentId);

	Page<UserCheckDTO> findAllUserCheckByVoteType(Pageable pageable, String voteType);

	/**
	 * create new userChecks with positive vote.
	 *
	 * @param userCheck
	 * @return optional<userCheck>
	 */

	Optional<UserCheckDTO> saveUserCheckLike(UserCheckDTO userCheckDTO);

	/**
	 * create new userChecks with negative vote.
	 *
	 * @param userCheck
	 * @return optional<userCheck>
	 */

	Optional<UserCheckDTO> saveUserCheckDislike(UserCheckDTO userCheckDTO);

	/**
	 * Get all the userChecks by replyId.
	 *
	 * @param pageable
	 *            the pagination information, replyId to find
	 * @return the list of entities
	 */

	Page<UserCheckDTO> findAllUserCheckByReplyId(Pageable pageable, Long replyId);

	/**
	 * Get all the userChecks by helpId.
	 *
	 * @param pageable
	 *            the pagination information, commentId to find
	 * @return the list of entities
	 */

	Page<UserCheckDTO> findAllUserCheckByHelpId(Pageable pageable, Long helpId);

	/**
	 * Get all the userChecks by needId.
	 *
	 * @param pageable
	 *            the pagination information, needId to find
	 * @return the list of entities
	 */

	Page<UserCheckDTO> findAllUserCheckByNeedId(Pageable pageable, Long needId);

	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */

	Integer countOfNewsFeedUserChecksLike(Long newsFeedId);

	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */

	Integer countOfNewsFeedUserChecksDislike(Long newsFeedId);

}
