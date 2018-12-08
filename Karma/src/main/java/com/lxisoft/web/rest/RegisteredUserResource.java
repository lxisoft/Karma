package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.RegisteredUserService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.RegisteredUserDTO;
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
 * REST controller for managing RegisteredUser.
 */

public class RegisteredUserResource {

    private final Logger log = LoggerFactory.getLogger(RegisteredUserResource.class);

    private static final String ENTITY_NAME = "karmaRegisteredUser";

    private final RegisteredUserService registeredUserService;

    public RegisteredUserResource(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }

    /**
     * POST  /registered-users : Create a new registeredUser.
     *
     * @param registeredUserDTO the registeredUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new registeredUserDTO, or with status 400 (Bad Request) if the registeredUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/registered-users")
    @Timed
    public ResponseEntity<RegisteredUserDTO> createRegisteredUser(@RequestBody RegisteredUserDTO registeredUserDTO) throws URISyntaxException {
        log.debug("REST request to save RegisteredUser : {}", registeredUserDTO);
        if (registeredUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new registeredUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RegisteredUserDTO result = registeredUserService.save(registeredUserDTO);
        return ResponseEntity.created(new URI("/api/registered-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /registered-users : Updates an existing registeredUser.
     *
     * @param registeredUserDTO the registeredUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated registeredUserDTO,
     * or with status 400 (Bad Request) if the registeredUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the registeredUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/registered-users")
    @Timed
    public ResponseEntity<RegisteredUserDTO> updateRegisteredUser(@RequestBody RegisteredUserDTO registeredUserDTO) throws URISyntaxException {
        log.debug("REST request to update RegisteredUser : {}", registeredUserDTO);
        if (registeredUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RegisteredUserDTO result = registeredUserService.save(registeredUserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, registeredUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /registered-users : get all the registeredUsers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of registeredUsers in body
     */
    @GetMapping("/registered-users")
    @Timed
    public ResponseEntity<List<RegisteredUserDTO>> getAllRegisteredUsers(Pageable pageable) {
        log.debug("REST request to get a page of RegisteredUsers");
        Page<RegisteredUserDTO> page = registeredUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/registered-users");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /registered-users/:id : get the "id" registeredUser.
     *
     * @param id the id of the registeredUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the registeredUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/registered-users/{id}")
    @Timed
    public ResponseEntity<RegisteredUserDTO> getRegisteredUser(@PathVariable Long id) {
        log.debug("REST request to get RegisteredUser : {}", id);
        Optional<RegisteredUserDTO> registeredUserDTO = registeredUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(registeredUserDTO);
    }

    /**
     * DELETE  /registered-users/:id : delete the "id" registeredUser.
     *
     * @param id the id of the registeredUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/registered-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteRegisteredUser(@PathVariable Long id) {
        log.debug("REST request to delete RegisteredUser : {}", id);
        registeredUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
