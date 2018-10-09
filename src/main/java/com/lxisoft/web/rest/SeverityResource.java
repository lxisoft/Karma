package com.lxisoft.web.rest;

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
 * REST controller for managing Severity.
 */
@RestController
@RequestMapping("/api")
public class SeverityResource {

    private final Logger log = LoggerFactory.getLogger(SeverityResource.class);

    private static final String ENTITY_NAME = "karmaSeverity";

    @Autowired
    private final SeverityService severityService;

    public SeverityResource(SeverityService severityService) {
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
    public ResponseEntity<SeverityDTO> createSeverity(@RequestBody SeverityDTO severityDTO) throws URISyntaxException {
        log.debug("REST request to save Severity : {}", severityDTO);
        if (severityDTO.getId() != null) {
            throw new BadRequestAlertException("A new severity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SeverityDTO result = severityService.save(severityDTO);
        return ResponseEntity.created(new URI("/api/severities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
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
    public ResponseEntity<SeverityDTO> updateSeverity(@RequestBody SeverityDTO severityDTO) throws URISyntaxException {
        log.debug("REST request to update Severity : {}", severityDTO);
        if (severityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SeverityDTO result = severityService.save(severityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, severityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /severities : get all the severities.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of severities in body
     */
    @GetMapping("/severities")
    @Timed
    public ResponseEntity<List<SeverityDTO>> getAllSeverities(Pageable pageable) {
        log.debug("REST request to get a page of Severities");
        Page<SeverityDTO> page = severityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/severities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /severities/:id : get the "id" severity.
     *
     * @param id the id of the severityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the severityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/severities/{id}")
    @Timed
    public ResponseEntity<SeverityDTO> getSeverity(@PathVariable Long id) {
        log.debug("REST request to get Severity : {}", id);
        Optional<SeverityDTO> severityDTO = severityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(severityDTO);
    }

    /**
     * DELETE  /severities/:id : delete the "id" severity.
     *
     * @param id the id of the severityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/severities/{id}")
    @Timed
    public ResponseEntity<Void> deleteSeverity(@PathVariable Long id) {
        log.debug("REST request to delete Severity : {}", id);
        severityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
      *
     * @param severity the id of the severityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the severityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/getSeverityBySeverityLevel/{severityLevel}")
    @Timed
    public ResponseEntity<SeverityDTO> getSeverityBySeverityLevel(@PathVariable String severityLevel) {
        log.debug("REST request to get Severity : {}", severityLevel);
        Optional<SeverityDTO> severityDTO = severityService.findBySeverityLevel(severityLevel);
        return ResponseUtil.wrapOrNotFound(severityDTO);
    }

}
