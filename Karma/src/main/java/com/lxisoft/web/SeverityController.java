package com.lxisoft.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.SeverityService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.SeverityDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing Severity.
 */
@Controller
public class SeverityController {

    private final Logger log = LoggerFactory.getLogger(SeverityController.class);

    private static final String ENTITY_NAME = "karmaSeverity";

    @Autowired
    private final SeverityService severityService;

    public SeverityController(SeverityService severityService) {
        this.severityService = severityService;
    }

       /**
     * GET  /severities : get all the severities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of severities in body
     */
    @GetMapping("/severities")
    @Timed
    public String getAllSeverities(Pageable pageable,Model model) {
        log.debug("request to get a page of Severities");
        Page<SeverityDTO> page = severityService.findAll(pageable);
       
        List<SeverityDTO> severityDto=page.getContent();
        model.addAttribute("severitylevels",severityDto);
        
        return "pending-requests";
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
        log.debug("request to get Severity : {}", id);
        
        Optional<SeverityDTO> severityDTO = severityService.findOne(id);
        
        model.addAttribute("severity", severityDTO);
		
        return "home";
    }
    
    /**
    *
   * @param severityLevel the id of the severityDTO to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the severityDTO, or with status 404 (Not Found)
   */
  @GetMapping("/getSeverityBySeverityLevel/{severityLevel}")
  @Timed
  public String getSeverityBySeverityLevel(@PathVariable String severityLevel,Model model) {
      log.debug("REST request to get Severity : {}", severityLevel);
      Optional<SeverityDTO> severityDTO = severityService.findBySeverityLevel(severityLevel);
      
      model.addAttribute("severitylevel", severityDTO);
      return "home";
  }

   
}
