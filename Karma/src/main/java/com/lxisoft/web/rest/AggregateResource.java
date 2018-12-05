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
package com.lxisoft.web.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import io.github.jhipster.web.util.ResponseUtil;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.AggregateService;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.CommentDTO;
import com.lxisoft.service.dto.FeedDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.PostDTO;
import com.lxisoft.service.dto.RegisteredUserDTO;
import com.lxisoft.service.dto.ReplyDTO;
import com.lxisoft.service.dto.SeverityDTO;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;

/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

/**
 * REST controller for managing Need.
 */
@RestController
@RequestMapping("/api")
public class AggregateResource {
	
	private final Logger log = LoggerFactory.getLogger(AggregateResource.class);

	@Autowired
	AggregateService aggregateService;
	
	 /**
     * POST  /needs : Create a new need.
     *
     * @param needDTO the needDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new needDTO, or with status 400 (Bad Request) if the need has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping("/needs")
    @Timed
    public ResponseEntity<NeedDTO> PostNeed(@RequestBody NeedDTO needDTO) throws URISyntaxException, IOException {
        
    	log.debug("REST request to save Need : {}", needDTO);
                
        if (needDTO.getId() != null) {
            throw new BadRequestAlertException("A new need cannot already have an ID","Need","idexists");
        }        
                
       NeedDTO needDto = aggregateService.saveNeedAsPending(needDTO);
                       
       return ResponseEntity.created(new URI("/api/needs/" + needDto.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("Need",needDto.getId().toString()))
                .body(needDto);
        }
    
    /**
     * PUT  /needs : Updates an existing need.
     *
     * @param needDTO the needDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated needDTO,
     * or with status 400 (Bad Request) if the needDTO is not valid,
     * or with status 500 (Internal Server Error) if the needDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PutMapping("/needs")
    @Timed
    public ResponseEntity<NeedDTO> updateNeed(@RequestBody NeedDTO needDTO) throws URISyntaxException, IOException {
        log.debug("REST request to  resource update Need : {}", needDTO);
        if (needDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", "Need", "idnull");
        }
        NeedDTO result = aggregateService.saveNeed(needDTO);
        aggregateService.saveNeedWithApprovalStatus(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Need",needDTO.getId().toString()))
            .body(result);
    }
    
    
    /**
     * GET  /needs : get all the needs.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of needs in body
     */
    @GetMapping("/needs")
    @Timed
    public ResponseEntity<List<NeedDTO>> getAllNeeds(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Needs");
        Page<NeedDTO> page;
        if (eagerload) {
            page = aggregateService.findAllWithEagerRelationships(pageable);
        } else {
            page = aggregateService.findAllNeeds(pageable);
        }
              
        //Page<NeedDTO> page1 = new PageImpl<NeedDTO>(needs, pageable, needs.size());
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/needs?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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

	@GetMapping("/needs/getAllNeedsByApprovedStatus/{approvalStatus}")
	@Timed
	public ResponseEntity<List<NeedDTO>> getAllNeedsByApprovedStatus(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "approvalStatus") String approvalStatus) {
		log.debug("request to get a page of Needs");
		Page<NeedDTO> page;
		if (eagerload) {
			page = aggregateService.findAllWithEagerRelationships(pageable);
		} else {
			page = aggregateService.findAllNeedsByApprovedStatus(pageable, approvalStatus);
		}
				
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/needs?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		
	}

    /**
     * GET  /needs/:id : get the "id" need.
     *
     * @param id the id of the needDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the needDTO, or with status 404 (Not Found)
     */
    @GetMapping("/needs/{id}")
    @Timed
    public ResponseEntity<NeedDTO> getNeed(@PathVariable Long id) {
        log.debug("REST request to get Need : {}", id);
        
        Optional<NeedDTO> needDTO = aggregateService.findOneNeed(id);
       
        return ResponseUtil.wrapOrNotFound(needDTO);
    }

