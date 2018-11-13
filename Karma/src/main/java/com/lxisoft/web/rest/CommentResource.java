package com.lxisoft.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.CommentService;
import com.lxisoft.service.dto.CommentDTO;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Comment.
 */
@RestController
@RequestMapping("/api")
public class CommentResource {

	private final Logger log = LoggerFactory.getLogger(CommentResource.class);

	private static final String ENTITY_NAME = "karmaComment";

	private final CommentService commentService;

	public CommentResource(CommentService commentService) {
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
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) throws URISyntaxException {
		log.debug("REST request to save Comment : {}", commentDTO);
		if (commentDTO.getId() != null) {
			throw new BadRequestAlertException("A new comment cannot already have an ID", ENTITY_NAME, "idexists");
		}

		if (commentDTO.getDateInString() != null) {
			String parseDate = commentDTO.getDateInString().replace(" ", "T").concat("Z");

			Instant dateInstant = Instant.parse(parseDate);
			commentDTO.setDate(dateInstant);
		}

		CommentDTO result = commentService.save(commentDTO);
		return ResponseEntity.created(new URI("/api/comments/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /comments : Updates an existing comment.
	 *
	 * @param commentDTO
	 *            the commentDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         commentDTO, or with status 400 (Bad Request) if the commentDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         commentDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/comments")
	@Timed
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO) throws URISyntaxException {
		log.debug("REST request to update Comment : {}", commentDTO);
		if (commentDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		CommentDTO result = commentService.save(commentDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, commentDTO.getId().toString())).body(result);
	}

	/**
	 * GET /comments : get all the comments.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of comments
	 *         in body
	 */
	@GetMapping("/comments")
	@Timed
	public ResponseEntity<List<CommentDTO>> getAllComments(Pageable pageable) {
		log.debug("REST request to get a page of Comments");
		Page<CommentDTO> page = commentService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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
	public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) {
		log.debug("REST request to get Comment : {}", id);
		Optional<CommentDTO> commentDTO = commentService.findOne(id);
		return ResponseUtil.wrapOrNotFound(commentDTO);
	}

	/*
	 * DELETE /comments/:id : delete the "id" comment.
	 * 
	 * @param id the id of the commentDTO to delete
	 * 
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/comments/{id}")
	@Timed
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		log.debug("REST request to delete Comment : {}", id);
		commentService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * GET /commentsByNeedId/:id : get the comment by needId.
	 *
	 * @param id
	 *            the id of the commentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         commentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/commentsByNeedId/{id}")
	@Timed
	public ResponseEntity<List<CommentDTO>> getCommentByNeedId(@PathVariable Long id) {
		log.debug("REST request to get Comments buy needId : {}", id);
		Pageable pageable = null;
		Page<CommentDTO> page = commentService.findByNeedId(id, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/commentsByNeedId");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);

	}

	/**
	 * GET /comments : get all the comments by violation id.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of comments
	 *         in body
	 */
	@GetMapping("/getAllCommentsByViolationId/{violationId}")
	@Timed
	public ResponseEntity<List<CommentDTO>> getAllCommentsByViolationId(Pageable pageable,
			@PathVariable Long violationId) {
		log.debug("REST request to get a page of Comments  by violation id");
		Page<CommentDTO> page = commentService.findAllCommentByViolationId(pageable, violationId);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getAllCommentsByViolationId");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /comments : get all the comments by help id.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of comments
	 *         in body
	 */
	@GetMapping("/getAllCommentsByHelpId/{helpId}")
	@Timed
	public ResponseEntity<List<CommentDTO>> getAllCommentsByHelpId(Pageable pageable, @PathVariable Long helpId) {
		log.debug("REST request to get a page of Comments  by help id");
		Page<CommentDTO> page = commentService.findAllCommentByHelpId(pageable, helpId);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getAllCommentsByHelpId");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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
	public ResponseEntity<List<CommentDTO>> getAllCommentsByNewsFeedId(Pageable pageable,
			@PathVariable Long newsFeedId) {
		log.debug("REST request to get a page of Comments  by newsFeed id");
		Page<CommentDTO> page = commentService.findAllCommentsByNewsFeedId(pageable, newsFeedId);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getAllCommentsByNewsFeedId");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
}
