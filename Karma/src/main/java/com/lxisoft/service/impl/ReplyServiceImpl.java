package com.lxisoft.service.impl;

import com.lxisoft.service.ReplyService;
import com.lxisoft.domain.Reply;
import com.lxisoft.repository.ReplyRepository;
import com.lxisoft.service.dto.ReplyDTO;
import com.lxisoft.service.mapper.ReplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Reply.
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final Logger log = LoggerFactory.getLogger(ReplyServiceImpl.class);

    private final ReplyRepository replyRepository;

    private final ReplyMapper replyMapper;

    public ReplyServiceImpl(ReplyRepository replyRepository, ReplyMapper replyMapper) {
        this.replyRepository = replyRepository;
        this.replyMapper = replyMapper;
    }

    /**
     * Save a reply.
     *
     * @param replyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReplyDTO save(ReplyDTO replyDTO) {
        log.debug("Request to save Reply : {}", replyDTO);
        Reply reply = replyMapper.toEntity(replyDTO);
        reply = replyRepository.save(reply);
        return replyMapper.toDto(reply);
    }

    /**
     * Get all the replies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReplyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Replies");
        return replyRepository.findAll(pageable)
            .map(replyMapper::toDto);
    }


    /**
     * Get one reply by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReplyDTO> findOne(Long id) {
        log.debug("Request to get Reply : {}", id);
        return replyRepository.findById(id)
            .map(replyMapper::toDto);
    }

    /**
     * Delete the reply by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reply : {}", id);
        replyRepository.deleteById(id);
    }

	@Override
	public Page<ReplyDTO> findAllRepliesByCommentId(Pageable pageable, Long commentId) {
		 log.debug("Request to get all Replies by comment id");
	        return replyRepository.findAllRepliesByCommentId(pageable,commentId)
	            .map(replyMapper::toDto);
	   
	}
}
