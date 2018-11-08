package com.lxisoft.web;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.CommentService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.CommentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

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
     * POST  /comments : Create a new comment.
     *
     * @param commentDTO the commentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new commentDTO, or with status 400 (Bad Request) if the comment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/comments")
    @Timed
    public String createComment(@RequestParam(required = false, defaultValue = "false") CommentDTO commentDTO,Model model) throws URISyntaxException {
        log.debug("REST request to save Comment : {}", commentDTO);
        if (commentDTO.getId() != null) {
            throw new BadRequestAlertException("A new comment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        String parseDate=commentDTO.getDateInString().replace(" ","T").concat("Z");
        
        Instant dateInstant=Instant.parse(parseDate);
        commentDTO.setDate(dateInstant);
        
        
        CommentDTO result = commentService.save(commentDTO);
        model.addAttribute("comment",result);
        return null;
    }
    
    
    
    
    
    /**
     * GET  /commentsByNeedId/:id : get the "id" comment.
     *
     * @param id the id of the commentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/commentsByNeedId/{id}")
    @Timed
    public String getCommentByNeedId(@RequestParam(required=false,defaultValue="false") @PathVariable Long id,Model model) {
        log.debug("REST request to get Comments buy needId : {}", id);
        Pageable pageable=null;
        Page<CommentDTO> result = commentService.findByNeedId(id,pageable);
        model.addAttribute("comments",result);
        return null;

    }
    
    /**
     * GET  /comments/:id : get the "id" comment.
     *
     * @param id the id of the commentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the commentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comments/{id}")
    @Timed
    public String getComment(@RequestParam(required=false,defaultValue="false") @PathVariable Long id,Model model) {
        log.debug("REST request to get Comment : {}", id);
        Optional<CommentDTO> commentDTO = commentService.findOne(id);
        model.addAttribute("comment",commentDTO);
        return null;
    }
    
    

} 