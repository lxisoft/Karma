package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.IdentityProofService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.IdentityProofDTO;
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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing IdentityProof.
 */
@RestController
@RequestMapping("/api")
public class IdentityProofResource {

    private final Logger log = LoggerFactory.getLogger(IdentityProofResource.class);

    private static final String ENTITY_NAME = "karmaIdentityProof";

    private final IdentityProofService identityProofService;

    public IdentityProofResource(IdentityProofService identityProofService) {
        this.identityProofService = identityProofService;
    }

    /**
     * POST  /identity-proofs : Create a new identityProof.
     *
     * @param identityProofDTO the identityProofDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new identityProofDTO, or with status 400 (Bad Request) if the identityProof has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/identity-proofs")
    @Timed
    public ResponseEntity<IdentityProofDTO> createIdentityProof(@RequestBody IdentityProofDTO identityProofDTO) throws URISyntaxException {
        log.debug("REST request to save IdentityProof : {}", identityProofDTO);
        if (identityProofDTO.getId() != null) {
            throw new BadRequestAlertException("A new identityProof cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdentityProofDTO result = identityProofService.save(identityProofDTO);
        return ResponseEntity.created(new URI("/api/identity-proofs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /identity-proofs : Updates an existing identityProof.
     *
     * @param identityProofDTO the identityProofDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated identityProofDTO,
     * or with status 400 (Bad Request) if the identityProofDTO is not valid,
     * or with status 500 (Internal Server Error) if the identityProofDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/identity-proofs")
    @Timed
    public ResponseEntity<IdentityProofDTO> updateIdentityProof(@RequestBody IdentityProofDTO identityProofDTO) throws URISyntaxException {
        log.debug("REST request to update IdentityProof : {}", identityProofDTO);
        if (identityProofDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdentityProofDTO result = identityProofService.save(identityProofDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, identityProofDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /identity-proofs : get all the identityProofs.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of identityProofs in body
     */
    @GetMapping("/identity-proofs")
    @Timed
    public ResponseEntity<List<IdentityProofDTO>> getAllIdentityProofs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("owner-is-null".equals(filter)) {
            log.debug("REST request to get all IdentityProofs where owner is null");
            return new ResponseEntity<>(identityProofService.findAllWhereOwnerIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of IdentityProofs");
        Page<IdentityProofDTO> page = identityProofService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/identity-proofs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /identity-proofs/:id : get the "id" identityProof.
     *
     * @param id the id of the identityProofDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the identityProofDTO, or with status 404 (Not Found)
     */
    @GetMapping("/identity-proofs/{id}")
    @Timed
    public ResponseEntity<IdentityProofDTO> getIdentityProof(@PathVariable Long id) {
        log.debug("REST request to get IdentityProof : {}", id);
        Optional<IdentityProofDTO> identityProofDTO = identityProofService.findOne(id);
        return ResponseUtil.wrapOrNotFound(identityProofDTO);
    }

    /**
     * DELETE  /identity-proofs/:id : delete the "id" identityProof.
     *
     * @param id the id of the identityProofDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/identity-proofs/{id}")
    @Timed
    public ResponseEntity<Void> deleteIdentityProof(@PathVariable Long id) {
        log.debug("REST request to delete IdentityProof : {}", id);
        identityProofService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
