package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.IdentityProofTypeService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.IdentityProofTypeDTO;
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
 * REST controller for managing IdentityProofType.
 */
@RestController
@RequestMapping("/api")
public class IdentityProofTypeResource {

    private final Logger log = LoggerFactory.getLogger(IdentityProofTypeResource.class);

    private static final String ENTITY_NAME = "karmaIdentityProofType";

    private final IdentityProofTypeService identityProofTypeService;

    public IdentityProofTypeResource(IdentityProofTypeService identityProofTypeService) {
        this.identityProofTypeService = identityProofTypeService;
    }

    /**
     * POST  /identity-proof-types : Create a new identityProofType.
     *
     * @param identityProofTypeDTO the identityProofTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new identityProofTypeDTO, or with status 400 (Bad Request) if the identityProofType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/identity-proof-types")
    @Timed
    public ResponseEntity<IdentityProofTypeDTO> createIdentityProofType(@RequestBody IdentityProofTypeDTO identityProofTypeDTO) throws URISyntaxException {
        log.debug("REST request to save IdentityProofType : {}", identityProofTypeDTO);
        if (identityProofTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new identityProofType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IdentityProofTypeDTO result = identityProofTypeService.save(identityProofTypeDTO);
        return ResponseEntity.created(new URI("/api/identity-proof-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /identity-proof-types : Updates an existing identityProofType.
     *
     * @param identityProofTypeDTO the identityProofTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated identityProofTypeDTO,
     * or with status 400 (Bad Request) if the identityProofTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the identityProofTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/identity-proof-types")
    @Timed
    public ResponseEntity<IdentityProofTypeDTO> updateIdentityProofType(@RequestBody IdentityProofTypeDTO identityProofTypeDTO) throws URISyntaxException {
        log.debug("REST request to update IdentityProofType : {}", identityProofTypeDTO);
        if (identityProofTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IdentityProofTypeDTO result = identityProofTypeService.save(identityProofTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, identityProofTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /identity-proof-types : get all the identityProofTypes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of identityProofTypes in body
     */
    @GetMapping("/identity-proof-types")
    @Timed
    public ResponseEntity<List<IdentityProofTypeDTO>> getAllIdentityProofTypes(Pageable pageable) {
        log.debug("REST request to get a page of IdentityProofTypes");
        Page<IdentityProofTypeDTO> page = identityProofTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/identity-proof-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /identity-proof-types/:id : get the "id" identityProofType.
     *
     * @param id the id of the identityProofTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the identityProofTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/identity-proof-types/{id}")
    @Timed
    public ResponseEntity<IdentityProofTypeDTO> getIdentityProofType(@PathVariable Long id) {
        log.debug("REST request to get IdentityProofType : {}", id);
        Optional<IdentityProofTypeDTO> identityProofTypeDTO = identityProofTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(identityProofTypeDTO);
    }

    /**
     * DELETE  /identity-proof-types/:id : delete the "id" identityProofType.
     *
     * @param id the id of the identityProofTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/identity-proof-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteIdentityProofType(@PathVariable Long id) {
        log.debug("REST request to delete IdentityProofType : {}", id);
        identityProofTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
