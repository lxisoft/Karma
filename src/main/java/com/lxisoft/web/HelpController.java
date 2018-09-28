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


import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.HelpService;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.HelpDTO;

import com.lxisoft.web.rest.HelpResource;
import com.lxisoft.web.rest.errors.BadRequestAlertException;


/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */
public class HelpController {
	
	private final Logger log = LoggerFactory.getLogger(HelpResource.class);

    private static final String ENTITY_NAME = "karmaHelp";

    private HelpService helpService;
    
    @Autowired
	ApprovalStatusService approvalStatusService;


    public HelpController(HelpService helpService) {
        this.helpService = helpService;
    }

    /**
     * POST  /helps : Create a new help.
     *
     * @param helpDTO the helpDTO to create
     * @return the String value
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/helps")
    @Timed
    public String createHelp(@RequestBody HelpDTO helpDTO,Model model) throws URISyntaxException {
    	
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
        HelpDTO helpdto = helpService.save(helpDTO);
        model.addAttribute("help", helpdto);
        return "help";
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

	@GetMapping("/help/{approvalStatus}")
	@Timed
	public String getAllHelpsByApprovedStatus(Pageable pageable,
			@RequestParam(required = false, defaultValue = "false") boolean eagerload,
			@PathVariable(value = "approvalStatus") String approvalStatus, Model model) {
		log.debug("request to get a page of helps");
		
		Page<HelpDTO> page = helpService.findAllHelpsByApprovedStatus(pageable,approvalStatus);
		
		List<HelpDTO> helps = page.getContent();
		
		model.addAttribute("helps", helps);
		if (approvalStatus.equals("completed"))
			return "home";
		else if (approvalStatus.equals("incompleted"))
			return "incompleted-helps";
		else
			return "home";
	}
		

}
