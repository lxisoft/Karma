package com.bytatech.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bytatech.domain.VerificationTeam;

import com.bytatech.repository.VerificationTeamRepository;
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
 * REST controller for managing VerificationTeam.
 */
@RestController
@RequestMapping("/api")
public class VerificationTeamResource {

    private final Logger log = LoggerFactory.getLogger(VerificationTeamResource.class);

    private static final String ENTITY_NAME = "verificationTeam";

    private final VerificationTeamRepository verificationTeamRepository;

    public VerificationTeamResource(VerificationTeamRepository verificationTeamRepository) {
        this.verificationTeamRepository = verificationTeamRepository;
    }

    /**
     * POST  /verification-teams : Create a new verificationTeam.
     *
     * @param verificationTeam the verificationTeam to create
     * @return the ResponseEntity with status 201 (Created) and with body the new verificationTeam, or with status 400 (Bad Request) if the verificationTeam has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/verification-teams")
    @Timed
    public ResponseEntity<VerificationTeam> createVerificationTeam(@RequestBody VerificationTeam verificationTeam) throws URISyntaxException {
        log.debug("REST request to save VerificationTeam : {}", verificationTeam);
        if (verificationTeam.getId() != null) {
            throw new BadRequestAlertException("A new verificationTeam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VerificationTeam result = verificationTeamRepository.save(verificationTeam);
        return ResponseEntity.created(new URI("/api/verification-teams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /verification-teams : Updates an existing verificationTeam.
     *
     * @param verificationTeam the verificationTeam to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated verificationTeam,
     * or with status 400 (Bad Request) if the verificationTeam is not valid,
     * or with status 500 (Internal Server Error) if the verificationTeam couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/verification-teams")
    @Timed
    public ResponseEntity<VerificationTeam> updateVerificationTeam(@RequestBody VerificationTeam verificationTeam) throws URISyntaxException {
        log.debug("REST request to update VerificationTeam : {}", verificationTeam);
        if (verificationTeam.getId() == null) {
            return createVerificationTeam(verificationTeam);
        }
        VerificationTeam result = verificationTeamRepository.save(verificationTeam);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, verificationTeam.getId().toString()))
            .body(result);
    }

    /**
     * GET  /verification-teams : get all the verificationTeams.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of verificationTeams in body
     */
    @GetMapping("/verification-teams")
    @Timed
    public List<VerificationTeam> getAllVerificationTeams() {
        log.debug("REST request to get all VerificationTeams");
        return verificationTeamRepository.findAllWithEagerRelationships();
        }

    /**
     * GET  /verification-teams/:id : get the "id" verificationTeam.
     *
     * @param id the id of the verificationTeam to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the verificationTeam, or with status 404 (Not Found)
     */
    @GetMapping("/verification-teams/{id}")
    @Timed
    public ResponseEntity<VerificationTeam> getVerificationTeam(@PathVariable Long id) {
        log.debug("REST request to get VerificationTeam : {}", id);
        VerificationTeam verificationTeam = verificationTeamRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(verificationTeam));
    }

    /**
     * DELETE  /verification-teams/:id : delete the "id" verificationTeam.
     *
     * @param id the id of the verificationTeam to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/verification-teams/{id}")
    @Timed
    public ResponseEntity<Void> deleteVerificationTeam(@PathVariable Long id) {
        log.debug("REST request to delete VerificationTeam : {}", id);
        verificationTeamRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
