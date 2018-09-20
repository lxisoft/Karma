package com.bytatech.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bytatech.domain.Severity;

import com.bytatech.repository.SeverityRepository;
import com.bytatech.web.rest.errors.BadRequestAlertException;
import com.bytatech.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final String ENTITY_NAME = "severity";

    private final SeverityRepository severityRepository;

    public SeverityResource(SeverityRepository severityRepository) {
        this.severityRepository = severityRepository;
    }

    /**
     * POST  /severities : Create a new severity.
     *
     * @param severity the severity to create
     * @return the ResponseEntity with status 201 (Created) and with body the new severity, or with status 400 (Bad Request) if the severity has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/severities")
    @Timed
    public ResponseEntity<Severity> createSeverity(@RequestBody Severity severity) throws URISyntaxException {
        log.debug("REST request to save Severity : {}", severity);
        if (severity.getId() != null) {
            throw new BadRequestAlertException("A new severity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Severity result = severityRepository.save(severity);
        return ResponseEntity.created(new URI("/api/severities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /severities : Updates an existing severity.
     *
     * @param severity the severity to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated severity,
     * or with status 400 (Bad Request) if the severity is not valid,
     * or with status 500 (Internal Server Error) if the severity couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/severities")
    @Timed
    public ResponseEntity<Severity> updateSeverity(@RequestBody Severity severity) throws URISyntaxException {
        log.debug("REST request to update Severity : {}", severity);
        if (severity.getId() == null) {
            return createSeverity(severity);
        }
        Severity result = severityRepository.save(severity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, severity.getId().toString()))
            .body(result);
    }

    /**
     * GET  /severities : get all the severities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of severities in body
     */
    @GetMapping("/severities")
    @Timed
    public List<Severity> getAllSeverities() {
        log.debug("REST request to get all Severities");
        return severityRepository.findAll();
        }

    /**
     * GET  /severities/:id : get the "id" severity.
     *
     * @param id the id of the severity to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the severity, or with status 404 (Not Found)
     */
    @GetMapping("/severities/{id}")
    @Timed
    public ResponseEntity<Severity> getSeverity(@PathVariable Long id) {
        log.debug("REST request to get Severity : {}", id);
        Severity severity = severityRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(severity));
    }

    /**
     * DELETE  /severities/:id : delete the "id" severity.
     *
     * @param id the id of the severity to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/severities/{id}")
    @Timed
    public ResponseEntity<Void> deleteSeverity(@PathVariable Long id) {
        log.debug("REST request to delete Severity : {}", id);
        severityRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
