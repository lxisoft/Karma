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
package com.lxisoft.web;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.client.karma.model.ApprovalStatusDTO;

import com.lxisoft.client.karma.model.NeedDTO;

import com.lxisoft.client.karma.api.ApprovalStatusResourceApi;

import com.lxisoft.client.karma.api.NeedResourceApi;

import com.lxisoft.web.rest.errors.BadRequestAlertException;

/**
 * TODO Provide a detailed description here
 * 
 * @author Sarangi Balu sarangibalu, sarangibalu.a@lxisoft.com
 */
@Controller
public class NeedController {

	private final Logger log = LoggerFactory.getLogger(NeedController.class);

	private static final String ENTITY_NAME = "karmaNeed";
	
	@Autowired
	NeedResourceApi needResourceApi;

	@Autowired
	ApprovalStatusResourceApi approvalStatusResourceApi;
	

	/**
	 * POST /needs : Create a new need.
	 *
	 * @param needDTO
	 *            the needDTO to create
	 * @return the string value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@PostMapping("/needs")
	@Timed
	public String createNeed(@ModelAttribute NeedDTO needDTO,Model model) throws URISyntaxException, IllegalStateException, IOException {
		log.debug(" request to save Need : {}", needDTO);
				
	    //NeedDTO need = needResourceApi.createNeedUsingPOST(needDTO).getBody();
	     
	    model.addAttribute("need", needResourceApi.createNeedUsingPOST(needDTO).getBody());
		return "help-post-result";

	}
	/**
	 * PUT /needs : Updates an existing need.
	 *
	 * @param needDTO
	 *            the needDTO to update
	 * @return the string value, or with status 400 (Bad Request) if the needDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         needDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PutMapping("/needs")
	@Timed
	public String updateNeed(@ModelAttribute NeedDTO needDTO,Model model) throws URISyntaxException, IOException {
		
		log.debug("request to update Need : {}", needDTO);
		
		NeedDTO needDto = needResourceApi.updateNeedUsingPUT(needDTO).getBody();
		
		ApprovalStatusDTO approvalStatusDTO=approvalStatusResourceApi.getApprovalStatusUsingGET(needDTO.getApprovalStatusId()).getBody();
		
		model.addAttribute("need", needDto);
		model.addAttribute("message",approvalStatusDTO );
		return "approve-decline";
	}

	/**
	 * GET /needs : get all the needs.
	 *
	 * @param pageable
	 *            the pagination information
	 * @param eagerload
	 *            flag to eager load entities from relationships (This is
	 *            applicable for many-to-many)
	 * @return the string value
	 */
	@GetMapping("/needs")
	@Timed
	public String getAllNeeds(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload, Model model) {
		log.debug("request to get a page of Needs");
	
		List<NeedDTO> needs = needResourceApi.getAllNeedsUsingGET(eagerload, null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
				
		model.addAttribute("needs", needs);
		return "home";

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

	@GetMapping("/home/{approvalStatus}")
	@Timed
	public String getAllNeedsByApprovedStatus(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "approvalStatus") String approvalStatus, Model model) {
		log.debug("request to get a page of Needs");
		
		List<NeedDTO> needs=null; //= needResourceApi.getAllNeedsByApprovedStatusUsingGET(pageable, approvalStatus).getBody();
					
		model.addAttribute("needs", needs);
		
		if (approvalStatus.equals("approved"))
			return "home";
		else if (approvalStatus.equals("pending"))
			return "pending-requests";
		else
			return "home";
	}

	/**
	 * GET /needs/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/needs/statuses/{id}")
	@Timed
	public String getNeedWithStatusesById(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);
		
		//Pageable pageable = PageRequest.of(0, 20);
		
		NeedDTO needDTO =  needResourceApi.getNeedUsingGET(id).getBody();
		List<ApprovalStatusDTO> approvalStatusDTOs =  approvalStatusResourceApi.getAllApprovalStatusesUsingGET(id, null, null, null, null, null, null, null, null, null).getBody();  
		
		model.addAttribute("need", needDTO);
		model.addAttribute("approvalStatuses",approvalStatusDTOs);
		
		//model.addAttribute("approvalStatuses", approvalStatusService.findAll(pageable).getContent());
		return "pending-need";
	}

	/**
	 * GET /needs/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/needs/{id}")
	@Timed
	public String getNeed(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);

		NeedDTO needDTO =  needResourceApi.getNeedUsingGET(id).getBody();

		model.addAttribute("need", needDTO);

		return "need";
	}

}
