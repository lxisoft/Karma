package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.UserCheckService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.UserCheckDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
 * REST controller for managing UserCheck.
 */
@RestController
@RequestMapping("/api")
public class UserCheckResource {

    private final Logger log = LoggerFactory.getLogger(UserCheckResource.class);

    private static final String ENTITY_NAME = "karmaUserCheck";

    private final UserCheckService userCheckService;

    public UserCheckResource(UserCheckService userCheckService) {
        this.userCheckService = userCheckService;
    }

    /**
     * POST  /user-checks : Create a new userCheck.
     *
     * @param userCheckDTO the userCheckDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCheckDTO, or with status 400 (Bad Request) if the userCheck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks")
    @Timed
    public ResponseEntity<UserCheckDTO> createUserCheck(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException {
        log.debug("REST request to save UserCheck : {}", userCheckDTO);
        if (userCheckDTO.getId() != null) {
            throw new BadRequestAlertException("A new userCheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCheckDTO result = userCheckService.save(userCheckDTO);
        return ResponseEntity.created(new URI("/api/user-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
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
    public ResponseEntity<UserCheckDTO> updateUserCheck(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException {
        log.debug("REST request to update UserCheck : {}", userCheckDTO);
        if (userCheckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserCheckDTO result = userCheckService.save(userCheckDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userCheckDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-checks : get all the userChecks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of userChecks in body
     */
    @GetMapping("/user-checks")
    @Timed
    public ResponseEntity<List<UserCheckDTO>> getAllUserChecks(Pageable pageable) {
        log.debug("REST request to get a page of UserChecks");
        Page<UserCheckDTO> page = userCheckService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/user-checks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /user-checks/:id : get the "id" userCheck.
     *
     * @param id the id of the userCheckDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userCheckDTO, or with status 404 (Not Found)
     */
    @GetMapping("/user-checks/{id}")
    @Timed
    public ResponseEntity<UserCheckDTO> getUserCheck(@PathVariable Long id) {
        log.debug("REST request to get UserCheck : {}", id);
        Optional<UserCheckDTO> userCheckDTO = userCheckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userCheckDTO);
    }

    /**
     * DELETE  /user-checks/:id : delete the "id" userCheck.
     *
     * @param id the id of the userCheckDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-checks/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserCheck(@PathVariable Long id) {
        log.debug("REST request to delete UserCheck : {}", id);
        userCheckService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * POST  /user-checks : checking the genuineness.
     *
     * @param userCheckDTO the userCheckDTO to create
     * 
     * @param voteType the voteType of the userCheckDto
     * 
     * @return the ResponseEntity with status 201 (Created) and with body the new userCheckDTO, or with status 400 (Bad Request) if the userCheck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks/markingGenuinenes")
    @Timed
    public ResponseEntity<UserCheckDTO> markingGenuinenes(@RequestBody UserCheckDTO userCheckDTO) throws URISyntaxException {
        log.debug("REST request to save UserCheck : {}", userCheckDTO);
        
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
        
                    
        return ResponseEntity.created(new URI("/api/user-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
        }
}
