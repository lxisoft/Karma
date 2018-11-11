package com.lxisoft.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.MediaService;
import com.lxisoft.service.NewsFeedService;
import com.lxisoft.service.dto.NewsFeedDTO;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing NewsFeed.
 */
@RestController
@RequestMapping("/api")
public class NewsFeedResource {

	private final Logger log = LoggerFactory.getLogger(NewsFeedResource.class);

	private static final String ENTITY_NAME = "karmaNewsFeed";

	private final NewsFeedService newsFeedService;

	@Autowired
	private MediaService mediaService;

	public NewsFeedResource(NewsFeedService newsFeedService) {
		this.newsFeedService = newsFeedService;
	}

	/**
	 * POST /news-feeds : Create a new newsFeed.
	 *
	 * @param newsFeedDTO
	 *            the newsFeedDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new newsFeedDTO, or with status 400 (Bad Request) if the newsFeed
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/news-feeds")
	@Timed
	public ResponseEntity<NewsFeedDTO> createNewsFeed(@RequestBody NewsFeedDTO newsFeedDTO) throws URISyntaxException {
		log.debug("REST request to save NewsFeed : {}", newsFeedDTO);
		if (newsFeedDTO.getId() != null) {
			throw new BadRequestAlertException("A new newsFeed cannot already have an ID", ENTITY_NAME, "idexists");
		}
		NewsFeedDTO result = newsFeedService.save(newsFeedDTO);
		return ResponseEntity.created(new URI("/api/news-feeds/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /news-feeds : Updates an existing newsFeed.
	 *
	 * @param newsFeedDTO
	 *            the newsFeedDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         newsFeedDTO, or with status 400 (Bad Request) if the newsFeedDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         newsFeedDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/news-feeds")
	@Timed
	public ResponseEntity<NewsFeedDTO> updateNewsFeed(@RequestBody NewsFeedDTO newsFeedDTO) throws URISyntaxException {
		log.debug("REST request to update NewsFeed : {}", newsFeedDTO);
		if (newsFeedDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		NewsFeedDTO result = newsFeedService.save(newsFeedDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsFeedDTO.getId().toString())).body(result);
	}

	/**
	 * GET /news-feeds : get all the newsFeeds.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of newsFeeds
	 *         in body
	 */
	@GetMapping("/news-feeds")
	@Timed
	public ResponseEntity<List<NewsFeedDTO>> getAllNewsFeeds(Pageable pageable) {
		log.debug("REST request to get a page of NewsFeeds");
		Page<NewsFeedDTO> page = newsFeedService.findAllNewsFeeds(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/news-feeds");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /news-feeds/:id : get the "id" newsFeed.
	 *
	 * @param id
	 *            the id of the newsFeedDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         newsFeedDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/news-feeds/{id}")
	@Timed
	public ResponseEntity<NewsFeedDTO> getNewsFeed(@PathVariable Long id) {
		log.debug("REST request to get NewsFeed : {}", id);
		Optional<NewsFeedDTO> newsFeedDTO = newsFeedService.findOne(id);
		return ResponseUtil.wrapOrNotFound(newsFeedDTO);
	}

	/**
	 * DELETE /news-feeds/:id : delete the "id" newsFeed.
	 *
	 * @param id
	 *            the id of the newsFeedDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/news-feeds/{id}")
	@Timed
	public ResponseEntity<Void> deleteNewsFeed(@PathVariable Long id) {
		log.debug("REST request to delete NewsFeed : {}", id);
		newsFeedService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * POST /postnewsfeeds : Create a new newsFeed.
	 *
	 * @param newsFeedDTO
	 *            the newsFeedDTO to create
	 * 
	 * @param attachedFiles
	 *            the files attached with newsFeedDTO
	 * @return the ResponseEntity with status 201 (Created) and with body the
	 *         new newsFeedDTO, or with status 400 (Bad Request) if the newsFeed
	 *         has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */

	@PostMapping("/postnewsfeed")
	@Timed
	public ResponseEntity<NewsFeedDTO> createNewsFeed(@RequestBody NewsFeedDTO newsFeedDTO,
			@RequestParam MultipartFile[] attachedFiles) throws URISyntaxException, IllegalStateException, IOException {
		log.debug("REST request to save NewsFeed : {}", newsFeedDTO);
		if (newsFeedDTO.getId() != null) {
			throw new BadRequestAlertException("A new newsFeed cannot already have an ID", ENTITY_NAME, "idexists");
		}
		newsFeedDTO.setAttachedFiles(attachedFiles);
		NewsFeedDTO result = newsFeedService.saveNewsFeed(newsFeedDTO);
		return ResponseEntity.created(new URI("/api/news-feeds/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

}
