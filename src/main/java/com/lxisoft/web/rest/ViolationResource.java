package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.ViolationService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.ViolationDTO;
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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Violation.
 */
@RestController
@RequestMapping("/api")
public class ViolationResource {

    private final Logger log = LoggerFactory.getLogger(ViolationResource.class);

    private static final String ENTITY_NAME = "karmaViolation";

    private final ViolationService violationService;

    public ViolationResource(ViolationService violationService) {
        this.violationService = violationService;
    }

    /**
     * POST  /violations : Create a new violation.
     *
     * @param violationDTO the violationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new violationDTO, or with status 400 (Bad Request) if the violation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/violations")
    @Timed
    public ResponseEntity<ViolationDTO> createViolation(@RequestBody ViolationDTO violationDTO) throws URISyntaxException {
        log.debug("REST request to save Violation : {}", violationDTO);
        if (violationDTO.getId() != null) {
            throw new BadRequestAlertException("A new violation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViolationDTO result = violationService.save(violationDTO);
        return ResponseEntity.created(new URI("/api/violations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /violations : Updates an existing violation.
     *
     * @param violationDTO the violationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated violationDTO,
     * or with status 400 (Bad Request) if the violationDTO is not valid,
     * or with status 500 (Internal Server Error) if the violationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/violations")
    @Timed
    public ResponseEntity<ViolationDTO> updateViolation(@RequestBody ViolationDTO violationDTO) throws URISyntaxException {
        log.debug("REST request to update Violation : {}", violationDTO);
        if (violationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ViolationDTO result = violationService.save(violationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, violationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /violations : get all the violations.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of violations in body
     */
    @GetMapping("/violations")
    @Timed
    public ResponseEntity<List<ViolationDTO>> getAllViolations(Pageable pageable) {
        log.debug("REST request to get a page of Violations");
        Page<ViolationDTO> page = violationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/violations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /violations/:id : get the "id" violation.
     *
     * @param id the id of the violationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the violationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/violations/{id}")
    @Timed
    public ResponseEntity<ViolationDTO> getViolation(@PathVariable Long id) {
        log.debug("REST request to get Violation : {}", id);
        Optional<ViolationDTO> violationDTO = violationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(violationDTO);
    }

    /**
     * DELETE  /violations/:id : delete the "id" violation.
     *
     * @param id the id of the violationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/violations/{id}")
    @Timed
    public ResponseEntity<Void> deleteViolation(@PathVariable Long id) {
        log.debug("REST request to delete Violation : {}", id);
        violationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /violations : get all the violations by anonymous user type.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of violations in body
     */
    @GetMapping("/getViolationByIsAnonymous/{isAnonymous}")
    @Timed
    public ResponseEntity<List<ViolationDTO>> getViolationByIsAnonymous(Pageable pageable,@PathVariable Boolean isAnonymous) {
        log.debug("REST request to get all the violations by anonymous user type");
        log.info("**********{}",isAnonymous);
        Page<ViolationDTO> page = violationService.findViolationByIsAnonymous(pageable,isAnonymous);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getViolationByIsAnonymous/");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /violations : get all the violations by after date.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of violations in body
     */
    @GetMapping("/getViolationByDateAfter/{date}")
    @Timed
    public ResponseEntity<List<ViolationDTO>> getViolationByDateAfter(Pageable pageable,@PathVariable Instant date) {
        log.debug("REST request to get all the violations by after date");
        Page<ViolationDTO> page = violationService.findViolationByDateAfter(pageable,date);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/getViolationByDateAfter/");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
