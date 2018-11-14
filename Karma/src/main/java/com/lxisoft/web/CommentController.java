package com.lxisoft.web;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.CommentService;
import com.lxisoft.service.dto.CommentDTO;

/**
 * REST controller for managing Comment.
 */
@Controller
public class CommentController {

	private final Logger log = LoggerFactory.getLogger(CommentController.class);

	private static final String ENTITY_NAME = "karmaComment";

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
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
	public String createComment(@RequestParam(required = false, defaultValue = "false") CommentDTO commentDTO,
			Model model) throws URISyntaxException {
		log.debug("REST request to save Comment : {}", commentDTO);

		// CommentDTO comment
		// =commentResourceApi.createCommentUsingPOST(CommentDTO).getBody();
		// model.addAttribute("comment",result);
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
	@GetMapping("/commentsByNeedId/{id}")
	@Timed
	public String getCommentsByNeedId(@RequestParam(required = false, defaultValue = "false") @PathVariable Long id,
			Model model) {
		log.debug("REST request to get Comments buy needId : {}", id);

		// Page<CommentDTO> commentDTO =
		// commentResourceApi.getCommentUsingGET(id).getBody();

		// model.addAttribute("comment", commentDTO);

		return "comment";

	}

	/**
	 * GET /comments/:id : get the "id" comment.
	 *
	 * @param id
	 *            the id of the commentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         commentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/comments/{id}")
	@Timed
	public String getComment(@RequestParam(required = false, defaultValue = "false") @PathVariable Long id,
			Model model) {
		log.debug("REST request to get Comment : {}", id);
		// CommentDTO commentDTO =
		// commentResourceApi.getCommentUsingGET(id).getBody();

		// model.addAttribute("comment", commentDTO);
		return "comment";
	}

	/**
	 * PUT /comment : Updates an existing comment.
	 *
	 * @param commentDTO
	 *            the commentDTO to update
	 * @return the string value, or with status 400 (Bad Request) if the
	 *         commentDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the commentDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException
	 */
	@PutMapping("/comments")
	@Timed
	public String updateComment(@ModelAttribute CommentDTO commentDTO, Model model)
			throws URISyntaxException, IOException {

		log.debug("request to update comment : {}", commentDTO);

		// CommentDTO commentDto =
		// commentResourceApi.updateCommentUsingPUT(commentDTO).getBody();

		// ApprovalStatusDTO
		// approvalStatusDTO=approvalStatusResourceApi.getApprovalStatusUsingGET(commentDTO.getApprovalStatusId()).getBody();

		// model.addAttribute("comment", commentDto);
		// model.addAttribute("message",approvalStatusDTO );
		return "approve-decline";
	}

	/**
	 * GET /comments : get all the comment.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/comments")
	@Timed
	public String getAllComments(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload, Model model) {
		log.debug("request to get a page of comments");

		// List<CommentDTO> comments =
		// commentResourceApi.getAllCommentUsingGET(eagerload, null, null, null,
		// null, eagerload, null, null, eagerload, eagerload,
		// eagerload).getBody();

		// model.addAttribute("comment", comments);
		return "home";

	}

	/**
	 * GET /commentsBYVoilationId/id : get the "id" comment.
	 *
	 * @param id
	 *            the id of the commentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         commentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/getAllcommentByViolationId/{violationId}")
	@Timed
	public String getAllCommentsByViolationId(
			@RequestParam(required = false, defaultValue = "false") @PathVariable Long id, Model model) {
		log.debug("REST request to get Comment : {}", id);
		// Page<CommentDTO> commentDTO =
		// commentResourceApi.getCommentUsingGET(id).getBody();

		// model.addAttribute("comment", commentDTO);
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
	@GetMapping("/getAllcommentByHelpId/{violationId}")
	@Timed
	public String getAllCommentsByHelpId(@RequestParam(required = false, defaultValue = "false") @PathVariable Long id,
			Model model) {
		log.debug("REST request to get Comment : {}", id);
		// Page<CommentDTO> commentDTO =
		// commentResourceApi.getCommentUsingGET(id).getBody();

		// model.addAttribute("comment", commentDTO);
		return "comment";
	}

	/**
	 * GET /comments : get all the comments by newsFeed id.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of comments
	 *         in body
	 */
	@GetMapping("/getAllCommentsByNewsFeedId/{newsFeedId}")
	@Timed
	public String getAllCommentsByNewsFeedId(Pageable pageable, @PathVariable Long newsFeedId, Model model) {
		log.debug("REST request to get all comments of news feed");
		Page<CommentDTO> commentDtoList = commentService.findAllCommentsByNewsFeedId(pageable, newsFeedId);
		;
		model.addAttribute("commentList", commentDtoList);
		return "show-commentsOfnewsfeed-demo";
	}

}