package com.lxisoft.web;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.SeverityService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.SeverityDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Severity.
 */
@Controller

public class SeverityController {

    private final Logger log = LoggerFactory.getLogger(SeverityController.class);

    private static final String ENTITY_NAME = "karmaSeverity";

    private final SeverityService severityService;

    public SeverityController(SeverityService severityService) {
        this.severityService = severityService;
    }

    /**
     * POST  /severities : Create a new severity.
     *
     * @param severityDTO the severityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new severityDTO, or with status 400 (Bad Request) if the severity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/severities")
    @Timed
    public String createSeverity(@RequestBody SeverityDTO severityDTO) throws URISyntaxException {
        log.debug("REST request to save Severity : {}", severityDTO);
        if (severityDTO.getId() != null) {
            throw new BadRequestAlertException("A new severity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        if(severityDTO.getSeverityLevel() == null){
        	throw new BadRequestAlertException("A new severity's severityLevel cannot be null", ENTITY_NAME, "severityLevelnull");	
        	
        }
        
        SeverityDTO result = severityService.save(severityDTO);
        return "severity";
    }

    /**
     * PUT  /severities : Updates an existing severity.
     *
     * @param severityDTO the severityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated severityDTO,
     * or with status 400 (Bad Request) if the severityDTO is not valid,
     * or with status 500 (Internal Server Error) if the severityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/severities")
    @Timed
    public String updateSeverity(@RequestBody SeverityDTO severityDTO,Model model) throws URISyntaxException {
        log.debug("REST request to update Severity : {}", severityDTO);
        if (severityDTO.getId() == null) {
        	
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
            
        }
        SeverityDTO result = severityService.save(severityDTO);
        
        model.addAttribute("severity",result);
        
        return "severity";
    }
 
    /**
     * GET  /severities : get all the severities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of severities in body
     */
    @GetMapping("/severities")
    @Timed
    public String getAllSeverities(Pageable pageable,Model model){
    	
        log.debug("REST request to get a page of Severities");
        Page<SeverityDTO> page = severityService.findAll(pageable);
        List<SeverityDTO>severities=page.getContent();
       model.addAttribute("severities",severities);
        return "severities";
    } 

    /**
     * GET  /severities/:id : get the "id" severity.
     *
     * @param id the id of the severityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the severityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/severities/{id}")
    @Timed
    public String getSeverity(@PathVariable Long id,Model model) {
        log.debug("REST request to get Severity : {}", id);
        Optional<SeverityDTO> severityDTO = severityService.findOne(id);
        SeverityDTO severity=severityDTO.get();
        
        model.addAttribute("severity",severity);
        
        
     
        return "severity";
    }

    /**
     * DELETE  /severities/:id : delete the "id" severity.
     *
     * @param id the id of the severityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/severities/{id}")
    @Timed
    public String deleteSeverity(@PathVariable Long id) {
        log.debug("REST request to delete Severity : {}", id);
        severityService.delete(id);
        return "done";
    }
    
}