package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.VerificationTeamService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.VerificationTeamDTO;
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
 * REST controller for managing VerificationTeam.
 */
/*@RestController
@RequestMapping("/api")*/
public class VerificationTeamResource {

    private final Logger log = LoggerFactory.getLogger(VerificationTeamResource.class);

    private static final String ENTITY_NAME = "karmaVerificationTeam";

    private final VerificationTeamService verificationTeamService;

    public VerificationTeamResource(VerificationTeamService verificationTeamService) {
        this.verificationTeamService = verificationTeamService;
    }

    /**
     * POST  /verification-teams : Create a new verificationTeam.
     *
     * @param verificationTeamDTO the verificationTeamDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new verificationTeamDTO, or with status 400 (Bad Request) if the verificationTeam has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/verification-teams")
    @Timed
    public ResponseEntity<VerificationTeamDTO> createVerificationTeam(@RequestBody VerificationTeamDTO verificationTeamDTO) throws URISyntaxException {
        log.debug("REST request to save VerificationTeam : {}", verificationTeamDTO);
        if (verificationTeamDTO.getId() != null) {
            throw new BadRequestAlertException("A new verificationTeam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VerificationTeamDTO result = verificationTeamService.save(verificationTeamDTO);
        return ResponseEntity.created(new URI("/api/verification-teams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /verification-teams : Updates an existing verificationTeam.
     *
     * @param verificationTeamDTO the verificationTeamDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated verificationTeamDTO,
     * or with status 400 (Bad Request) if the verificationTeamDTO is not valid,
     * or with status 500 (Internal Server Error) if the verificationTeamDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/verification-teams")
    @Timed
    public ResponseEntity<VerificationTeamDTO> updateVerificationTeam(@RequestBody VerificationTeamDTO verificationTeamDTO) throws URISyntaxException {
        log.debug("REST request to update VerificationTeam : {}", verificationTeamDTO);
        if (verificationTeamDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VerificationTeamDTO result = verificationTeamService.save(verificationTeamDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, verificationTeamDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /verification-teams : get all the verificationTeams.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of verificationTeams in body
     */
    @GetMapping("/verification-teams")
    @Timed
    public ResponseEntity<List<VerificationTeamDTO>> getAllVerificationTeams(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of VerificationTeams");
        Page<VerificationTeamDTO> page;
        if (eagerload) {
            page = verificationTeamService.findAllWithEagerRelationships(pageable);
        } else {
            page = verificationTeamService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/verification-teams?eagerload=%b", eagerload));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /verification-teams/:id : get the "id" verificationTeam.
     *
     * @param id the id of the verificationTeamDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the verificationTeamDTO, or with status 404 (Not Found)
     */
    @GetMapping("/verification-teams/{id}")
    @Timed
    public ResponseEntity<VerificationTeamDTO> getVerificationTeam(@PathVariable Long id) {
        log.debug("REST request to get VerificationTeam : {}", id);
        Optional<VerificationTeamDTO> verificationTeamDTO = verificationTeamService.findOne(id);
        return ResponseUtil.wrapOrNotFound(verificationTeamDTO);
    }

    /**
     * DELETE  /verification-teams/:id : delete the "id" verificationTeam.
     *
     * @param id the id of the verificationTeamDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/verification-teams/{id}")
    @Timed
    public ResponseEntity<Void> deleteVerificationTeam(@PathVariable Long id) {
        log.debug("REST request to delete VerificationTeam : {}", id);
        verificationTeamService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
