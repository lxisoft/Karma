package com.lxisoft.web;
import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.LoggedUserService;
import com.lxisoft.service.MediaService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.LoggedUserDTO;
import com.lxisoft.service.dto.MediaDTO;
import com.lxisoft.service.dto.NeedDTO;

import io.github.jhipster.web.util.ResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.MultipartConfigElement;

/**
 * Controller for managing LoggedUser.
 */
@Controller
public class LoggedUserController {

    private final Logger log = LoggerFactory.getLogger(LoggedUserController.class);

    private static final String ENTITY_NAME = "karmaLoggedUser";

    private final LoggedUserService loggedUserService;
    
    @Value("${upload.path}")
    private String path;
    
   // private final Path pathLocation=Paths.get("uploadedfiles");
    
    @Autowired
    MediaService mediaService;
    
    public LoggedUserController(LoggedUserService loggedUserService) {
        this.loggedUserService = loggedUserService;
    }

    /**
     * POST  /logged-users : Create a new loggedUser.
     *
     * @param loggedUserDTO the loggedUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loggedUserDTO, or with status 400 (Bad Request) if the loggedUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping("/logged-users")
    @Timed
    public String createLoggedUser(@ModelAttribute LoggedUserDTO loggedUserDTO,@RequestParam MultipartFile file,Model model) throws URISyntaxException, IOException {
       
    System.out.println("heloooo");
    	log.debug("request to save LoggedUser : {}", loggedUserDTO);
    	
    	 MediaDTO mediaDTO=new MediaDTO();
    	 
    	 mediaDTO.setFile(file);
         
    	 mediaService.save(mediaDTO);
        
      
        Optional<MediaDTO> mediaDto=mediaService.findByFileName(file.getOriginalFilename());
        
        Long mediaId=mediaDto.get().getId();
        loggedUserDTO.setProfilePicId(mediaId);
        log.info("*************{}",mediaId);
       
        loggedUserService.save(loggedUserDTO);
        
       model.addAttribute("loggedUserDTO",loggedUserDTO);
       model.addAttribute("file",file);
          
       // LoggedUserDTO result = loggedUserService.save(loggedUserDTO);
        return "home";
    }

    /**
     * PUT  /logged-users : Updates an existing loggedUser.
     *
     * @param loggedUserDTO the loggedUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loggedUserDTO,
     * or with status 400 (Bad Request) if the loggedUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the loggedUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logged-users")
    @Timed
    public ResponseEntity<LoggedUserDTO> updateLoggedUser(@RequestBody LoggedUserDTO loggedUserDTO) throws URISyntaxException {
        log.debug("REST request to update LoggedUser : {}", loggedUserDTO);
        if (loggedUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoggedUserDTO result = loggedUserService.save(loggedUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loggedUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logged-users : get all the loggedUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loggedUsers in body
     */
    @GetMapping("/logged-users")
    @Timed
    public ResponseEntity<List<LoggedUserDTO>> getAllLoggedUsers(Pageable pageable) {
        log.debug("REST request to get a page of LoggedUsers");
        Page<LoggedUserDTO> page = loggedUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/logged-users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /logged-users/:id : get the "id" loggedUser.
     *
     * @param id the id of the loggedUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loggedUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/logged-users/{id}")
    @Timed
    public ResponseEntity<LoggedUserDTO> getLoggedUser(@PathVariable Long id) {
        log.debug("REST request to get LoggedUser : {}", id);
        Optional<LoggedUserDTO> loggedUserDTO = loggedUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loggedUserDTO);
    }

    /**
     * DELETE  /logged-users/:id : delete the "id" loggedUser.
     *
     * @param id the id of the loggedUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logged-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoggedUser(@PathVariable Long id) {
        log.debug("REST request to delete LoggedUser : {}", id);
        loggedUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    
    /*
     * test
     */
    @GetMapping("/registration")
	public String showDetails(Model model){
		
		//model.addAttribute("car", new Car());
		return "registration-form";
	}
	
    
    
}
