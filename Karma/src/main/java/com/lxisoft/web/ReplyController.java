package com.lxisoft.web;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Reply.
 */
@Controller
public class ReplyController {

    private final Logger log = LoggerFactory.getLogger(ReplyController.class);

    private static final String ENTITY_NAME = "karmaReply";

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
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
    public String createReply(@RequestBody ReplyDTO replyDTO) throws URISyntaxException {
        log.debug("REST request to save Reply : {}", replyDTO);
        if (replyDTO.getId() != null) {
            throw new BadRequestAlertException("A new reply cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        String parseDate=replyDTO.getDateInString().replace(" ","T").concat("Z");
        Instant date=Instant.parse(parseDate);
        replyDTO.setDate(date);
        
        ReplyDTO result = replyService.save(replyDTO);
        return null;
    }
}