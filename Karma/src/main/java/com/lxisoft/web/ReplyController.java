package com.lxisoft.web;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.ReplyService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.ReplyDTO;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.models.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Reply.
 */

/**
* TODO Provide a detailed description here
* 
* @author Neeraja.M, neeraja.m@lxisoft.com
*/
@Controller
public class ReplyController {

    private final Logger log = LoggerFactory.getLogger(ReplyController.class);

    private static final String ENTITY_NAME = "karmaReply";

  /*  @Autowired
	ReplyResourceApi replyResourceApi;

	@Autowired
	ApprovalStatusResourceApi approvalStatusResourceApi;
    */
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
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

	public String createReply(@ModelAttribute ReplyDTO replyDTO,Model model) throws URISyntaxException, IllegalStateException, IOException {
		log.debug(" request to save Reply : {}", replyDTO);

	   // ReplyDTO reply = replyResourceApi.createReplyUsingPOST(replyDTO).getBody();
	     
	   // model.addAttribute("reply",reply);
		return "help-post-result";

	}
    
    
	
	/**
	 * PUT /reply : Updates an existing reply.
	 *
	 * @param replyDTO
	 *            the replyDTO to update
	 * @return the string value, or with status 400 (Bad Request) if the replyDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         replyDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PutMapping("/replies")
	@Timed

	public String updateReply(@ModelAttribute ReplyDTO replyDTO, Model model) throws URISyntaxException, IOException {
		
		log.debug("request to update Reply : {}", replyDTO);

		//ReplyDTO needDto = needResourceApi.updateNeedUsingPUT(replyDTO).getBody();
		
		//ApprovalStatusDTO approvalStatusDTO=approvalStatusResourceApi.getApprovalStatusUsingGET(needDTO.getApprovalStatusId()).getBody();
		
		//model.addAttribute("reply", replyDto);
		//model.addAttribute("message",approvalStatusDTO );
		return "approve-decline";
	}
	
	
	
	
	
	/**
	 * GET /replies : get all the replies.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/replies")
	@Timed

	public String getAllReplies(Pageable pageable,

			@RequestParam(required = false, defaultValue = "false") boolean eagerload, Model model) {
		log.debug("request to get a page of replies");
	
		//List<ReplyDTO> replies = needResourceApi.getAllReplyUsingGET(eagerload, null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
				
		//model.addAttribute("replies", replies);
		return "home";

	}
	
	
	
	/**
	 * GET /replies/:id : get the reply by "id".
	 *
	 * @param id
	 *            the id of the replyDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/replies/{id}")
	@Timed

	public String getReply(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get reply by id : {}", id);


		//ReplyDTO replyDTO =  needResourceApi.getNeedUsingGET(id).getBody();

		//model.addAttribute("reply", replyDTO);

		return "reply";
	}

    

	
	/**
    * GET  /replies : get all the replies.
    *
    * @param pageable the pagination information
    * @return the ResponseEntity with status 200 (OK) and the list of replies in body
    */
   @GetMapping("/getAllRepliesByCommentId/{commentId}")
   @Timed
   public String getAllRepliesByCommentId(Pageable pageable,@PathVariable Long commentId) {
       log.debug("REST request to get a page of Replies");
       
       
       
     //List<ReplyDTO> replies = needResourceApi.getAllReplyUsingGET(eagerload, null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
		
     		//model.addAttribute("replies", replies);
       
       
       
     
       
       return "replies";
   }   

    
    
}