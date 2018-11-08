package com.lxisoft.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.lxisoft.service.ApprovalStatusService;
import com.lxisoft.web.rest.errors.BadRequestAlertException;
import com.lxisoft.web.rest.util.HeaderUtil;
import com.lxisoft.web.rest.util.PaginationUtil;
import com.lxisoft.service.dto.ApprovalStatusDTO;
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
 * REST controller for managing ApprovalStatus.
 */
@RestController
@RequestMapping("/api")
public class ApprovalStatusResource {

    private final Logger log = LoggerFactory.getLogger(ApprovalStatusResource.class);

    private static final String ENTITY_NAME = "karmaApprovalStatus";

    private final ApprovalStatusService approvalStatusService;

    public ApprovalStatusResource(ApprovalStatusService approvalStatusService) {
        this.approvalStatusService = approvalStatusService;
    }

    /**
     * POST  /approval-statuses : Create a new approvalStatus.
     *
     * @param approvalStatusDTO the approvalStatusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new approvalStatusDTO, or with status 400 (Bad Request) if the approvalStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/approval-statuses")
    @Timed
    public ResponseEntity<ApprovalStatusDTO> createApprovalStatus(@RequestBody ApprovalStatusDTO approvalStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ApprovalStatus : {}", approvalStatusDTO);
        if (approvalStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new approvalStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApprovalStatusDTO result = approvalStatusService.save(approvalStatusDTO);
        return ResponseEntity.created(new URI("/api/approval-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /approval-statuses : Updates an existing approvalStatus.
     *
     * @param approvalStatusDTO the approvalStatusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated approvalStatusDTO,
     * or with status 400 (Bad Request) if the approvalStatusDTO is not valid,
     * or with status 500 (Internal Server Error) if the approvalStatusDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/approval-statuses")
    @Timed
    public ResponseEntity<ApprovalStatusDTO> updateApprovalStatus(@RequestBody ApprovalStatusDTO approvalStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ApprovalStatus : {}", approvalStatusDTO);
        if (approvalStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApprovalStatusDTO result = approvalStatusService.save(approvalStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, approvalStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /approval-statuses : get all the approvalStatuses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of approvalStatuses in body
     */
    @GetMapping("/approval-statuses")
    @Timed
    public ResponseEntity<List<ApprovalStatusDTO>> getAllApprovalStatuses(Pageable pageable) {
        log.debug("REST request to get a page of ApprovalStatuses");
        Page<ApprovalStatusDTO> page = approvalStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/approval-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /approval-statuses/:id : get the "id" approvalStatus.
     *
     * @param id the id of the approvalStatusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the approvalStatusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/approval-statuses/{id}")
    @Timed
    public ResponseEntity<ApprovalStatusDTO> getApprovalStatus(@PathVariable Long id) {
        log.debug("REST request to get ApprovalStatus : {}", id);
        Optional<ApprovalStatusDTO> approvalStatusDTO = approvalStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(approvalStatusDTO);
    }

    /**
     * DELETE  /approval-statuses/:id : delete the "id" approvalStatus.
     *
     * @param id the id of the approvalStatusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/approval-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteApprovalStatus(@PathVariable Long id) {
        log.debug("REST request to delete ApprovalStatus : {}", id);
        approvalStatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * GET  /approval-statuses/:status : get the approvalStatus by status.
     *
     * @param id the id of the approvalStatusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the approvalStatusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/approval-statuses/getApprovalStatus/{status}")
    @Timed
    public ResponseEntity<ApprovalStatusDTO> getApprovalStatus(@PathVariable String status) {
        log.debug("REST request to get ApprovalStatus : {}", status);
        Optional<ApprovalStatusDTO> approvalStatusDTO = approvalStatusService.findByStatus(status);
        return ResponseUtil.wrapOrNotFound(approvalStatusDTO);
    }

}
