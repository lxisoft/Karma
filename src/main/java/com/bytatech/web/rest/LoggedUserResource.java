package com.bytatech.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bytatech.domain.LoggedUser;

import com.bytatech.repository.LoggedUserRepository;
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
 * REST controller for managing LoggedUser.
 */
@RestController
@RequestMapping("/api")
public class LoggedUserResource {

    private final Logger log = LoggerFactory.getLogger(LoggedUserResource.class);

    private static final String ENTITY_NAME = "loggedUser";

    private final LoggedUserRepository loggedUserRepository;

    public LoggedUserResource(LoggedUserRepository loggedUserRepository) {
        this.loggedUserRepository = loggedUserRepository;
    }

    /**
     * POST  /logged-users : Create a new loggedUser.
     *
     * @param loggedUser the loggedUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loggedUser, or with status 400 (Bad Request) if the loggedUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/logged-users")
    @Timed
    public ResponseEntity<LoggedUser> createLoggedUser(@RequestBody LoggedUser loggedUser) throws URISyntaxException {
        log.debug("REST request to save LoggedUser : {}", loggedUser);
        if (loggedUser.getId() != null) {
            throw new BadRequestAlertException("A new loggedUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoggedUser result = loggedUserRepository.save(loggedUser);
        return ResponseEntity.created(new URI("/api/logged-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /logged-users : Updates an existing loggedUser.
     *
     * @param loggedUser the loggedUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loggedUser,
     * or with status 400 (Bad Request) if the loggedUser is not valid,
     * or with status 500 (Internal Server Error) if the loggedUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/logged-users")
    @Timed
    public ResponseEntity<LoggedUser> updateLoggedUser(@RequestBody LoggedUser loggedUser) throws URISyntaxException {
        log.debug("REST request to update LoggedUser : {}", loggedUser);
        if (loggedUser.getId() == null) {
            return createLoggedUser(loggedUser);
        }
        LoggedUser result = loggedUserRepository.save(loggedUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loggedUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /logged-users : get all the loggedUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of loggedUsers in body
     */
    @GetMapping("/logged-users")
    @Timed
    public List<LoggedUser> getAllLoggedUsers() {
        log.debug("REST request to get all LoggedUsers");
        return loggedUserRepository.findAll();
        }

    /**
     * GET  /logged-users/:id : get the "id" loggedUser.
     *
     * @param id the id of the loggedUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loggedUser, or with status 404 (Not Found)
     */
    @GetMapping("/logged-users/{id}")
    @Timed
    public ResponseEntity<LoggedUser> getLoggedUser(@PathVariable Long id) {
        log.debug("REST request to get LoggedUser : {}", id);
        LoggedUser loggedUser = loggedUserRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(loggedUser));
    }

    /**
     * DELETE  /logged-users/:id : delete the "id" loggedUser.
     *
     * @param id the id of the loggedUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/logged-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoggedUser(@PathVariable Long id) {
        log.debug("REST request to delete LoggedUser : {}", id);
        loggedUserRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
