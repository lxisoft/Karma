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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.LoggedUserService;
import com.lxisoft.service.dto.LoggedUserDTO;

import com.lxisoft.web.rest.LoggedUserResource;
import com.lxisoft.web.rest.errors.BadRequestAlertException;


/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

@Controller
public class LoggedUserController {
	
	private final Logger log = LoggerFactory.getLogger(LoggedUserResource.class);

    private static final String ENTITY_NAME = "karmaLoggedUser";

    private final LoggedUserService loggedUserService;

    public LoggedUserController(LoggedUserService loggedUserService) {
        this.loggedUserService = loggedUserService;
    }
    
    /**
     * POST  /logged-users : Create a new loggedUser.
     *
     * @param loggedUserDTO the loggedUserDTO to create
     * 
     * @return the String value
     * 
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
     */
    @PostMapping("/logged-users")
    @Timed
    public String createLoggedUser(@ModelAttribute LoggedUserDTO loggedUserDTO,Model model) throws URISyntaxException {
        log.debug("request to save LoggedUser : {}", loggedUserDTO);
        
        if (loggedUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new loggedUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        LoggedUserDTO loggedUserDto = loggedUserService.save(loggedUserDTO);
        
        model.addAttribute("loggedUser", loggedUserDto);
        
        return "home";
    }

    /**
     * PUT  /logged-users : Updates an existing loggedUser.
     *
     * @param loggedUserDTO the loggedUserDTO to update
     * @return the String value.
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logged-users")
    @Timed
    public String updateLoggedUser(@ModelAttribute LoggedUserDTO loggedUserDTO,Model model) throws URISyntaxException {
        log.debug("request to update LoggedUser : {}", loggedUserDTO);
        if (loggedUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoggedUserDTO loggedUserDto = loggedUserService.save(loggedUserDTO);
        
        model.addAttribute("loggedUser", loggedUserDto);
        
        return "home";
    }

    /**
     * GET  /logged-users : get all the loggedUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loggedUsers in body
     */
    @GetMapping("/logged-users")
    @Timed
    public String getAllLoggedUsers(Pageable pageable,Model model) {
        log.debug("request to get a page of LoggedUsers");
        Page<LoggedUserDTO> page = loggedUserService.findAll(pageable);
        
        List<LoggedUserDTO> loggedUsers = page.getContent();
		model.addAttribute("loggedUsers", loggedUsers);
        
        return "home";
    }

    /**
     * GET  /logged-users/:id : get the "id" loggedUser.
     *
     * @param id the id of the loggedUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loggedUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/logged-users/{id}")
    @Timed
    public String getLoggedUser(@PathVariable Long id,Model model) {
        log.debug("request to get LoggedUser : {}", id);
        Optional<LoggedUserDTO> loggedUserDTO = loggedUserService.findOne(id);
        
        model.addAttribute("loggedUser", loggedUserDTO);
        
        return "home";
    }

   
    
    /**
     * GET  /logged-users/:id : get the "id" loggedUser.
     *
     * @param id the id of the loggedUserDTO rating to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loggedUserDTO, or with status 404 (Not Found)
     */
    
    @GetMapping("/logged-users/updateLoggedUserRatingById/{id}")
    @Timed
    public String updateLoggedUserRatingById(@PathVariable Long id,Model model) {
        log.debug("REST request to rate LoggedUser : {}", id);
        LoggedUserDTO loggedUserDTO = loggedUserService.findOne(id).orElse(null); 
        
        Long rating=1l;
        if(loggedUserDTO.getRating()==null)
        	loggedUserDTO.setRating(rating);	
        else
        	loggedUserDTO.setRating((loggedUserDTO.getRating())+1);
        
    	loggedUserDTO = loggedUserService.save(loggedUserDTO);
        return "home";
    }


}
