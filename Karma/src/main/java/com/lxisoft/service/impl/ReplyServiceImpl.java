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
        
       
       // optional.get().setElapsedTime()
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
	public String timeAgo(long time) {
		long currentTime = (System.currentTimeMillis()) / 1000;
        long timeElapsed =currentTime- time;
        long seconds = timeElapsed;
        int minutes = Math.round(timeElapsed / 60);
        int hours = Math.round(timeElapsed / 3600);
        int days = Math.round(timeElapsed / 86400);
        int weeks = Math.round(timeElapsed / 604800);
        int months = Math.round(timeElapsed / 2600640);
        int years = Math.round(timeElapsed / 31207680);
		
		
        // Seconds
        if (seconds <= 60) {
            return "just now";
        }
        //Minutes
        else if (minutes <= 60) {
            if (minutes == 1) {
                return "one minute ago";
            } else {
                return minutes + " minutes ago";
            }
        }
        //Hours
        else if (hours <= 24) {
            if (hours == 1) {
                return "an hour ago";
            } else {
                return hours + " hrs ago";
            }
        }
        //Days
        else if (days <= 7) {
            if (days == 1) {
                return "yesterday";
            } else {
                return days + " days ago";
            }
        }
        //Weeks
        else if (weeks <= 4.3) {
            if (weeks == 1) {
                return "a week ago";
            } else {
                return weeks + " weeks ago";
            }
        }
        //Months
        else if (months <= 12) {
            if (months == 1) {
                return "a month ago";
            } else {
                return months + " months ago";
            }
        }
        //Years
        else {
            if (years == 1) {
                return "one year ago";
            } else {
                return years + " years ago";
            }
        }
	}

	@Override
	public Page<ReplyDTO> findByCommentId(Pageable pageable, Long id) {
		log.debug("Request to get Reply from commentId : {}", id);
        return replyRepository.findByCommentId(pageable,id)
            .map(replyMapper::toDto);
        
        
		
	}
}
