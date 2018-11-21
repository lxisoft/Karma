/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lxisoft.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.CommentDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.ReplyDTO;
import com.lxisoft.service.dto.SeverityDTO;
import com.lxisoft.service.dto.UserCheckDTO;

/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

/**
 * Service Interface for managing all domains.
 */
public interface AggregateService {
		
	/**
     * Save a need.
     *
     * @param needDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
    NeedDTO saveNeed(NeedDTO needDTO) throws IOException;
    
	/**
     * Save a need.
     *
     * @param needDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
    NeedDTO saveNeedAsPending(NeedDTO needDTO) throws IOException;
    
    /**
     * Get all the needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
    Page<NeedDTO> findAllNeeds(Pageable pageable);

    /**
     * Get all the Need with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NeedDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" need.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
    Optional<NeedDTO> findOneNeed(Long id);

    /**
     * Delete the "id" need.
     *
     * @param id the id of the entity
     */
    void deleteNeed(Long id);
    
    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of approvedstatus entities
     */
    Page<NeedDTO> findAllNeedsByApprovedStatus(Pageable pageable, String approvalStatus);
    
    /**
     * Get all the SeverityId needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
    Page<NeedDTO> findAllNeedsBySeverityId(Pageable pageable,Long severityId);
    
    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of approvedstatus entities
     */
	Page<NeedDTO> findAllNeedsByApprovalStatusId(Pageable pageable,Long approvalStatusId);
	
	/**
     * find pending status id.
     *
     * @param status the status of the entity
     */
    Optional<ApprovalStatusDTO> findNeedByApprovalStatus(String approvalStatus);
    
    /**
	 * @param pageable
	 * @param checkedNeedId
	 * @return
	 */
	Page<UserCheckDTO> findAllUserChecksByCheckedNeedId(Pageable pageable, Long checkedNeedId);

	/**
     * Get all the approvalStatuses.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<ApprovalStatusDTO> findAllApprovalStatuses(Pageable pageable);

	 /**
     * Get the "id" approvalStatus.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
	Optional<ApprovalStatusDTO> findOneApprovalStatus(Long id);

	/**
     * Get all the categories.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<CategoryDTO> findAllCategories(Pageable pageable);

	 /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
	HelpDTO saveHelpAsIncomplete(HelpDTO helpDTO);
    
	 /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
	HelpDTO saveHelpAsComplete(HelpDTO helpDTO);

	/**
     * Get all the helps.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<HelpDTO> findAllHelps(Pageable pageable);
	
	/**
     * Get all the helps.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<HelpDTO> findAllHelpsWithTime(Pageable pageable);

	/**
     * Get the "id" help.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
	Optional<HelpDTO> findOneHelp(Long id);

	/**
     * Delete the "id" help.
     *
     * @param id the id of the entity
     */
	void deleteHelp(Long id);

	/**
   	 * @param pageable
   	 * @param approvalStatus
   	 * @return
   	 */
	Page<HelpDTO> findAllHelpsByApprovedStatus(Pageable pageable, String approvalStatus);

	/**
	 * @param pageable
	 * @param fulfilledNeedId
	 * @return
	 */
	Page<HelpDTO> findAllHelpsByfulfilledNeedId(Pageable pageable, Long fulfilledNeedId);

	/**
	 * @param userCheckDTO
	 * @return
	 */
	UserCheckDTO saveUserCheck(UserCheckDTO userCheckDTO);

	/**
     * Get all the userChecks.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<UserCheckDTO> findAllUserChecks(Pageable pageable);

	 /**
     * Get the "id" userCheck.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
	Optional<UserCheckDTO> findOneUserCheck(Long id);

	/**
     * Delete the "id" userCheck.
     *
     * @param id the id of the entity
     */
	void deleteUserCheck(Long id);

	
	/**
	 * @param userCheckDTO
	 * @return
	 */
	UserCheckDTO markingGenuinenes(UserCheckDTO userCheckDTO);

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
	 * @param commentDTO
	 * @return
	 */
	CommentDTO saveComment(CommentDTO commentDTO);

	
	/**
	 * @param pageable
	 * @param needId
	 * @return
	 */
	Page<CommentDTO> findAllCommentsByNeedId(Pageable pageable, Long needId);
	
	/**
	 * Get all the comments by help id.
	 *
	 * @param pageable
	 *            the pagination information, helpId
	 * @return the list of entities
	 */
	Page<CommentDTO> findAllCommentsByHelpId(Pageable pageable, Long helpId);

	/**
	 * @param replyDTO
	 * @return
	 */
	ReplyDTO saveReply(ReplyDTO replyDTO);

	/**
	 * @param pageable
	 * @param commentId
	 * @return
	 */
	Page<ReplyDTO> findAllRepliesByCommentId(Pageable pageable, Long commentId);
	
	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */

	Integer calculateLikesNumberOfHelps(Long checkedHelpId);

	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */

	Integer calculateDislikesNumberOfHelps(Long checkedHelpId);
	
	/**
     * Get the "id" severity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SeverityDTO> findOneseverity(Long id);
    
    /**
     * Get all the severities.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
    Page<SeverityDTO> findAllSeverities(Pageable pageable);

    /**
	 * send email after confirmation.
	 *
	 * @param mail
	 *            the mail of posted user
	 */
	String sendMail(String mail, String messageContent);

	/**
	 * Save a need.
	 *
	 * @param needDTO
	 *            the entity to save
	 * 
	 * @return the persisted entity
	 * 
	 * @throws IOException
	 */
	public NeedDTO saveNeedWithApprovalStatus(NeedDTO needDTO) throws IOException;





}
