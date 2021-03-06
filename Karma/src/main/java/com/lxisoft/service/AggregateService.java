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
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.CommentDTO;
import com.lxisoft.service.dto.FeedDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.PostDTO;
import com.lxisoft.service.dto.RegisteredUserDTO;
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
     * @throws IOException 
     */
    Page<NeedDTO> findAllNeedsByApprovedStatus(Pageable pageable, String approvalStatus) throws IOException;
    
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
	HelpDTO saveHelpAsComplete(HelpDTO helpDTO) throws IOException;

	/**
     * Get all the helps.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<HelpDTO> findAllHelps(Pageable pageable);

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

	/**
	 * @param userCheckDTO
	 * @return
	 * @throws IOException 
	 */
	UserCheckDTO markingGenuinenes(UserCheckDTO userCheckDTO) throws IOException;
	
	/**
	 * create new userChecks with positive vote.
	 *
	 * @param userCheck
	 * @return optional<userCheck>
	 * @throws IOException 
	 */

	Optional<UserCheckDTO> saveUserCheckLike(UserCheckDTO userCheckDTO) throws IOException;

	/**
	 * create new userChecks with negative vote.
	 *
	 * @param userCheck
	 * @return optional<userCheck>
	 * @throws IOException 
	 */

	Optional<UserCheckDTO> saveUserCheckDislike(UserCheckDTO userCheckDTO) throws IOException;

	/**
	 * @param commentDTO
	 * @return
	 * @throws IOException 
	 */
	CommentDTO saveComment(CommentDTO commentDTO) throws IOException;

	
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
	 * @throws IOException 
	 */
	ReplyDTO saveReply(ReplyDTO replyDTO) throws IOException;

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
	Integer calculateNoOfGenuiness(Long checkedNeedId);

	/**
	 * Get count of userChecks to newsFeed by newsFeedId.
	 *
	 * @param String
	 *            to find, newsFeedId to find
	 * @return the count of userChecks
	 */

	Integer calculateDislikesNumberOfHelps(Long checkedHelpId);
	
	/**
	 * @param userCheckDTO
	 * @return
	 */
	UserCheckDTO saveUserCheck(UserCheckDTO userCheckDTO);

	
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
	 * @param pageable
	 * @param fulfilledNeedId
	 * @return
	 */
	Page<HelpDTO> findAllCompletedHelpsByfulfilledNeedId(Pageable pageable, Long fulfilledNeedId);

	// Code:Ruhail
		/**
		 * @param postDTO
		 * @return
		 */
		PostDTO savePost(PostDTO postDTO);
		// Code:End

		// Code:Ruhail
		/**
		 * Get all the posts.
		 *
		 * @param pageable
		 *            the pagination information
		 * 
		 * @return the list of entities
		 */
		Page<PostDTO> findAllPosts(Pageable pageable);

		// Code:End
		// Code:Ruhail
		/**
		 * Get count of postLikes.
		 *
		 * @param postId
		 *            to get count
		 * 
		 * @return the count of likes
		 */
		Integer calculateCountOfPostLikesByPostId(Long postId);

		// Code:End
		// Code:Ruhail
		/**
		 * Get count of postDislikes.
		 *
		 * @param postId
		 *            to get count
		 * 
		 * @return the count of dislikes
		 */
		Integer calculateCountOfPostDislikesByPostId(Long postId);

		// Code:End
		// Code:Ruhail
		/**
		 * Get count of postComments.
		 *
		 * @param postId
		 *            to get count
		 * 
		 * @return the count of comments
		 */
		Integer calculateCountOfPostCommentsByPostId(Long postId);

		// Code:End
		// Code:Ruhail
		/**
		 * Find time difference between current date and posted date.
		 *
		 * @param postedDate
		 *            to find the time
		 * 
		 * @return the time
		 */
		String calculateTimeDifferenceBetweenCurrentAndPostedTime(Date postedDate);
		// Code:End


		//anjali
		/**
	      * Save a feed.
	      *
		  * @param feedDTO the entity to save
		  * @return the persisted entity
		  * @throws IOException 
		  */
		   FeedDTO saveFeed(FeedDTO feedDTO) throws IOException;
			
		    
		    /**
		     * Get all the feeds.
		     *
		     * @param pageable the pagination information
		     * @return the list of entities
		     */
		    Page<FeedDTO> findAllFeeds(Pageable pageable);

		    /**
		     * Get all the feeds by Registered User Id.
		     *
		     * @param pageable the pagination information,registeredUserId the id of the user
		     * @return the list of entities
		     */
			Page<FeedDTO> findAllFeedsByRegisteredUserId(Pageable pageable, Long registeredUserId);

			//anjali

			// Code:End
			// Code:Ruhail
			/**
			 * Find one post by id.
			 *
			 * @param id
			 *            to find the post
			 * 
			 * @return PostDTO
			 */
			Optional<PostDTO> findOnePost(Long id);

			// Code:End
			
			//neeraja
			
			/**
			  * @param registeredUserId
			  * @return emotionalQuotient
			  */
			Long calculateRegisteredUserEmotionalQuotient(Long registeredUserId);

			/**
			  * @param registeredUserId
			  * @return socialQuotient
			  */
			Long calculateRegisteredUserSocialQuotient(Long registeredUserId);

			/**
			  * @param registeredUserId
			  * @return registeredUserDto
			  */
			Optional<RegisteredUserDTO> findOneRegisteredUser(Long id);


			//neeraja end
			
			//anjali
			  /**
			     * Save a media.
			     *
			     * @param mediaDTO the entity to save
			     * @return the persisted entity
			     * @throws IOException 
			     */
			    MediaDTO saveMedia(MediaDTO mediaDTO) throws IOException;

			    /**
			     * Get all the media.
			     *
			     * @param needId of the media
			     * @return the list of entities
			     */
			    Page<MediaDTO> findAllUrlByNeedId(Long needId,Pageable pageable);

			    /**
			     * Get all the media.
			     *
			     * @param helpId of the media
			     * @return the list of entities
			     */
				Page<MediaDTO> findAllUrlByHelpId(Long helpId, Pageable pageable);

				 /**
			     * Get all the media by needId.
			     *
			     * @param needId of the media
			     * @return the list of entities
			     */
			    Page<MediaDTO> findAllFileByNeedId(Long needId,Pageable pageable);

			    /**
			     * Get all the media by helpId.
			     *
			     * @param helpId of the media
			     * @return the list of entities
			     */
			    Page<MediaDTO> findAllFileByHelpId(Long helpId,Pageable pageable);

			    //anjali

			//Code Starts:	Dheeraj Das
				
				/**
			     * Convert date from string to instant
			     *
			     * @param parsedDate in string
			     * @return the date in instant
			     */
				Instant convertDateFromStringToInstant(String parsedDate);
				
			//Code Ends:	Dheeraj Das	
				
			//Code Starts :	Dheeraj Das
			
				/**
			     * Get top five registeredUser by social quotient.
			     *
			     * @param pageable the pagination information
			     * @return the list of entity
			     */
				Page<RegisteredUserDTO> findTop5RegisteredUsersBySocialQuotient(Pageable pageable);
				
			//Code Ends : Dheeraj Das
				
			//Code Starts :	Dheeraj Das
			
				/**
			     * Get top five registeredUser by emotional quotient.
			     *
			     * @param pageable the pagination information
			     * @return the list of entity
			     */
				Page<RegisteredUserDTO> findTop5RegisteredUsersByEmotionalQuotient(Pageable pageable);
				
			//Code Ends : Dheeraj Das
			  
				/**
			     * Get registeredUsers by first name.
			     *
			     * @param pageable the pagination information
			     * @return the list of entity
			     */
				Page<RegisteredUserDTO> findAllRegisteredUsersByFirstNameStartingWith(Pageable pageable,String name);
				

}
