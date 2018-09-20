package com.bytatech.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bytatech.domain.ApprovalStatus;

import com.bytatech.repository.ApprovalStatusRepository;
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
 * REST controller for managing ApprovalStatus.
 */
@RestController
@RequestMapping("/api")
public class ApprovalStatusResource {

    private final Logger log = LoggerFactory.getLogger(ApprovalStatusResource.class);

    private static final String ENTITY_NAME = "approvalStatus";

    private final ApprovalStatusRepository approvalStatusRepository;

    public ApprovalStatusResource(ApprovalStatusRepository approvalStatusRepository) {
        this.approvalStatusRepository = approvalStatusRepository;
    }

    /**
     * POST  /approval-statuses : Create a new approvalStatus.
     *
     * @param approvalStatus the approvalStatus to create
     * @return the ResponseEntity with status 201 (Created) and with body the new approvalStatus, or with status 400 (Bad Request) if the approvalStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/approval-statuses")
    @Timed
    public ResponseEntity<ApprovalStatus> createApprovalStatus(@RequestBody ApprovalStatus approvalStatus) throws URISyntaxException {
        log.debug("REST request to save ApprovalStatus : {}", approvalStatus);
        if (approvalStatus.getId() != null) {
            throw new BadRequestAlertException("A new approvalStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApprovalStatus result = approvalStatusRepository.save(approvalStatus);
        return ResponseEntity.created(new URI("/api/approval-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /approval-statuses : Updates an existing approvalStatus.
     *
     * @param approvalStatus the approvalStatus to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated approvalStatus,
     * or with status 400 (Bad Request) if the approvalStatus is not valid,
     * or with status 500 (Internal Server Error) if the approvalStatus couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/approval-statuses")
    @Timed
    public ResponseEntity<ApprovalStatus> updateApprovalStatus(@RequestBody ApprovalStatus approvalStatus) throws URISyntaxException {
        log.debug("REST request to update ApprovalStatus : {}", approvalStatus);
        if (approvalStatus.getId() == null) {
            return createApprovalStatus(approvalStatus);
        }
        ApprovalStatus result = approvalStatusRepository.save(approvalStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, approvalStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /approval-statuses : get all the approvalStatuses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of approvalStatuses in body
     */
    @GetMapping("/approval-statuses")
    @Timed
    public List<ApprovalStatus> getAllApprovalStatuses() {
        log.debug("REST request to get all ApprovalStatuses");
        return approvalStatusRepository.findAll();
        }

    /**
     * GET  /approval-statuses/:id : get the "id" approvalStatus.
     *
     * @param id the id of the approvalStatus to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the approvalStatus, or with status 404 (Not Found)
     */
    @GetMapping("/approval-statuses/{id}")
    @Timed
    public ResponseEntity<ApprovalStatus> getApprovalStatus(@PathVariable Long id) {
        log.debug("REST request to get ApprovalStatus : {}", id);
        ApprovalStatus approvalStatus = approvalStatusRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(approvalStatus));
    }

    /**
     * DELETE  /approval-statuses/:id : delete the "id" approvalStatus.
     *
     * @param id the id of the approvalStatus to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/approval-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteApprovalStatus(@PathVariable Long id) {
        log.debug("REST request to delete ApprovalStatus : {}", id);
        approvalStatusRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
