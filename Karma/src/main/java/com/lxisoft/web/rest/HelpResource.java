package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.HelpService;
import com.lxisoft.service.MediaService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.MediaDTO;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Help.
 */
@RestController
@RequestMapping("/api")
public class HelpResource {

    private final Logger log = LoggerFactory.getLogger(HelpResource.class);

    private static final String ENTITY_NAME = "karmaHelp";

    private final HelpService helpService;
    
    @Autowired
	MediaService mediaService;

    @Autowired
   	ApprovalStatusService approvalStatusService;

    public HelpResource(HelpService helpService) {
        this.helpService = helpService;
    }

    /**
     * POST  /helps : Create a new help.
     *
     * @param helpDTO the helpDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new helpDTO, or with status 400 (Bad Request) if the help has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping("/helps")
    @Timed
    public ResponseEntity<HelpDTO> createHelp(@RequestBody HelpDTO helpDTO) throws URISyntaxException, IOException {
        log.debug("REST request to save Help : {}", helpDTO);
        if (helpDTO.getId() != null) {
            throw new BadRequestAlertException("A new help cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        if (helpDTO.getApprovalStatusId() == null) {

			Optional<ApprovalStatusDTO> approvalStatus = approvalStatusService.findByStatus("incompleted");

			Long id = approvalStatus.get().getId();
			log.debug("***************{}" + id);
			helpDTO.setApprovalStatusId(approvalStatus.get().getId());
		}
        
        HelpDTO helpDto = helpService.save(helpDTO);
        
        return ResponseEntity.created(new URI("/api/helps/" + helpDto.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, helpDto.getId().toString()))
            .body(helpDto);
    }

    /**
     * PUT  /helps : Updates an existing help.
     *
     * @param helpDTO the helpDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated helpDTO,
     * or with status 400 (Bad Request) if the helpDTO is not valid,
     * or with status 500 (Internal Server Error) if the helpDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PutMapping("/helps")
    @Timed
    public ResponseEntity<HelpDTO> updateHelp(@RequestBody HelpDTO helpDTO) throws URISyntaxException, IOException {
        log.debug("REST request to update Help : {}", helpDTO);
        if (helpDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HelpDTO result = helpService.save(helpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, helpDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /helps : get all the helps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of helps in body
     */
    @GetMapping("/helps")
    @Timed
    public ResponseEntity<List<HelpDTO>> getAllHelps(Pageable pageable) {
        log.debug("REST request to get a page of Helps");

        Page<HelpDTO> page = helpService.findAllHelps(pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/helps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /helps/:id : get the "id" help.
     *
     * @param id the id of the helpDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the helpDTO, or with status 404 (Not Found)
     */
    @GetMapping("/helps/{id}")
    @Timed
    public ResponseEntity<HelpDTO> getHelp(@PathVariable Long id) {
        log.debug("REST request to get Help : {}", id);
        Optional<HelpDTO> helpDTO = helpService.findOne(id);
        
        return ResponseUtil.wrapOrNotFound(helpDTO);
    }

    /**
     * DELETE  /helps/:id : delete the "id" help.
     *
     * @param id the id of the helpDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/helps/{id}")
    @Timed
    public ResponseEntity<Void> deleteHelp(@PathVariable Long id) {
        log.debug("REST request to delete Help : {}", id);
        helpService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /helps : get all the helps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of helps in body
     */
    @GetMapping("/helps/getAllHelpsByApprovedStatus/{approvalStatus}")
    @Timed
    public ResponseEntity<List<HelpDTO>> getAllHelpsByApprovedStatus(Pageable pageable,@PathVariable String approvalStatus) {
        log.debug("REST request to get a page of Helps");
        Page<HelpDTO> page = helpService.findAllHelpsByApprovedStatus(pageable,approvalStatus);
        
        List<HelpDTO> helps = page.getContent();

		/*for(HelpDTO helpDto:helps){
			
			List<String> fileNameList=new ArrayList();
			
			log.info("*********need");
			
			Page<MediaDTO> mediaList=mediaService.findAllUrlByHelpId(helpDto.getId(), pageable);
			
			List<MediaDTO> mediaDtoList=mediaList.getContent();
			
			for(MediaDTO media:mediaDtoList){
				
				String mediaUrl=media.getUrl();
				fileNameList.add(mediaUrl);
				log.info("*********media url{}",mediaUrl);
			
			}
			helpDto.setFileNameList(fileNameList);
		}*/

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/helps/getAllHelpsByApprovedStatus/");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    

    /**
     * POST  /helps : Create a new help with media.
     *
     * @param helpDTO the helpDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new helpDTO, or with status 400 (Bad Request) if the help has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IOException 
     */
    @PostMapping("/helps/createHelpWithMedia")
    @Timed
    public ResponseEntity<HelpDTO> createHelpWithMedia(@RequestBody HelpDTO helpDTO,@RequestParam MultipartFile[] files) throws URISyntaxException, IOException {
        log.debug("REST request to save Help : {}", helpDTO);
        if (helpDTO.getId() != null) {
            throw new BadRequestAlertException("A new help cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        if (helpDTO.getApprovalStatusId() == null) {

			Optional<ApprovalStatusDTO> approvalStatus = approvalStatusService.findByStatus("incompleted");

			Long id = approvalStatus.get().getId();
			log.debug("***************{}" + id);
			helpDTO.setApprovalStatusId(approvalStatus.get().getId());
		}
        
        HelpDTO helpDto = helpService.save(helpDTO);
        
		for(MultipartFile file:files){
			
			log.info("********helpcontroller",file.getName());
			
			 MediaDTO mediaDTO=new MediaDTO();
	    	 
	    	 mediaDTO.setFile(file);
	    	 
	    	 mediaDTO.setHelpId(helpDto.getId());
	         
	    	 mediaService.save(mediaDTO);
	        
		}
		
        return ResponseEntity.created(new URI("/api/helps/createHelpWithMedia" + helpDto.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, helpDto.getId().toString()))
            .body(helpDto);
    }



}
