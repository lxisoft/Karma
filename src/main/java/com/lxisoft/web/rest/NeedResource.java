package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.service.NeedService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.NeedDTO;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Need.
 */
@RestController
@RequestMapping("/api")
public class NeedResource {

    private final Logger log = LoggerFactory.getLogger(NeedResource.class);

    private static final String ENTITY_NAME = "karmaNeed";

    private final NeedService needService;
    
    @Autowired
    ApprovalStatusService approvalStatusService;

    public NeedResource(NeedService needService) {
        this.needService = needService;
    }

    /**
     * POST  /needs : Create a new need.
     *
     * @param needDTO the needDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new needDTO, or with status 400 (Bad Request) if the need has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/needs")
    @Timed
    public ResponseEntity<NeedDTO> createNeed(@RequestBody NeedDTO needDTO) throws URISyntaxException {
        log.debug("REST request to save Need : {}", needDTO);
        if (needDTO.getId() != null) {
            throw new BadRequestAlertException("A new need cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        if(needDTO.getApprovalStatusId()==null){
        	
        	Optional<ApprovalStatusDTO> approvalStatus=approvalStatusService.findByStatus("pending");
        	
        	long id=approvalStatus.get().getId();
        	log.debug("***************{}"+id);
        	needDTO.setApprovalStatusId(approvalStatus.get().getId());
        }
        
        NeedDTO result = needService.save(needDTO);
        return ResponseEntity.created(new URI("/api/needs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
        
    }

    /**
     * PUT  /needs : Updates an existing need.
     *
     * @param needDTO the needDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated needDTO,
     * or with status 400 (Bad Request) if the needDTO is not valid,
     * or with status 500 (Internal Server Error) if the needDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/needs")
    @Timed
    public ResponseEntity<NeedDTO> updateNeed(@RequestBody NeedDTO needDTO) throws URISyntaxException {
        log.debug("REST request to update Need : {}", needDTO);
        if (needDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NeedDTO result = needService.save(needDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, needDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /needs : get all the needs.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of needs in body
     */
    @GetMapping("/needs")
    @Timed
    public ResponseEntity<List<NeedDTO>> getAllNeeds(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Needs");
        Page<NeedDTO> page;
        if (eagerload) {
            page = needService.findAllWithEagerRelationships(pageable);
        } else {
            page = needService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/needs?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /needs/:id : get the "id" need.
     *
     * @param id the id of the needDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the needDTO, or with status 404 (Not Found)
     */
    @GetMapping("/needs/{id}")
    @Timed
    public ResponseEntity<NeedDTO> getNeed(@PathVariable Long id) {
        log.debug("REST request to get Need : {}", id);
        Optional<NeedDTO> needDTO = needService.findOne(id);
        return ResponseUtil.wrapOrNotFound(needDTO);
    }

    /**
     * DELETE  /needs/:id : delete the "id" need.
     *
     * @param id the id of the needDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/needs/{id}")
    @Timed
    public ResponseEntity<Void> deleteNeed(@PathVariable Long id) {
        log.debug("REST request to delete Need : {}", id);
        needService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    

    /*@GetMapping("/needs/{approvalStatusId}")
    @Timed
    public ResponseEntity<List<NeedDTO>> getAllNeedsByApprovedStatusId(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload,long approvalStatusId) {
        log.debug("REST request to get a page of Needs");
        Page<NeedDTO> page;
        if (eagerload) {
            page = needService.findAllWithEagerRelationships(pageable);
        } else {
            page = needService.findAllNeedsByApprovedStatusId(pageable,approvalStatusId);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/approvedneeds?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }*/
    
    @GetMapping("/needs/{approvalStatus}")
    @Timed
    public ResponseEntity<List<NeedDTO>> getAllNeedsByApprovedStatus(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload,String approvalStatus) {
        log.debug("REST request to get a page of Needs");
        Page<NeedDTO> page;
        if (eagerload) {
            page = needService.findAllWithEagerRelationships(pageable);
        } else {
            page = needService.findAllNeedsByApprovedStatus(pageable,approvalStatus);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/approvedneeds?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

     
}
