package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.NewsFeedService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.NewsFeedDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NewsFeed.
 */
@RestController
@RequestMapping("/api")
public class NewsFeedResource {

    private final Logger log = LoggerFactory.getLogger(NewsFeedResource.class);

    private static final String ENTITY_NAME = "karmaNewsFeed";

    private final NewsFeedService newsFeedService;

    public NewsFeedResource(NewsFeedService newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    /**
     * POST  /news-feeds : Create a new newsFeed.
     *
     * @param newsFeedDTO the newsFeedDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newsFeedDTO, or with status 400 (Bad Request) if the newsFeed has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
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
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /news-feeds : Updates an existing newsFeed.
     *
     * @param newsFeedDTO the newsFeedDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated newsFeedDTO,
     * or with status 400 (Bad Request) if the newsFeedDTO is not valid,
     * or with status 500 (Internal Server Error) if the newsFeedDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
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
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newsFeedDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /news-feeds : get all the newsFeeds.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of newsFeeds in body
     */
    @GetMapping("/news-feeds")
    @Timed
    public ResponseEntity<List<NewsFeedDTO>> getAllNewsFeeds(Pageable pageable) {
        log.debug("REST request to get a page of NewsFeeds");
        Page<NewsFeedDTO> page = newsFeedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/news-feeds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /news-feeds/:id : get the "id" newsFeed.
     *
     * @param id the id of the newsFeedDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newsFeedDTO, or with status 404 (Not Found)
     */
    @GetMapping("/news-feeds/{id}")
    @Timed
    public ResponseEntity<NewsFeedDTO> getNewsFeed(@PathVariable Long id) {
        log.debug("REST request to get NewsFeed : {}", id);
        Optional<NewsFeedDTO> newsFeedDTO = newsFeedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newsFeedDTO);
    }

    /**
     * DELETE  /news-feeds/:id : delete the "id" newsFeed.
     *
     * @param id the id of the newsFeedDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/news-feeds/{id}")
    @Timed
    public ResponseEntity<Void> deleteNewsFeed(@PathVariable Long id) {
        log.debug("REST request to delete NewsFeed : {}", id);
        newsFeedService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
