package com.lxisoft.web;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.MediaService;
import com.lxisoft.service.ViolationService;
import com.lxisoft.web.rest.ViolationResource;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.dto.ViolationDTO;
import io.github.jhipster.web.util.ResponseUtil;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Violation.
 */
@Controller
public class ViolationController {

    private final Logger log = LoggerFactory.getLogger(ViolationController.class);

    private static final String ENTITY_NAME = "karmaViolation";

    private final ViolationService violationService;
     
    @Autowired
    MediaService mediaService;
    
    @Autowired
    ViolationResource violationResource;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    /**
     * POST  /violations : Create a new violation.
     *
     * @param violationDTO the violationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new violationDTO, or with status 400 (Bad Request) if the violation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping("/violations")
    @Timed
    public String createViolation(@ModelAttribute ViolationDTO violationDTO,@RequestParam MultipartFile[] files,Model model) throws URISyntaxException, IOException {
        
    	log.debug("request to save Violation : {}", violationDTO);
    	
    	ViolationDTO violationDto = violationResource.createViolationWithMedia(violationDTO,files).getBody();
       
        model.addAttribute("violation", violationDto);
        
        return "post-violation-result";
        
    }
    
    /**
     * PUT  /violations : Updates an existing violation.
     *
     * @param violationDTO the violationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated violationDTO,
     * or with status 400 (Bad Request) if the violationDTO is not valid,
     * or with status 500 (Internal Server Error) if the violationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PutMapping("/violations")
    @Timed
    public String updateViolation(@ModelAttribute ViolationDTO violationDTO,Model model) throws URISyntaxException, IOException {
        log.debug("REST request to update Violation : {}", violationDTO);
       
        ViolationDTO violationDto = violationResource.updateViolation(violationDTO).getBody();
      
        model.addAttribute("violation", violationDto);
        
        return "post-violation-result";
               
    }

    /**
     * GET  /violations : get all the violations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of violations in body
     */
    @GetMapping("/violations")
    @Timed
    public String getAllViolations(Pageable pageable,Model model) {
  
        log.debug("request to get a page of Violations");
        
        List<ViolationDTO> violationDto = violationResource.getAllViolations(pageable).getBody();
        
        model.addAttribute("violation", violationDto);
        
        return "post-violation-result";
    
    }
    
    /**
     * GET  /violations/:id : get the "id" violation.
     *
     * @param id the id of the violationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the violationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/violations/{id}")
    @Timed
    public String getViolation(@PathVariable Long id,Model model) {
        log.debug("request to get Violation : {}", id);
        ViolationDTO violationDTO = violationResource.getViolation(id).getBody();
      
        model.addAttribute("violation", violationDTO);
        
        return "post-violation-result";
       }
    
    /**
     * GET  /violations : get all the violations by anonymous user type.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of violations in body
     */
    @GetMapping("/getViolationByIsAnonymous/{isAnonymous}")
    @Timed
    public String getViolationByIsAnonymous(Pageable pageable,@PathVariable Boolean isAnonymous,Model model) {
        log.debug("request to get all the violations by anonymous user type");
        
        List<ViolationDTO> violationDto = violationResource.getViolationByIsAnonymous(pageable,isAnonymous).getBody();
       
        model.addAttribute("violation",violationDto);
		
        return "post-violation-result";
    }
	
    /*
     * test for violation-post
     */
    @GetMapping("/postviolation")
	public String showDetails(Model model){
		
    	ViolationDTO violationDTO=new ViolationDTO();
		model.addAttribute("violation", new ViolationDTO());
		return "post-violation";
	}
    
}