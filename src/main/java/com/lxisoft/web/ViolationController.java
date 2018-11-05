package com.lxisoft.web;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.MediaService;
import com.lxisoft.service.ViolationService;
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
    	
        if (violationDTO.getId() != null) {
            throw new BadRequestAlertException("A new violation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        for(MultipartFile file:files){
        	
        	MediaDTO mediaDTO=new MediaDTO();
        	
        	mediaDTO.setFile(file);
        	mediaDTO.setViolationId(violationDTO.getId());
        	mediaService.save(mediaDTO);
        	
        	
        //	violationDTO.setFiles(files);
        	
        }
       
        ViolationDTO violationDto = violationService.save(violationDTO);
        
        model.addAttribute("Violation", violationDto);
        
        return "home";
    }
}