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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.client.karma.api.AggregateResourceApi;
import com.lxisoft.client.karma.model.ApprovalStatusDTO;
import com.lxisoft.client.karma.model.CategoryDTO;
import com.lxisoft.client.karma.model.CommentDTO;
import com.lxisoft.client.karma.model.FeedDTO;
import com.lxisoft.client.karma.model.HelpDTO;
import com.lxisoft.client.karma.model.MediaDTO;
import com.lxisoft.client.karma.model.NeedDTO;
import com.lxisoft.client.karma.model.RegisteredUserDTO;
import com.lxisoft.client.karma.model.ReplyDTO;
import com.lxisoft.client.karma.model.UserCheckDTO;

/**
 * TODO Provide a detailed description here
 * 
 * @author Sarangi Balu sarangibalu, sarangibalu.a@lxisoft.com
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
	public String postNeed(@ModelAttribute NeedDTO needDTO, @RequestParam MultipartFile[] multipartFiles, Model model)
			throws URISyntaxException, IllegalStateException, IOException {
		log.debug(" request to save Need : {},{}", needDTO, multipartFiles.length);

		NeedDTO needDto = aggregateResourceApi.postNeedUsingPOST(needDTO).getBody();

		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {

				MediaDTO mediaDTO = new MediaDTO();

				mediaDTO.setFileName(file.getOriginalFilename());
				mediaDTO.setNeedId(needDto.getId());
				mediaDTO.setExtension(file.getContentType());
				mediaDTO.setBytes(file.getBytes());

				MediaDTO mediaDto = aggregateResourceApi.postMediaUsingPOST(mediaDTO).getBody();

			}
		}

		log.debug("save Need : {},{}", needDTO, multipartFiles);

		model.addAttribute("need", needDto);
		return "help-post-result";

	}

	/**
	 * Redirects to the page containing all the approved needs
	 *
	 */
	@GetMapping("/")
	@Timed
	public String getAllNeeds() {
		log.debug("request to redirect to url /home/aaproved");
		return "redirect:/home/approved";

	}

	/**
	 * GET /needs : get all the needs. getA
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

		List<NeedDTO> needs = aggregateResourceApi.getAllNeedsUsingGET(eagerload, null, null, null, null, eagerload,
				null, null, eagerload, eagerload, eagerload).getBody();

		model.addAttribute("needs", needs);
		return "home";

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
	@GetMapping("/completed-helps/{id}")
	@Timed
	public String getAllHelpsByNeedId(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get a page of Needs");

		List<String> timeArray = new ArrayList<String>();
		timeArray.add("time,desc");
		
		List<HelpDTO> helps = aggregateResourceApi.getAllCompletedHelpsByfulfilledNeedIdUsingGET(id, id, null, null,
				null, null, null, timeArray, null, null, null).getBody();
		NeedDTO need = aggregateResourceApi.getNeedUsingGET(id).getBody();
		model.addAttribute("helps", helps);
		model.addAttribute("need", need);
		return "completed-helps";

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

		NeedDTO needDTO = aggregateResourceApi.getNeedUsingGET(id).getBody();

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

		NeedDTO needDTO = aggregateResourceApi.getNeedUsingGET(id).getBody();
		List<ApprovalStatusDTO> approvalStatusDTOs = aggregateResourceApi
				.getAllApprovalStatusesUsingGET(id, null, null, null, null, null, null, null, null, null).getBody();

		model.addAttribute("need", needDTO);
		model.addAttribute("approvalStatuses", approvalStatusDTOs);

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

		List<String> dateArray = new ArrayList<String>();
		dateArray.add("date,desc");

		// List<NeedDTO> needs =
		// aggregateResourceApi.getAllNeedsByApprovedStatusUsingGET(approvalStatus,
		// eagerload, offset, page, pageNumber, pageSize, paged, size, sort,
		// sortSorted, sortUnsorted, unpaged)

		List<NeedDTO> needs = aggregateResourceApi.getAllNeedsByApprovedStatusUsingGET(approvalStatus, eagerload, null,
				null, null, null, eagerload, null, dateArray, null, null, null).getBody();

		List<CategoryDTO> categories = aggregateResourceApi.getAllCategoriesUsingGET(null, null, null, null, eagerload,
				null, null, eagerload, eagerload, eagerload).getBody();

		// List<SeverityDTO>
		// Severities=aggregateResourceApi.getAllSeveritiesUsingGET(null, null,
		// null, null, eagerload, null, null, eagerload, eagerload,
		// eagerload).getBody();

		List<ApprovalStatusDTO> approvalStatuses = aggregateResourceApi.getAllApprovalStatusesUsingGET(null, null, null,
				null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();

		model.addAttribute("needs", needs);

		model.addAttribute("categories", categories);

		// model.addAttribute("severities", Severities);

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
	public String updateNeed(@ModelAttribute NeedDTO needDTO, BindingResult bindingResult, Model model)
			throws URISyntaxException, IOException {

		log.debug("request to update Need : {}", needDTO);

		needDTO = aggregateResourceApi.updateNeedUsingPUT(needDTO).getBody();

		ApprovalStatusDTO approvalStatusDTO = aggregateResourceApi
				.getApprovalStatusUsingGET(needDTO.getApprovalStatusId()).getBody();

		model.addAttribute("need", needDTO);
		model.addAttribute("message", approvalStatusDTO);
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
	public String helpNeedy(@ModelAttribute HelpDTO helpDTO, @RequestParam MultipartFile[] multipartFiles, Model model)
			throws URISyntaxException, IOException {

		log.debug("REST request to save Help : {}", helpDTO);

		HelpDTO helpDto = aggregateResourceApi.helpNeedyUsingPOST(helpDTO).getBody();

		if (multipartFiles != null) {
			for (MultipartFile file : multipartFiles) {

				MediaDTO mediaDTO = new MediaDTO();

				mediaDTO.setFileName(file.getOriginalFilename());
				mediaDTO.setHelpId(helpDto.getId());
				mediaDTO.setExtension(file.getContentType());
				mediaDTO.setBytes(file.getBytes());

				MediaDTO mediaDto = aggregateResourceApi.postMediaUsingPOST(mediaDTO).getBody();

			}
		}

		log.debug("save help : {},{}", helpDto, multipartFiles);

		model.addAttribute("help", helpDto);
		model.addAttribute("message", "submitted");
		return "approve-decline";
	}

	/**
	 * PUT /helps : Updates an existing need.
	 *
	 * @param helpDTO
	 *            the needDTO to update
	 * @return the string value, or with status 400 (Bad Request) if the needDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         needDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException
	 */
	@PutMapping("/helps")
	@Timed
	public String updateHelp(@ModelAttribute HelpDTO helpDTO, Model model) throws URISyntaxException, IOException {
		log.debug("request to update Need : {}", helpDTO);

		HelpDTO help = aggregateResourceApi.updateHelpUsingPUT(helpDTO).getBody();
		ApprovalStatusDTO approvalStatusDTO = aggregateResourceApi.getApprovalStatusUsingGET(help.getApprovalStatusId())
				.getBody();

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

		List<String> timeArray = new ArrayList<String>();
		timeArray.add("time,desc");

		// List<NeedDTO> needs =
		// aggregateResourceApi.getAllNeedsByApprovedStatusUsingGET(approvalStatus,
		// eagerload, offset, page, pageNumber, pageSize, paged, size, sort,
		// sortSorted, sortUnsorted, unpaged)

		/*
		 * List<NeedDTO> needs =
		 * aggregateResourceApi.getAllNeedsByApprovedStatusUsingGET(
		 * approvalStatus, eagerload, null, null, null, null, eagerload, null,
		 * dateArray, null, null, null).getBody();
		 */

		// List<HelpDTO> helps =
		// aggregateResourceApi.getAllHelpsByApprovedStatusUsingGET(approvalStatus,
		// offset, page, pageNumber, pageSize, paged, size, sort, sortSorted,
		// sortUnsorted, unpaged)
		List<HelpDTO> helps = aggregateResourceApi.getAllHelpsByApprovedStatusUsingGET(approvalStatus, null, null, null,

				null, eagerload, null, timeArray, null, null, null).getBody();

		List<ApprovalStatusDTO> approvalStatuses = aggregateResourceApi.getAllApprovalStatusesUsingGET(null, null, null,
				null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();

		model.addAttribute("approvalStatuses", approvalStatuses);

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
	public String getHelpForApproval(@PathVariable(value = "id") Long id, Model model, Pageable pageable) {

		log.debug("request to get Need : {}", id);

		HelpDTO helpDto = aggregateResourceApi.getHelpUsingGET(id).getBody();

		List<ApprovalStatusDTO> approvalStatuses = aggregateResourceApi
				.getAllApprovalStatusesUsingGET(id, null, null, null, null, null, null, null, null, null).getBody();

		model.addAttribute("help", helpDto);
		model.addAttribute("approvalStatuses", approvalStatuses);

		return "incompleted-help";
	}

	/**
	 * POST /user-checks : checking the genuineness.
	 *
	 * @param userCheckDTO
	 *            the userCheckDTO to create
	 * 
	 * @param voteType
	 *            the voteType of the userCheckDto
	 * 
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new userCheckDTO, or with status 400 (Bad Request) if the
	 *         userCheck has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/user-checks/markingGenuineness")
	@Timed
	public String markingGenuineness(@ModelAttribute UserCheckDTO userCheckDTO, Model model) throws URISyntaxException {
		log.debug("REST request to save UserCheck : {}", userCheckDTO);

		UserCheckDTO result = aggregateResourceApi.markingGenuinenesUsingPOST(userCheckDTO).getBody();

		List<NeedDTO> needs = aggregateResourceApi.getAllNeedsByApprovedStatusUsingGET("approved", null, null, null,
				null, null, null, null, null, null, null, null).getBody();

		model.addAttribute("result", result);

		model.addAttribute("needs", needs);

		return "home::needs";
	}

	/**
	 * GET /feeds : get all the feeds.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/getFeeds")
	@Timed
	public String getAllFeeds(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload, Model model) {
		log.debug("request to get a page of Feeds");

		List<String> dateArray = new ArrayList<String>();
		dateArray.add("date,desc");

		
		List<FeedDTO> feeds = aggregateResourceApi
				.getAllFeedsUsingGET(null, null, null, null, eagerload, null, dateArray, null, null, null)
				.getBody();

		model.addAttribute("feeds", feeds);
		return "feeds";

	}

	/**
	 * GET /needs : get all the feeds by registeredUserId
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/feeds/getAllFeedsByRegisteredUserId/{registeredUserId}")
	@Timed
	public String getAllFeedsByRegisteredUserId(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "registeredUserId") Long registeredUserId, Model model) {
		log.debug("request to get a page of Feeds");

		List<String> dateArray = new ArrayList<String>();
		dateArray.add("date,desc");
		
		List<FeedDTO> feeds = aggregateResourceApi.getAllFeedsByRegisteredUserIdUsingGET(registeredUserId,
				registeredUserId, null, null, null, eagerload, null, dateArray, null, null, null).getBody();

		model.addAttribute("feeds", feeds);

		return "home";

	}

	/**
	 * GET /needs : get all the comments by needId
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/comments/getAllCommentsByNeedId/{id}")
	@Timed
	public String getAllCommentsByNeedId(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get a page of Comments");

		List<String> dateArray = new ArrayList<String>();
		dateArray.add("date,desc");
		
		List<CommentDTO> comments = aggregateResourceApi.getAllCommentsByNeedIdUsingGET(id, id, null, null, null,
				eagerload, null, dateArray, null, null, null).getBody();

		model.addAttribute("comments", comments);

		return "home :: comment-section(checkedNeedId=${id},checkedHelpId=null,postId=null)";

	}

	/**
	 * GET /needs : get all the comments by helpId
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/comments/getAllCommentsByHelpId/{id}")
	@Timed
	public String getAllCommentsByHelpId(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get a page of Comments by help id");

		List<String> dateArray = new ArrayList<String>();
		dateArray.add("date,desc");
		
		List<CommentDTO> comments = aggregateResourceApi.getAllCommentsByHelpIdUsingGET(id, id, null, null, null,
				eagerload, null, dateArray, null, null, null).getBody();

		model.addAttribute("comments", comments);

		return "home :: comment-section(checkedNeedId=null,checkedHelpId=${id},postId=null)";

	}

	/**
	 * GET /needs : get all the replies by commentId
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/replies/getAllRepliesByCommentId/{id}")
	@Timed
	public String getAllRepliesByCommentId(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get a page of Replie");

		List<String> dateArray = new ArrayList<String>();
		dateArray.add("date,desc");
		List<ReplyDTO> replies = aggregateResourceApi.getAllRepliesByCommentIdUsingGET(id, id, null, null, null,
				eagerload, null, dateArray, null, null, null).getBody();

		model.addAttribute("replies", replies);

		return "home :: replies(commentId=${id})";

	}

	/**
	 * POST /comments : add comment
	 *
	 * @param CommentDTO
	 *            the commentDTO to create
	 * 
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new userCheckDTO, or with status 400 (Bad Request) if the
	 *         userCheck has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/comments/addComment")
	@Timed
	public String addComment(@ModelAttribute CommentDTO commentDTO, Model model) throws URISyntaxException {
		log.debug("REST request to save Comment : {}", commentDTO);

		CommentDTO result = aggregateResourceApi.addCommentUsingPOST(commentDTO).getBody();
		// Long
		// id=(result.getNeedId()==null)?(result.getHelpId()==null)?result.getPostId():result.getHelpId():result.getNeedId();
		String resultFragment = null;
		List<CommentDTO> comments = new ArrayList<CommentDTO>();
		if (result.getNeedId() != null) {
			comments = aggregateResourceApi.getAllCommentsByNeedIdUsingGET(result.getNeedId(), null, null, null, null,
					null, null, null, null, null, null).getBody();

			model.addAttribute("need", aggregateResourceApi.getNeedUsingGET(result.getNeedId()).getBody());
			resultFragment = "home::need";
		}

		else if (result.getHelpId() != null) {
			comments = aggregateResourceApi.getAllCommentsByHelpIdUsingGET(result.getHelpId(), null, null, null, null,
					null, null, null, null, null, null).getBody();
			model.addAttribute("help", aggregateResourceApi.getHelpUsingGET(result.getNeedId()));
			resultFragment = "completed-helps::help";
		}

		else {
			log.info("method is not defined for posts");
		}
		model.addAttribute("result", result);

		model.addAttribute("comments", comments);

		return resultFragment;
	}

	/**
	 * POST /comments : add reply
	 *
	 * @param CommentDTO
	 *            the replyDTO to create
	 * 
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new userCheckDTO, or with status 400 (Bad Request) if the
	 *         userCheck has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/replies/addReply")
	@Timed
	public String addReply(@ModelAttribute ReplyDTO replyDTO, Model model) throws URISyntaxException {
		log.debug("REST request to save Reply : {}", replyDTO);

		List<ReplyDTO> replies = new ArrayList<ReplyDTO>();

		ReplyDTO result = aggregateResourceApi.addReplyUsingPOST(replyDTO).getBody();

		replies = aggregateResourceApi.getAllRepliesByCommentIdUsingGET(result.getCommentId(), null, null, null, null,
				null, null, null, null, null, null).getBody();
		model.addAttribute("result", result);
		model.addAttribute("replies", replies);
		return "home::replies(commentId=${replyDTO.commentId})";
	}

	/**
	 * GET /registeredUserDTO/:id : get the "id" registeredUserDTO.
	 *
	 * @param id
	 *            the id of the registeredUserDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/registered-users/{id}")
	@Timed
	public String getOneRegisteredUser(@PathVariable(value = "id") Long id, Model model) {

		log.debug("request to get registeredUserDTO : {}", id);

		RegisteredUserDTO registeredUser = aggregateResourceApi.getOneRegisteredUserUsingGET(id).getBody();

		model.addAttribute("user", registeredUser);

		return "user";
	}

	@GetMapping("/login")
	@Timed
	public String customLogin() {

		log.debug("request to login with custom loginpage");

		return "login";
	}

}
