package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.FeedService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.FeedDTO;
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
 * REST controller for managing Feed.
 */
/*@RestController
@RequestMapping("/api")*/
public class FeedResource {

    private final Logger log = LoggerFactory.getLogger(FeedResource.class);

    private static final String ENTITY_NAME = "karmaFeed";

    private final FeedService feedService;

    public FeedResource(FeedService feedService) {
        this.feedService = feedService;
    }

    /**
     * POST  /feeds : Create a new feed.
     *
     * @param feedDTO the feedDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feedDTO, or with status 400 (Bad Request) if the feed has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/feeds")
    @Timed
    public ResponseEntity<FeedDTO> createFeed(@RequestBody FeedDTO feedDTO) throws URISyntaxException {
        log.debug("REST request to save Feed : {}", feedDTO);
        if (feedDTO.getId() != null) {
            throw new BadRequestAlertException("A new feed cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeedDTO result = feedService.save(feedDTO);
        return ResponseEntity.created(new URI("/api/feeds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /feeds : Updates an existing feed.
     *
     * @param feedDTO the feedDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feedDTO,
     * or with status 400 (Bad Request) if the feedDTO is not valid,
     * or with status 500 (Internal Server Error) if the feedDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/feeds")
    @Timed
    public ResponseEntity<FeedDTO> updateFeed(@RequestBody FeedDTO feedDTO) throws URISyntaxException {
        log.debug("REST request to update Feed : {}", feedDTO);
        if (feedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeedDTO result = feedService.save(feedDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feedDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feeds : get all the feeds.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of feeds in body
     */
    @GetMapping("/feeds")
    @Timed
    public ResponseEntity<List<FeedDTO>> getAllFeeds(Pageable pageable) {
        log.debug("REST request to get a page of Feeds");
        Page<FeedDTO> page = feedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/feeds");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /feeds/:id : get the "id" feed.
     *
     * @param id the id of the feedDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedDTO, or with status 404 (Not Found)
     */
    @GetMapping("/feeds/{id}")
    @Timed
    public ResponseEntity<FeedDTO> getFeed(@PathVariable Long id) {
        log.debug("REST request to get Feed : {}", id);
        Optional<FeedDTO> feedDTO = feedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feedDTO);
    }

    /**
     * DELETE  /feeds/:id : delete the "id" feed.
     *
     * @param id the id of the feedDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/feeds/{id}")
    @Timed
    public ResponseEntity<Void> deleteFeed(@PathVariable Long id) {
        log.debug("REST request to delete Feed : {}", id);
        feedService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
