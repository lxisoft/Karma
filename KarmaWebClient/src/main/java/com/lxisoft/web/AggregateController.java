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
package com.lxisoft.web;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.client.karma.api.AggregateResourceApi;
import com.lxisoft.client.karma.model.ApprovalStatusDTO;
import com.lxisoft.client.karma.model.CategoryDTO;
import com.lxisoft.client.karma.model.CommentDTO;
import com.lxisoft.client.karma.model.HelpDTO;
import com.lxisoft.client.karma.model.NeedDTO;
import com.lxisoft.client.karma.model.ReplyDTO;
import com.lxisoft.client.karma.model.SeverityDTO;
import com.lxisoft.client.karma.model.UserCheckDTO;


/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

@Controller
public class AggregateController {
	
	private final Logger log = LoggerFactory.getLogger(AggregateController.class);
	
	@Autowired
	AggregateResourceApi aggregateResourceApi;
	
	/**
	 * POST /needs : Create a new need.
	 *
	 * @param needDTO
	 *            the needDTO to create
	 * @return the string value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("/needs")
	@Timed
	public String postNeed(@ModelAttribute NeedDTO needDTO,Model model) throws URISyntaxException, IllegalStateException, IOException {
		log.debug(" request to save Need : {},{}", needDTO);
		
		log.debug("save Need : {}", needDTO);
	     
	    model.addAttribute("need", aggregateResourceApi.postNeedUsingPOST(needDTO).getBody());
		return "help-post-result";

	}
	
	/**
	 * GET /needs : get all the needs.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/needs")
	@Timed
	public String getAllNeeds(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload, Model model) {
		log.debug("request to get a page of Needs");
	
		List<NeedDTO> needs = aggregateResourceApi.getAllNeedsUsingGET(eagerload, null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
						
		model.addAttribute("needs", needs);
		return "home";

	}
	
	/**
	 * GET /needs/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/needs/{id}")
	@Timed
	public String getNeed(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);

		NeedDTO needDTO =  aggregateResourceApi.getNeedUsingGET(id).getBody();

		model.addAttribute("need", needDTO);

		return "need";
	}
	
	/**
	 * GET /needs/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/needs/statuses/{id}")
	@Timed
	public String getNeedWithStatusesById(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);
				
		NeedDTO needDTO =  aggregateResourceApi.getNeedUsingGET(id).getBody();
		
		List<ApprovalStatusDTO> approvalStatusDTOs =  aggregateResourceApi.getAllApprovalStatusesUsingGET(id, null, null, null, null, null, null, null, null, null).getBody();  
		
		model.addAttribute("need", needDTO);
		model.addAttribute("approvalStatuses",approvalStatusDTOs);
				
		return "pending-need";
	}

	/**
	 * GET /needs : get all the needs by approvalStatus.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */

