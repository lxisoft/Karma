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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.UserCheckService;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.web.rest.errors.BadRequestAlertException;

/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */
@Controller
public class UserCheckController {
	
	private final Logger log = LoggerFactory.getLogger(UserCheckController.class);

    private static final String ENTITY_NAME = "karmaUserCheck";

    @Autowired
    private final UserCheckService userCheckService;

    public UserCheckController(UserCheckService userCheckService) {
        this.userCheckService = userCheckService;
    }
    
    /**
     * POST  /user-checks : Create a new userCheck.
     *
     * @param userCheckDTO the userCheckDTO to create
     * @return the String value
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks")
    @Timed
    public String createUserCheck(@ModelAttribute UserCheckDTO userCheckDTO,Model model) throws URISyntaxException {
        log.debug("REST request to save UserCheck : {}", userCheckDTO);
        if (userCheckDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCheckDTO userCheck = userCheckService.save(userCheckDTO);
       model.addAttribute("userCheck",userCheck);
       return "home";
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
    public String updateUserCheck(@ModelAttribute UserCheckDTO userCheckDTO,Model model) throws URISyntaxException {
        log.debug("REST request to update UserCheck : {}", userCheckDTO);
        if (userCheckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserCheckDTO userCheck = userCheckService.save(userCheckDTO);
        model.addAttribute("userCheck",userCheck);
        return "home";
    }
    
    /**
     * POST  /user-checks : checking the genuineness.
     *
     * @param userCheckDTO the userCheckDTO to create
     * @return the String value
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks/markingGenuinenes")
    @Timed
    public String markingGenuinenes(@ModelAttribute UserCheckDTO userCheckDTO,Model model) throws URISyntaxException {
        log.debug("request to save UserCheck : {}", userCheckDTO);
               
        UserCheckDTO result=null;
        
        UserCheckDTO usrCheckDtoObject=userCheckService.findByCategoryAndCheckedNeedIdAndCheckedUserId(userCheckDTO.getCategory(),userCheckDTO.getCheckedNeedId(),userCheckDTO.getCheckedUserId()).orElse(null);
         
        if((userCheckDTO.getIsGenuine()==true) && (usrCheckDtoObject==null))
        {
        	userCheckDTO.setVoteType("postive");
        	result = userCheckService.save(userCheckDTO);
        }
        	        	
       else if((userCheckDTO.getIsGenuine()==true) && (usrCheckDtoObject!=null))
        {
    	   usrCheckDtoObject.setVoteType("postive");
           result=userCheckService.save(usrCheckDtoObject);
        }
        
       else if((userCheckDTO.getIsGenuine()==false) && (usrCheckDtoObject==null))
        {
    	   userCheckDTO.setVoteType("negative");
           result=userCheckService.save(userCheckDTO);
        }
        
       else
        {
   	       usrCheckDtoObject.setVoteType("negative");
           result=userCheckService.save(usrCheckDtoObject);
        }
        
       model.addAttribute("userCheckDTO", result);
       return "home";
        
        
       
        }
	

}
