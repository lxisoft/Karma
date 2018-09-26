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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.NeedService;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.web.rest.NeedResource;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */
@Controller
public class NeedController {
	
	private final Logger log = LoggerFactory.getLogger(NeedResource.class);

    private static final String ENTITY_NAME = "karmaNeed";

    private NeedService needService;

    public NeedController(NeedService needService) {
        this.needService = needService;
    }

    /**
     * POST  /needs : Create a new need.
     *
     * @param needDTO the needDTO to create
     * @return the string value
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/needs")
    @Timed
    public String createNeed(@ModelAttribute NeedDTO needDTO,Model model) throws URISyntaxException {
        log.debug(" request to save Need : {}", needDTO);
        if (needDTO.getId() != null) {
            throw new BadRequestAlertException("A new need cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NeedDTO need = needService.save(needDTO);
        model.addAttribute("need", need);
    	return "need";
        
    }
     
    /**
     * GET  /needs : get all the needs.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the string value
     */
    @GetMapping("/needs")
    @Timed
    public String getAllNeeds(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload,Model model) {
        log.debug("request to get a page of Needs");
        Page<NeedDTO> page;
        if (eagerload) {
            page = needService.findAllWithEagerRelationships(pageable);
        } else {
            page = needService.findAll(pageable);
        }
        List<NeedDTO> needs = page.getContent();
        model.addAttribute("needs", needs);
        return "needs";
        
    }
    
    /**
     * GET  /needs : get all the needs by approvalStatus.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the string value
     */
    
    @GetMapping("/needs/{approvalStatus}")
    @Timed
    public String getAllNeedsByApprovedStatus(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload,@PathVariable(value="approvalStatus") String approvalStatus,Model model) {
        log.debug("request to get a page of Needs");
        Page<NeedDTO> page;
        if (eagerload) {
            page = needService.findAllWithEagerRelationships(pageable);
        } else {
            page = needService.findAllNeedsByApprovedStatus(pageable,approvalStatus);
        }
        List<NeedDTO> needs = page.getContent();
        model.addAttribute("needs", needs);
        return "needs";
    }

    /**
     * GET  /needs/:id : get the "id" need.
     *
     * @param id the id of the needDTO to retrieve
     * @return the string value
     */
    @GetMapping("/needs/{id}")
    @Timed
    public String getNeed(@PathVariable(value="id") Long id,Model model) {
        log.debug("request to get Need : {}", id);
        Optional<NeedDTO> needDTO = needService.findOne(id);
        model.addAttribute("needById", needDTO);
        return "needById";
    }

    

}