	@GetMapping("home/{approvalStatus}")
	@Timed
	public String getAllNeedsByApprovedStatus(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "approvalStatus") String approvalStatus, Model model) {
		log.debug("request to get a page of Needs");
		
		List<NeedDTO> needs=aggregateResourceApi.getAllNeedsByApprovedStatusUsingGET(approvalStatus, eagerload, null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
		
		List<CategoryDTO> categories=aggregateResourceApi.getAllCategoriesUsingGET(null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
		
		List<SeverityDTO> Severities=aggregateResourceApi.getAllSeveritiesUsingGET(null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
		
		List<ApprovalStatusDTO> approvalStatuses=aggregateResourceApi.getAllApprovalStatusesUsingGET(null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
		
		model.addAttribute("needs", needs);
		
		model.addAttribute("categories", categories);
		
		model.addAttribute("severities", Severities);
		
		model.addAttribute("approvalStatuses", approvalStatuses);
		
		if (approvalStatus.equals("approved"))
			return "home";
		else if (approvalStatus.equals("pending"))
			return "pending-requests";
		else
			return "home";
	}
	
	/**
	 * PUT /needs : Updates an existing need.
	 *
	 * @param needDTO
	 *            the needDTO to update
	 * @return the string value, or with status 400 (Bad Request) if the needDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         needDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PutMapping("/needs")
	@Timed
	public String updateNeed(@ModelAttribute NeedDTO needDTO,Model model) throws URISyntaxException, IOException {
		
		log.debug("request to update Need : {}", needDTO);
		
		NeedDTO needDto = aggregateResourceApi.updateNeedUsingPUT(needDTO).getBody();
		
		ApprovalStatusDTO approvalStatusDTO=aggregateResourceApi.getApprovalStatusUsingGET(needDTO.getApprovalStatusId()).getBody();
		
		model.addAttribute("need", needDto);
		model.addAttribute("message",approvalStatusDTO );
		return "approve-decline";
	}
	
    /**
	 * POST /helps : Create a new help.
	 *
	 * @param helpDTO
	 *            the helpDTO to create
	 * @return the String value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PostMapping("/helps")
	@Timed
	public String helpNeedy(@ModelAttribute HelpDTO helpDTO,Model model) throws URISyntaxException, IOException {

		log.debug("REST request to save Help : {}", helpDTO);
		
		HelpDTO helpdto=aggregateResourceApi.helpNeedyUsingPOST(helpDTO).getBody();
		
		model.addAttribute("help", helpdto);
		model.addAttribute("message", "submitted");
		return "approve-decline";
	}
	
	/**
	 * PUT /helps : Updates an existing need.
	 *
	 * @param helpDTO
	 *            the needDTO to update
	 * @return the string value, 
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PutMapping("/helps")
	@Timed
	public String updateHelp(@ModelAttribute HelpDTO helpDTO, Model model) throws URISyntaxException, IOException {
		log.debug("request to update Need : {}", helpDTO);	
		
		HelpDTO help = aggregateResourceApi.updateHelpUsingPUT(helpDTO).getBody();
		ApprovalStatusDTO approvalStatusDTO = aggregateResourceApi.getApprovalStatusUsingGET(help.getApprovalStatusId()).getBody();
		
		model.addAttribute("help", help);
		model.addAttribute("message", approvalStatusDTO);
		return "approve-decline";
	}
	
	/**
	 * GET /needs : get all the needs by approvalStatus.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */

	@GetMapping("/helps/{approvalStatus}")
	@Timed
	public String getAllHelpsByApprovedStatus(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "approvalStatus") String approvalStatus, Model model) {
		log.debug("request to get a page of helps");

		List<HelpDTO> helps =aggregateResourceApi.getAllHelpsByApprovedStatusUsingGET(approvalStatus, null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
		
		model.addAttribute("helps", helps);

		if (approvalStatus.equals(("completed")))
			return "completed-helps";
		else if (approvalStatus.equals(("incompleted")))
			return "incompleted-helps";
		else
			return "home";
	}

	/**
	 * GET /help-need/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/help-need/{id}")
	@Timed
	public String getHelpWithNeed(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);

		HelpDTO help = new HelpDTO();
		help.setFulfilledNeedId(id);

		model.addAttribute("help", help);

		return "help-submission";
	}

	/**
	 * GET /help-need/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("helps/incomplete/{id}")
	@Timed
	public String getHelpForApproval(@PathVariable(value = "id") Long id, Model model,Pageable pageable) {

		log.debug("request to get Need : {}", id);

		HelpDTO helpDto = aggregateResourceApi.getHelpUsingGET(id).getBody();

		List<ApprovalStatusDTO> approvalStatuses = aggregateResourceApi.getAllApprovalStatusesUsingGET(id, null, null, null, null, null, null, null, null, null).getBody();
				
		model.addAttribute("help", helpDto);
		model.addAttribute("approvalStatuses", approvalStatuses);

		return "incompleted-help";
	}

	/**
	 * POST /comments : Create a new comment.
	 *
	 * @param commentDTO
	 *            the commentDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new commentDTO, or with status 400 (Bad Request) if the comment
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/comments")
	@Timed
	public String addComment(@ModelAttribute CommentDTO commentDTO,Model model) throws URISyntaxException {
		log.debug("REST request to save Comment : {}", commentDTO);
		CommentDTO comment=aggregateResourceApi.addCommentUsingPOST(commentDTO).getBody();
		model.addAttribute("comment",comment);
		return "comment-post-result";
	}

	/**
	 * GET /commentsByNeedId/:id : get the "id" comment.
	 *
	 * @param id
	 *            the id of the commentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         commentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/commentsByNeedId/{needId}")
	@Timed
	public String getAllCommentsByNeedId(@PathVariable Long needId,Model model,Pageable pageable) {
		log.debug("REST request to get Comments buy needId : {}",needId);

		List<CommentDTO> comments=aggregateResourceApi.getAllCommentsByNeedIdUsingGET(needId, needId, null, null, null, null, null, null, null, null, null).getBody();
		model.addAttribute("comments",comments);
		return "comment";

	}
	
	/**
	 * GET /commentsByHelpId/id : get the "id" comment.
	 *
	 * @param id
	 *            the id of the commentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         commentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/commentsByHelpId/{helpId}")
	@Timed
	public String getAllCommentsByHelpId(@PathVariable Long helpId,Model model,Pageable pageable) {
		log.debug("REST request to get Comment : {}", helpId);
		
		List<CommentDTO> comments =aggregateResourceApi.getAllCommentsByHelpIdUsingGET(helpId, helpId, null, null, null, null, null, null, null, null, null).getBody();

		model.addAttribute("comments",comments);
		return "comment";
	}
	
	/**
	 * PUT /need : Updates an existing need.
	 *
	 * @param need
	 *            the needDTO to update
	 * @return the string value, 
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PutMapping("/needsApprovalStatus")
	@Timed
	public String changeNeedApprovalStatus(@ModelAttribute NeedDTO needDTO,Model model)throws URISyntaxException, IOException {
		
		log.debug("REST request to update Need : {}", needDTO);
		
		NeedDTO result =aggregateResourceApi.updateNeedApprovalStatusUsingPUT(needDTO).getBody();
		
		model.addAttribute("need",result);
		
		return "needd";
	}

	/**
	 * POST /needs : Create a new reply.
	 *
	 * @param replyDTO
	 *            the replyDTO to create
	 * @return the string value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("/replies")
	@Timed
    public String addReply(@ModelAttribute ReplyDTO replyDTO,Model model) throws URISyntaxException, IllegalStateException, IOException {
		log.debug(" request to save Reply : {}", replyDTO);
        
		ReplyDTO reply=aggregateResourceApi.addReplyUsingPOST(replyDTO).getBody();
	    
		model.addAttribute("reply",reply);
		
		return "help-post-result";

	}

	/**
	 * GET  /replies : get all the replies.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of replies in body
	 */
	 @GetMapping("/getAllRepliesByCommentId/{commentId}")
	 @Timed
     public String getAllRepliesByCommentId(Pageable pageable,@PathVariable Long commentId,Model model) {
	 log.debug("REST request to get a page of Replies");
	 
	 List<ReplyDTO> replies = aggregateResourceApi.getAllRepliesByCommentIdUsingGET(commentId, commentId, null, null, null, null, null, null, null, null, null).getBody();
    
	 model.addAttribute("replies", replies);
	 
	 return "replies";
	 } 
	 
	 
	 /**
	    * POST  /user-checks : checking the genuineness.
	    *
	    * @param userCheckDTO the userCheckDTO to create
	    * 
	    * @param voteType the voteType of the userCheckDto
	    * 
	    * @return the ResponseEntity with status 201 (Created) and with body the new userCheckDTO, or with status 400 (Bad Request) if the userCheck has already an ID
	    * @throws URISyntaxException if the Location URI syntax is incorrect
	    */
	   @PostMapping("/user-checks/markingGenuinenes")
	   @Timed
	   public String markingGenuinenes(@ModelAttribute UserCheckDTO userCheckDTO,Model model) throws URISyntaxException {
	       log.debug("REST request to save UserCheck : {}", userCheckDTO);
	           
	       UserCheckDTO result=aggregateResourceApi.markingGenuinenesUsingPOST(userCheckDTO).getBody();
	       
	       model.addAttribute("result", result);
	                        
	       return "abcd";
	       }

	   /**
	    * POST  /user-checks : create  userCheck with positive vote type
	    * @param userCheckDTO the userCheckDTO to create
	    * @return the String value
	    * @throws URISyntaxException if the Location URI syntax is incorrect
	    */
	   @PostMapping("/user-checks/like")
	   @Timed
	   public String doLike(@ModelAttribute UserCheckDTO userCheckDTO,Model model) throws URISyntaxException {
	       log.debug("REST request to save UserCheck as like  : {}", userCheckDTO);
	       
	       UserCheckDTO result = aggregateResourceApi.doLikeUsingPOST(userCheckDTO).getBody();
	       
	       model.addAttribute("like", result);
	       
           return "abcd";
	   }
	   
	   /**
	    * POST  /user-checks : create user  check  with  negative vote type
	    * @param userCheckDTO the userCheckDTO to create
	    * @return the String value.
	    * @throws URISyntaxException if the Location URI syntax is incorrect
	    */
	   @PostMapping("/user-checks/dislike")
	   @Timed
	   public String doDislike(@ModelAttribute UserCheckDTO userCheckDTO,Model model) throws URISyntaxException {
	       log.debug("REST request to save UserCheck as dislike  : {}", userCheckDTO);
           
	       UserCheckDTO result = aggregateResourceApi.doDislikeUsingPOST(userCheckDTO).getBody();
	       
	       model.addAttribute("dislike", result);
	       
	       return "gfghf";
	   }

}
