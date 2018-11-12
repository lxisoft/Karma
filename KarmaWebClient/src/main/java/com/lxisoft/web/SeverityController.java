package com.lxisoft.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.client.karma.api.SeverityResourceApi;
import com.lxisoft.client.karma.model.SeverityDTO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller for managing Severity.
 */
@Controller
public class SeverityController {

    private final Logger log = LoggerFactory.getLogger(SeverityController.class);

    private static final String ENTITY_NAME = "karmaSeverity";

    @Autowired
    SeverityResourceApi severityResourceApi;

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
        
        List<SeverityDTO> severityDto=severityResourceApi.getAllSeveritiesUsingGET(null, null, null, null, null, null, null, null, null, null).getBody();
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
        
        SeverityDTO severityDTO = severityResourceApi.getSeverityUsingGET(id).getBody();
        
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
      
      SeverityDTO severityDTO = severityResourceApi.getSeverityBySeverityLevelUsingGET(severityLevel).getBody();
      
      model.addAttribute("severitylevel", severityDTO);
      return "home";
  }

   
}
