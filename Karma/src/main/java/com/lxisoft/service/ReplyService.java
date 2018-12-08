package com.lxisoft.service;

import com.lxisoft.service.dto.ReplyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Reply.
 */
public interface ReplyService {

    /**
     * Save a reply.
     *
     * @param replyDTO the entity to save
     * @return the persisted entity
     */
    ReplyDTO save(ReplyDTO replyDTO);

    /**
     * Get all the replies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReplyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reply.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReplyDTO> findOne(Long id);

    /**
     * Delete the "id" reply.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
