package com.bytatech.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bytatech.domain.UserCheck;

import com.bytatech.repository.UserCheckRepository;
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
 * REST controller for managing UserCheck.
 */
@RestController
@RequestMapping("/api")
public class UserCheckResource {

    private final Logger log = LoggerFactory.getLogger(UserCheckResource.class);

    private static final String ENTITY_NAME = "userCheck";

    private final UserCheckRepository userCheckRepository;

    public UserCheckResource(UserCheckRepository userCheckRepository) {
        this.userCheckRepository = userCheckRepository;
    }

    /**
     * POST  /user-checks : Create a new userCheck.
     *
     * @param userCheck the userCheck to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userCheck, or with status 400 (Bad Request) if the userCheck has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-checks")
    @Timed
    public ResponseEntity<UserCheck> createUserCheck(@RequestBody UserCheck userCheck) throws URISyntaxException {
        log.debug("REST request to save UserCheck : {}", userCheck);
        if (userCheck.getId() != null) {
            throw new BadRequestAlertException("A new userCheck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCheck result = userCheckRepository.save(userCheck);
        return ResponseEntity.created(new URI("/api/user-checks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-checks : Updates an existing userCheck.
     *
     * @param userCheck the userCheck to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userCheck,
     * or with status 400 (Bad Request) if the userCheck is not valid,
     * or with status 500 (Internal Server Error) if the userCheck couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-checks")
    @Timed
    public ResponseEntity<UserCheck> updateUserCheck(@RequestBody UserCheck userCheck) throws URISyntaxException {
        log.debug("REST request to update UserCheck : {}", userCheck);
        if (userCheck.getId() == null) {
            return createUserCheck(userCheck);
        }
        UserCheck result = userCheckRepository.save(userCheck);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userCheck.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-checks : get all the userChecks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userChecks in body
     */
    @GetMapping("/user-checks")
    @Timed
    public List<UserCheck> getAllUserChecks() {
        log.debug("REST request to get all UserChecks");
        return userCheckRepository.findAll();
        }

    /**
     * GET  /user-checks/:id : get the "id" userCheck.
     *
     * @param id the id of the userCheck to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userCheck, or with status 404 (Not Found)
     */
    @GetMapping("/user-checks/{id}")
    @Timed
    public ResponseEntity<UserCheck> getUserCheck(@PathVariable Long id) {
        log.debug("REST request to get UserCheck : {}", id);
        UserCheck userCheck = userCheckRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userCheck));
    }

    /**
     * DELETE  /user-checks/:id : delete the "id" userCheck.
     *
     * @param id the id of the userCheck to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-checks/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserCheck(@PathVariable Long id) {
        log.debug("REST request to delete UserCheck : {}", id);
        userCheckRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
