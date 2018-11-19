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
import org.springframework.http.ResponseEntity;
import io.github.jhipster.web.util.ResponseUtil;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.AggregateService;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.NeedDTO;
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
        log.debug("REST request to update Need : {}", needDTO);
        if (needDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", "Need", "idnull");
        }
        NeedDTO result = aggregateService.saveNeed(needDTO);
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
    @DeleteMapping("/needs/{id}")
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



}