    /**
     * DELETE  /needs/:id : delete the "id" need.
     *
     * @param id the id of the needDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/needs/deleteNeed/{id}")
    @Timed
    public ResponseEntity<Void> deleteNeed(@PathVariable Long id) {
        log.debug("REST request to delete Need : {}", id);
        aggregateService.deleteNeed(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("Need", id.toString())).build();
    }
    
    /**
    *
    * @param severityId the id of the needDTO to retrieve
    * @return the ResponseEntity with status 200 (OK) and with body the needDTO, or with status 404 (Not Found)
    */
   @GetMapping("/needs/getNeedsBySeverityId/{severityId}")
   @Timed
   public ResponseEntity<List<NeedDTO>> getAllNeedsBySeverityId(Pageable pageable,@PathVariable Long severityId, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
       log.debug("REST request to get Need : {}", severityId);
       
       Page<NeedDTO> page;
       
       if (eagerload) {
           page = aggregateService.findAllWithEagerRelationships(pageable);
       } else {
           page = aggregateService.findAllNeedsBySeverityId(pageable, severityId);
       }
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/severitylevel/?eagerload=%b", eagerload));
       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);    
       
       }
   
   
   /**
    * GET  /approval-statuses : get all the approvalStatuses.
    *
    * @param pageable the pagination information
    * @return the ResponseEntity with status 200 (OK) and the list of approvalStatuses in body
    */
   @GetMapping("/approval-statuses")
   @Timed
   public ResponseEntity<List<ApprovalStatusDTO>> getAllApprovalStatuses(Pageable pageable) {
       log.debug("REST request to get a page of ApprovalStatuses");
       Page<ApprovalStatusDTO> page = aggregateService.findAllApprovalStatuses(pageable);
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/approval-statuses");
       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
   
   
   /**
    * GET  /approval-statuses/:id : get the "id" approvalStatus.
    *
    * @param id the id of the approvalStatusDTO to retrieve
    * @return the ResponseEntity with status 200 (OK) and with body the approvalStatusDTO, or with status 404 (Not Found)
    */
   @GetMapping("/approval-statuses/{id}")
   @Timed
   public ResponseEntity<ApprovalStatusDTO> getApprovalStatus(@PathVariable Long id) {
       log.debug("REST request to get ApprovalStatus : {}", id);
       Optional<ApprovalStatusDTO> approvalStatusDTO = aggregateService.findOneApprovalStatus(id);
       return ResponseUtil.wrapOrNotFound(approvalStatusDTO);
   }
   
   /**
    * GET  /categories : get all the categories.
    *
    * @param pageable the pagination information
    * @return the ResponseEntity with status 200 (OK) and the list of categories in body
    */
   @GetMapping("/categories")
   @Timed
   public ResponseEntity<List<CategoryDTO>> getAllCategories(Pageable pageable) {
       log.debug("REST request to get a page of Categories");
       Page<CategoryDTO> page = aggregateService.findAllCategories(pageable);
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/categories");
       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
   
   /**
    * POST  /helps : Create a new help.
    *
    * @param helpDTO the helpDTO to create
    * @return the ResponseEntity with status 201 (Created) and with body the new helpDTO, or with status 400 (Bad Request) if the help has already an ID
    * @throws URISyntaxException if the Location URI syntax is incorrect
    * @throws IOException 
    */
   @PostMapping("/helps")
   @Timed
   public ResponseEntity<HelpDTO> helpNeedy(@RequestBody HelpDTO helpDTO) throws URISyntaxException, IOException {
       log.debug("REST request to save Help : {}", helpDTO);
       if (helpDTO.getId() != null) {
           throw new BadRequestAlertException("A new help cannot already have an ID","Help", "idexists");
       }
            
       HelpDTO helpDto = aggregateService.saveHelpAsIncomplete(helpDTO);
       
       return ResponseEntity.created(new URI("/api/helps/createHelpWithMedia" + helpDto.getId()))
		            .headers(HeaderUtil.createEntityCreationAlert("Help", helpDto.getId().toString()))
		            .body(helpDto);
   }
   
   
   /**
    * PUT  /helps : Updates an existing help.
    *
    * @param helpDTO the helpDTO to update
    * @return the ResponseEntity with status 200 (OK) and with body the updated helpDTO,
    * or with status 400 (Bad Request) if the helpDTO is not valid,
    * or with status 500 (Internal Server Error) if the helpDTO couldn't be updated
    * @throws URISyntaxException if the Location URI syntax is incorrect
    * @throws IOException 
    */
   @PutMapping("/helps")
   @Timed
   public ResponseEntity<HelpDTO> updateHelp(@RequestBody HelpDTO helpDTO) throws URISyntaxException, IOException {
       log.debug("REST request to update Help : {}", helpDTO);
       if (helpDTO.getId() == null) {
           throw new BadRequestAlertException("Invalid id","Help", "idnull");
       }
       HelpDTO result = aggregateService.saveHelpAsComplete(helpDTO);
       return ResponseEntity.ok()
           .headers(HeaderUtil.createEntityUpdateAlert("Help", helpDTO.getId().toString()))
           .body(result);
   }


   /**
    * GET  /helps : get all the helps.
    *
    * @param pageable the pagination information
    * @return the ResponseEntity with status 200 (OK) and the list of helps in body
    */
   @GetMapping("/helps")
   @Timed
   public ResponseEntity<List<HelpDTO>> getAllHelps(Pageable pageable) {
       log.debug("REST request to get a page of Helps");
       Page<HelpDTO> page = aggregateService.findAllHelps(pageable);
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/helps");
       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }


   /**
    * GET  /helps/:id : get the "id" help.
    *
    * @param id the id of the helpDTO to retrieve
    * @return the ResponseEntity with status 200 (OK) and with body the helpDTO, or with status 404 (Not Found)
    */
   @GetMapping("/helps/{id}")
   @Timed
   public ResponseEntity<HelpDTO> getHelp(@PathVariable Long id) {
       log.debug("REST request to get Help : {}", id);
             
       Optional<HelpDTO> helpDTO = aggregateService.findOneHelp(id);     
       return ResponseUtil.wrapOrNotFound(helpDTO);
   }
   
   /**
    * DELETE  /helps/:id : delete the "id" help.
    *
    * @param id the id of the helpDTO to delete
    * @return the ResponseEntity with status 200 (OK)
    */
   @DeleteMapping("/helps/{id}")
   @Timed
   public ResponseEntity<Void> deleteHelp(@PathVariable Long id) {
       log.debug("REST request to delete Help : {}", id);
       aggregateService.deleteHelp(id);
       return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("Help",id.toString())).build();
   }
   
   /**
    * GET  /helps : get all the helps.
    *
    * @param pageable the pagination information
    * @return the ResponseEntity with status 200 (OK) and the list of helps in body
    */
   @GetMapping("/helps/getAllHelpsByApprovedStatus/{approvalStatus}")
   @Timed
   public ResponseEntity<List<HelpDTO>> getAllHelpsByApprovedStatus(Pageable pageable,@PathVariable String approvalStatus) {
       log.debug("REST request to get a page of Helps");
       Page<HelpDTO> page = aggregateService.findAllHelpsByApprovedStatus(pageable,approvalStatus);           
       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/helps");
       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
   }
	
    
    /**
     * GET  /helps : get all the helps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of helps in body
     */
    @GetMapping("/helps/getAllCompletedHelpsByfulfilledNeedId/{fulfilledNeedId}")
    @Timed
    public ResponseEntity<List<HelpDTO>> getAllCompletedHelpsByfulfilledNeedId(Pageable pageable,@PathVariable Long fulfilledNeedId) {
        log.debug("REST request to get a page of Helps");
        Page<HelpDTO> page = aggregateService.findAllCompletedHelpsByfulfilledNeedId(pageable,fulfilledNeedId);        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/helps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * POST  /user-checks : Create a new userCheck.
     *
     * @param userCheckDTO the userCheckDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCheckDTO, or with status 400 (Bad Request) if the userCheck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks")
    @Timed
    public ResponseEntity<UserCheckDTO> createUserCheck(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException {
        log.debug("REST request to save UserCheck : {}", userCheckDTO);
        if (userCheckDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCheck cannot already have an ID", "UserCheck", "idexists");
        }
        UserCheckDTO result = aggregateService.saveUserCheck(userCheckDTO);
        return ResponseEntity.created(new URI("/api/user-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("UserCheck", result.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /user-checks : Updates an existing userCheck.
     *
     * @param userCheckDTO the userCheckDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userCheckDTO,
     * or with status 400 (Bad Request) if the userCheckDTO is not valid,
     * or with status 500 (Internal Server Error) if the userCheckDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-checks")
    @Timed
    public ResponseEntity<UserCheckDTO> updateUserCheck(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException {
        log.debug("REST request to update UserCheck : {}", userCheckDTO);
        if (userCheckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", "UserCheck", "idnull");
        }
        UserCheckDTO result = aggregateService.saveUserCheck(userCheckDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("UserCheck", userCheckDTO.getId().toString()))
            .body(result);
    }
    
       
    /**
     * GET  /user-checks : get all the userChecks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userChecks in body
     */
    @GetMapping("/user-checks/getAllUserChecksByCheckedNeedId/{checkedNeedId}")
    @Timed
    public ResponseEntity<List<UserCheckDTO>> getAllUserChecksByCheckedNeedId(Pageable pageable,@PathVariable Long checkedNeedId) {
        log.debug("REST request to get a page of UserChecks");
        Page<UserCheckDTO> page = aggregateService.findAllUserChecksByCheckedNeedId(pageable,checkedNeedId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-checks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }   
    
    /**
     * POST  /user-checks : checking the genuineness.
     *
     * @param userCheckDTO the userCheckDTO to create
     * 
     * @param voteType the voteType of the userCheckDto
     * 
     * @return the ResponseEntity with status 201 (Created) and with body the new userCheckDTO, or with status 400 (Bad Request) if the userCheck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks/markingGenuinenes")
    @Timed
    public ResponseEntity<UserCheckDTO> markingGenuinenes(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException,IOException {
        log.debug("REST request to save UserCheck : {}", userCheckDTO);
            
         UserCheckDTO result=aggregateService.markingGenuinenes(userCheckDTO);
                         
        return ResponseEntity.created(new URI("/api/user-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("UserCheck", result.getId().toString()))
            .body(result);
        }

    /**
     * POST  /user-checks : create  userCheck with positive vote type
     *
     * @param userCheckDTO the userCheckDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCheckDTO, or with status 400 (Bad Request) if the userCheck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks/like")
    @Timed
    public ResponseEntity<UserCheckDTO> doLike(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException,IOException {
        log.debug("REST request to save UserCheck as like  : {}", userCheckDTO);
        if (userCheckDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCheck cannot already have an ID", "UserCheck", "idexists");
        }
              
        UserCheckDTO result = aggregateService.saveUserCheckLike(userCheckDTO).get();

        return ResponseEntity.created(new URI("/api/user-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("UserCheck", result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /user-checks : create user  check  with  negative vote type
     *
     * @param userCheckDTO the userCheckDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCheckDTO, or with status 400 (Bad Request) if the userCheck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks/dislike")
    @Timed
    public ResponseEntity<UserCheckDTO> doDislike(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException,IOException {
        log.debug("REST request to save UserCheck as dislike  : {}", userCheckDTO);
        if (userCheckDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCheck cannot already have an ID", "UserCheck", "idexists");
        }      
        UserCheckDTO result = aggregateService.saveUserCheckDislike(userCheckDTO).get() ;
        return ResponseEntity.created(new URI("/api/user-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("UserCheck", result.getId().toString()))
            .body(result);
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
 	public ResponseEntity<CommentDTO> addComment(@RequestBody CommentDTO commentDTO) throws URISyntaxException,IOException {
 		log.debug("REST request to save Comment : {}", commentDTO);
 		if (commentDTO.getId() != null) {
 			throw new BadRequestAlertException("A new comment cannot already have an ID", "Comment", "idexists");
 		}		
 		CommentDTO result = aggregateService.saveComment(commentDTO);
 		return ResponseEntity.created(new URI("/api/comments/" + result.getId()))
 				.headers(HeaderUtil.createEntityCreationAlert("Comment", result.getId().toString())).body(result);
 	}

 	/**
 	 * GET /commentsByNeedId/:id : get the comment by needId.
 	 *
 	 * @param id
 	 *            the id of the commentDTO to retrieve
 	 * @return the ResponseEntity with status 200 (OK) and with body the
 	 *         commentDTO, or with status 404 (Not Found)
 	 */
 	@GetMapping("/commentsByNeedId/{needId}")
 	@Timed
 	public ResponseEntity<List<CommentDTO>> getAllCommentsByNeedId(Pageable pageable,@PathVariable Long needId) {
 		log.debug("REST request to get Comments buy needId : {}", needId);
 		Page<CommentDTO> page = aggregateService.findAllCommentsByNeedId(pageable,needId);
 		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/commentsByNeedId");
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
 	@GetMapping("/CommentsByHelpId/{helpId}")
 	@Timed
 	public ResponseEntity<List<CommentDTO>> getAllCommentsByHelpId(Pageable pageable, @PathVariable Long helpId) {
 		log.debug("REST request to get a page of Comments  by help id");
 		Page<CommentDTO> page = aggregateService.findAllCommentsByHelpId(pageable, helpId);
 		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getAllCommentsByHelpId");
 		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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
     public ResponseEntity<ReplyDTO> addReply(@RequestBody ReplyDTO replyDTO) throws URISyntaxException {
         log.debug("REST request to save Reply : {}", replyDTO);
         if (replyDTO.getId() != null) {
             throw new BadRequestAlertException("A new reply cannot already have an ID", "Reply", "idexists");
         }      
         ReplyDTO result = aggregateService.saveReply(replyDTO);
         return ResponseEntity.created(new URI("/api/replies/" + result.getId()))
             .headers(HeaderUtil.createEntityCreationAlert("Reply", result.getId().toString()))
             .body(result);
     }
     
     /**
      * GET  /replies : get all the replies.
      *
      * @param pageable the pagination information
      * @return the ResponseEntity with status 200 (OK) and the list of replies in body
      */
     @GetMapping("/getAllRepliesByCommentId/{commentId}")
     @Timed
     public ResponseEntity<List<ReplyDTO>> getAllRepliesByCommentId(Pageable pageable,@PathVariable Long commentId) {
         log.debug("REST request to get a page of Replies");
         Page<ReplyDTO> page = aggregateService.findAllRepliesByCommentId(pageable,commentId);
         HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/replies");
         return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
     }
     
    /* *//**
      * GET  /severities : get all the severities.
      *
      * @param pageable the pagination information
      * @return the ResponseEntity with status 200 (OK) and the list of severities in body
      *//*
     @GetMapping("/severities")
     @Timed
     public ResponseEntity<List<SeverityDTO>> getAllSeverities(Pageable pageable) {
         log.debug("REST request to get a page of Severities");
         Page<SeverityDTO> page = aggregateService.findAllSeverities(pageable);
         HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(new PageImpl<>(null),"/api/severities");
         return ResponseEntity.ok().headers(headers).body(page.getContent());
     } */
     
     
     
  // Code:Ruhail

 	/**
 	 * POST /postuserpost : Create a new post.
 	 *
 	 * @param postDTO
 	 *            the postDTO to create
 	 * 
 	 * @param attachedFiles
 	 *            the files attached with postDTO
 	 * @return the ResponseEntity with status 201 (Created) and with body the
 	 *         new postDTO, or with status 400 (Bad Request) if the newsFeed has
 	 *         already an ID
 	 * @throws URISyntaxException
 	 *             if the Location URI syntax is incorrect
 	 */

 	@PostMapping("/post-userpost")
 	@Timed
 	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO)
 			throws URISyntaxException, IllegalStateException, IOException {
 		// @RequestParam MultipartFile[] attachedFiles
 		log.debug("REST request to save NewsFeed : {}", postDTO);
 		if (postDTO.getId() != null) {
 			throw new BadRequestAlertException("Invalid id", "Post", "id not null");
 		}
 		// postDTO.setAttachedFiles(attachedFiles);
 		PostDTO result = aggregateService.savePost(postDTO);
 		return ResponseEntity.created(new URI("/api/post-userpost/" + result.getId()))
 				.headers(HeaderUtil.createEntityCreationAlert("karmapost", result.getId().toString())).body(result);
 	}

 	// Code:End
 	// Code:Ruhail
 	/**
 	 * GET /postss : get all the posts.
 	 *
 	 * @param pageable
 	 *            the pagination information
 	 * @return the ResponseEntity with status 200 (OK) and the list of posts in
 	 *         body
 	 */
 	@GetMapping("/posts")
 	@Timed
 	public ResponseEntity<List<PostDTO>> getAllPosts(Pageable pageable) {
 		log.debug("REST request to get a page of Posts");
 		Page<PostDTO> page = aggregateService.findAllPosts(pageable);
 		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/posts");
 		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
 	}
 	// Code:End

 	 //anjali
    
    /**
     * POST  /feeds : Create a new feed.
     *
     * @param feedDTO the feedDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feedDTO, or with status 400 (Bad Request) if the feed has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping("/feeds")
    @Timed
    public ResponseEntity<FeedDTO> PostFeed(@RequestBody FeedDTO feedDTO) throws URISyntaxException, IOException {
        
    	log.debug("REST request to save Feed : {}", feedDTO);
                
        if (feedDTO.getId() != null) {
            throw new BadRequestAlertException("A new feed cannot already have an ID","Feed","idexists");
        }        
                
       FeedDTO feedDto = aggregateService.saveFeed(feedDTO);
                       
       return ResponseEntity.created(new URI("/api/feeds/" + feedDto.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("Feed",feedDto.getId().toString()))
                .body(feedDto);
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
        Page<FeedDTO> page = aggregateService.findAllFeeds(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/feeds");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
     
    /**
     * GET  /feeds : get all the feeds by registered user id.
     *
     * @param pageable the pagination information,registeredUserId to get feeds of a particular user
     * @return the ResponseEntity with status 200 (OK) and the list of feeds in body
     */
    @GetMapping("/feeds/getAllFeedsByRegisteredUserId/{registeredUserId}")
    @Timed
    public ResponseEntity<List<FeedDTO>> getAllFeedsByRegisteredUserId(Pageable pageable,@PathVariable Long registeredUserId) {
        log.debug("REST request to get a page of Feeds");
        Page<FeedDTO> page = aggregateService.findAllFeedsByRegisteredUserId(pageable,registeredUserId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getAllFeedsByRegisteredUserId");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    //anjali
    
     // Code:End
 	// Code:Ruhail
 	@GetMapping("/post/{id}")
 	@Timed
 	public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
 		log.debug("REST request to get Post : {}", id);

 		Optional<PostDTO> postDTO = aggregateService.findOnePost(id);

 		return ResponseUtil.wrapOrNotFound(postDTO);
 	}
 	// Code:End


 	//neeraja
       
        /**
	     * GET  /registeredUser/:id : get the registeredUserDto  with id.
	     *
	     * @param id the id of the registeredUserDto to retrieve
	     * @return the ResponseEntity with status 200 (OK) and with body the helpDTO, or with status 404 (Not Found)
	     */
	    @GetMapping("/registeredUser/{id}")
	    @Timed
	    public ResponseEntity<RegisteredUserDTO> getOneRegisteredUser(@PathVariable Long id) {
	        log.debug("REST request to get registeredUserDto : {}", id);
	              
	        Optional<RegisteredUserDTO> registeredUserDTO = aggregateService.findOneRegisteredUser(id);     
	        return ResponseUtil.wrapOrNotFound(registeredUserDTO);
	    }   
    
    // neeraja end
    
    
  //sooraj
    /**
     * followOrUnfollowRegisteredUser
     * 
     * @param followingUserId
     * 
     * @param registeredUserId
     * 
     * @return the Boolean value
     */
    
   /* @PostMapping("/followOrUnfollowRegisteredUser/{followingUserId}/{registeredUserId}")
    public Boolean followOrUnfollowRegisteredUser(@PathVariable Long followingUserId,@PathVariable Long registeredUserId)
    {

     log.debug("user id of follower :"+followingUserId);
     log.debug("user id of registeredUserId:"+registeredUserId);
     Pageable pageable=null;
     Boolean result=aggregateService.followOrUnfollowRegisteredUser(followingUserId,registeredUserId);
    
     return result;

    }*/
    
  //sooraj end

	  //anjali
	    
	    /**
	     * POST  /media : Create a new media.
	     *
	     * @param mediaDTO the mediaDTO to create
	     * @return the ResponseEntity with status 201 (Created) and with body the new mediaDTO, or with status 400 (Bad Request) if the media has already an ID
	     * @throws URISyntaxException if the Location URI syntax is incorrect
	     * @throws IOException 
	     */
	    @PostMapping("/media")
	    @Timed
	    public ResponseEntity<MediaDTO> PostMedia(@RequestBody MediaDTO mediaDTO) throws URISyntaxException, IOException {
	        log.debug("REST request to save Media : {}", mediaDTO);
	        if (mediaDTO.getId() != null) {
	            throw new BadRequestAlertException("A new media cannot already have an ID", "media", "idexists");
	        }
	        MediaDTO result = aggregateService.saveMedia(mediaDTO);
	        return ResponseEntity.created(new URI("/api/media/" + result.getId()))
	            .headers(HeaderUtil.createEntityCreationAlert("media", result.getId().toString()))
	            .body(result);
	    }
	    
	    /**
	    *
	    * @param needId the id of the mediaDTO to retrieve
	    * @return the ResponseEntity with status 200 (OK) and with body the mediaDTO, or with status 404 (Not Found)
	    */
	   @GetMapping("/media/getAllMediaUrlsByNeedId/{needId}")
	   @Timed
	   public ResponseEntity<List<MediaDTO>> getAllMediaUrlsByNeedId(@PathVariable Long needId,Pageable pageable) {
	       log.debug("REST request to get a page of Media{}",needId);
	       Page<MediaDTO> page = aggregateService.findAllUrlByNeedId(needId,pageable);
	       HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/media/getAllMediaUrlsByNeedId/");
	       return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	   }
	    
	    //anjali




}
