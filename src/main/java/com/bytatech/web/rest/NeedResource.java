package com.bytatech.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bytatech.domain.Need;

import com.bytatech.repository.NeedRepository;
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
 * REST controller for managing Need.
 */
@RestController
@RequestMapping("/api")
public class NeedResource {

    private final Logger log = LoggerFactory.getLogger(NeedResource.class);

    private static final String ENTITY_NAME = "need";

    private final NeedRepository needRepository;

    public NeedResource(NeedRepository needRepository) {
        this.needRepository = needRepository;
    }

    /**
     * POST  /needs : Create a new need.
     *
     * @param need the need to create
     * @return the ResponseEntity with status 201 (Created) and with body the new need, or with status 400 (Bad Request) if the need has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/needs")
    @Timed
    public ResponseEntity<Need> createNeed(@RequestBody Need need) throws URISyntaxException {
        log.debug("REST request to save Need : {}", need);
        if (need.getId() != null) {
            throw new BadRequestAlertException("A new need cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Need result = needRepository.save(need);
        return ResponseEntity.created(new URI("/api/needs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /needs : Updates an existing need.
     *
     * @param need the need to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated need,
     * or with status 400 (Bad Request) if the need is not valid,
     * or with status 500 (Internal Server Error) if the need couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/needs")
    @Timed
    public ResponseEntity<Need> updateNeed(@RequestBody Need need) throws URISyntaxException {
        log.debug("REST request to update Need : {}", need);
        if (need.getId() == null) {
            return createNeed(need);
        }
        Need result = needRepository.save(need);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, need.getId().toString()))
            .body(result);
    }

    /**
     * GET  /needs : get all the needs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of needs in body
     */
    @GetMapping("/needs")
    @Timed
    public List<Need> getAllNeeds() {
        log.debug("REST request to get all Needs");
        return needRepository.findAllWithEagerRelationships();
        }

    /**
     * GET  /needs/:id : get the "id" need.
     *
     * @param id the id of the need to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the need, or with status 404 (Not Found)
     */
    @GetMapping("/needs/{id}")
    @Timed
    public ResponseEntity<Need> getNeed(@PathVariable Long id) {
        log.debug("REST request to get Need : {}", id);
        Need need = needRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(need));
    }

    /**
     * DELETE  /needs/:id : delete the "id" need.
     *
     * @param id the id of the need to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/needs/{id}")
    @Timed
    public ResponseEntity<Void> deleteNeed(@PathVariable Long id) {
        log.debug("REST request to delete Need : {}", id);
        needRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
