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

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.HelpService;
import com.lxisoft.service.MediaService;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.web.rest.HelpResource;
import com.lxisoft.web.rest.errors.BadRequestAlertException;

/**
 * TODO Provide a detailed description here
 * 
 * @author Sarangi Balu sarangibalu, sarangibalu.a@lxisoft.com
 */

@Controller
public class HelpController {

	private final Logger log = LoggerFactory.getLogger(HelpResource.class);

	private static final String ENTITY_NAME = "karmaHelp";

	private HelpService helpService;

	@Autowired
	ApprovalStatusService approvalStatusService;
	
	@Autowired
	MediaService mediaService;

	public HelpController(HelpService helpService) {
		this.helpService = helpService;
	}

	/**
	 * POST /helps : Create a new help.
	 *
	 * @param helpDTO
	 *            the helpDTO to create
	 * @return the String value
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PostMapping("/helps")
	@Timed
	public String createHelp(@ModelAttribute HelpDTO helpDTO,@RequestParam MultipartFile[] files,Model model) throws URISyntaxException, IOException {

		log.debug("REST request to save Help : {}", helpDTO);
		if (helpDTO.getId() != null) {
			throw new BadRequestAlertException("A new help cannot already have an ID", ENTITY_NAME, "idexists");
		}
		if (helpDTO.getApprovalStatusId() == null) {

			Optional<ApprovalStatusDTO> approvalStatus = approvalStatusService.findByStatus("incompleted");

			Long id = approvalStatus.get().getId();
			log.debug("***************{}" + id);
			helpDTO.setApprovalStatusId(approvalStatus.get().getId());
		}

		String parsedDate = helpDTO.getTimeInString().replaceAll(" ", "T").concat("Z");

		// creates a date instance of type instant from a string
		helpDTO.setTime(Instant.parse(parsedDate));

		HelpDTO helpdto = helpService.save(helpDTO);
		
		//
		for(MultipartFile file:files){
			
			log.info("********helpcontroller",file.getName());
			
			 MediaDTO mediaDTO=new MediaDTO();
	    	 
	    	 mediaDTO.setFile(file);
	    	 
	    	 mediaDTO.setHelpId(helpdto.getId());
	         
	    	 mediaService.save(mediaDTO);
	        
		}
		//
		
		model.addAttribute("help", helpdto);
		model.addAttribute("message", "submitted");
		return "approve-decline";
	}

	/**
	 * PUT /helps : Updates an existing need.
	 *
	 * @param helpDTO
	 *            the needDTO to update
	 * @return the string value, or with status 400 (Bad Request) if the needDTO
	 *         is not valid, or with status 500 (Internal Server Error) if the
	 *         needDTO couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 * @throws IOException 
	 */
	@PutMapping("/helps")
	@Timed
	public String updateHelp(@ModelAttribute HelpDTO helpDTO, Model model) throws URISyntaxException, IOException {
		log.debug("request to update Need : {}", helpDTO);
		if (helpDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}

		HelpDTO help = helpService.save(helpDTO);
		model.addAttribute("help", help);
		model.addAttribute("message", approvalStatusService.findOne(help.getApprovalStatusId()).orElse(null));
		return "approve-decline";
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

	@GetMapping("/helps/{approvalStatus}")
	@Timed
	public String getAllHelpsByApprovedStatus(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "approvalStatus") String approvalStatus, Model model) {
		log.debug("request to get a page of helps");

		Page<HelpDTO> page = helpService.findAllHelpsByApprovedStatus(pageable, approvalStatus);

		List<HelpDTO> helps = page.getContent();

		model.addAttribute("helps", helps);

		if (approvalStatus.equals(("completed")))
			return "completed-helps";
		else if (approvalStatus.equals(("incompleted")))
			return "incompleted-helps";
		else
			return "home";
	}

	/**
	 * GET /help-need/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("/help-need/{id}")
	@Timed
	public String getHelpWithNeed(@PathVariable(value = "id") Long id, Model model) {
		log.debug("request to get Need : {}", id);

		HelpDTO help = new HelpDTO();
		help.setFulfilledNeedId(id);

		model.addAttribute("help", help);

		return "help-submission";
	}

	/**
	 * GET /help-need/:id : get the "id" need.
	 *
	 * @param id
	 *            the id of the needDTO to retrieve
	 * @return the string value
	 */
	@GetMapping("helps/incomplete/{id}")
	@Timed
	public String getHelpForApproval(@PathVariable(value = "id") Long id, Model model) {

		log.debug("request to get Need : {}", id);

		HelpDTO help = helpService.findOne(id).orElse(null);

		List<ApprovalStatusDTO> approvalStatuses = approvalStatusService.findAll(PageRequest.of(0, 20)).getContent();
		model.addAttribute("help", help);
		model.addAttribute("approvalStatuses", approvalStatuses);

		return "incompleted-help";
	}

}
