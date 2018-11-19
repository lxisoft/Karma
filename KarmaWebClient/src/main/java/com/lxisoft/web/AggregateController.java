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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.client.karma.api.AggregateResourceApi;
import com.lxisoft.client.karma.model.NeedDTO;

/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

@Controller
public class AggregateController {
	
	private final Logger log = LoggerFactory.getLogger(AggregateController.class);
	
	@Autowired
	AggregateResourceApi aggregateResourceApi;
	
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
	public String postNeed(@ModelAttribute NeedDTO needDTO,Model model) throws URISyntaxException, IllegalStateException, IOException {
		log.debug(" request to save Need : {},{}", needDTO);
		
		log.debug("save Need : {}", needDTO);
	     
	    model.addAttribute("need", aggregateResourceApi.postNeedUsingPOST(needDTO).getBody());
		return "help-post-result";

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
	
		List<NeedDTO> needs = aggregateResourceApi.getAllNeedsUsingGET(eagerload, null, null, null, null, eagerload, null, null, eagerload, eagerload, eagerload).getBody();
						
		model.addAttribute("needs", needs);
		return "home";

	}



}
