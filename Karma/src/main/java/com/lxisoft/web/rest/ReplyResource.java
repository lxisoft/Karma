package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.ReplyService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.ReplyDTO;
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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Reply.
 */
@RestController
@RequestMapping("api")
public class ReplyResource {

    private final Logger log = LoggerFactory.getLogger(ReplyResource.class);

    private static final String ENTITY_NAME = "karmaReply";

    private final ReplyService replyService;

    public ReplyResource(ReplyService replyService) {
        this.replyService = replyService;
    }

    /**
     * POST  /replies : Create a new reply.
     *
     * @param replyDTO the replyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new replyDTO, or with status 400 (Bad Request) if the reply has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/replies")
    @Timed
    public ResponseEntity<ReplyDTO> createReply(@RequestBody ReplyDTO replyDTO) throws URISyntaxException {
        log.debug("REST request to save Reply : {}", replyDTO);
        if (replyDTO.getId() != null) {
            throw new BadRequestAlertException("A new reply cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        String parseDate=replyDTO.getDateInString().replace(" ","T").concat("Z");
        Instant date=Instant.parse(parseDate);
        replyDTO.setDate(date);
        
        ReplyDTO result = replyService.save(replyDTO);
        return ResponseEntity.created(new URI("/api/replies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /replies : Updates an existing reply.
     *
     * @param replyDTO the replyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated replyDTO,
     * or with status 400 (Bad Request) if the replyDTO is not valid,
     * or with status 500 (Internal Server Error) if the replyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/replies")
    @Timed
    public ResponseEntity<ReplyDTO> updateReply(@RequestBody ReplyDTO replyDTO) throws URISyntaxException {
        log.debug("REST request to update Reply : {}", replyDTO);
        if (replyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReplyDTO result = replyService.save(replyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, replyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /replies : get all the replies.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of replies in body
     */
    @GetMapping("/replies")
    @Timed
    public ResponseEntity<List<ReplyDTO>> getAllReplies(Pageable pageable) {
        log.debug("REST request to get a page of Replies");
        Page<ReplyDTO> page = replyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/replies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /replies/:id : get the "id" reply.
     *
     * @param id the id of the replyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the replyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/replies/{id}")
    @Timed
    public ResponseEntity<ReplyDTO> getReply(@PathVariable Long id) {
        log.debug("REST request to get Reply : {}", id);
        Optional<ReplyDTO> replyDTO = replyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(replyDTO);
    }

    /**
     * DELETE  /replies/:id : delete the "id" reply.
     *
     * @param id the id of the replyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/replies/{id}")
    @Timed
    public ResponseEntity<Void> deleteReply(@PathVariable Long id) {
        log.debug("REST request to delete Reply : {}", id);
        replyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
