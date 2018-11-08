package com.lxisoft.service.impl;

import com.lxisoft.service.UserCheckService;
import com.lxisoft.domain.UserCheck;
import com.lxisoft.repository.UserCheckRepository;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.service.mapper.UserCheckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UserCheck.
 */
@Service
@Transactional
public class UserCheckServiceImpl implements UserCheckService {

    private final Logger log = LoggerFactory.getLogger(UserCheckServiceImpl.class);

    private final UserCheckRepository userCheckRepository;

    private final UserCheckMapper userCheckMapper;

    public UserCheckServiceImpl(UserCheckRepository userCheckRepository, UserCheckMapper userCheckMapper) {
        this.userCheckRepository = userCheckRepository;
        this.userCheckMapper = userCheckMapper;
    }

    /**
     * Save a userCheck.
     *
     * @param userCheckDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UserCheckDTO save(UserCheckDTO userCheckDTO) {
        log.debug("Request to save UserCheck : {}", userCheckDTO);
       
        UserCheck userCheck = userCheckMapper.toEntity(userCheckDTO);
        userCheck = userCheckRepository.save(userCheck);
        return userCheckMapper.toDto(userCheck);
    }

    /**
     * Get all the userChecks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UserCheckDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserChecks");
        return userCheckRepository.findAll(pageable)
            .map(userCheckMapper::toDto);
    }


    /**
     * Get one userCheck by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UserCheckDTO> findOne(Long id) {
        log.debug("Request to get UserCheck : {}", id);
        return userCheckRepository.findById(id)
            .map(userCheckMapper::toDto);
    }

    /**
     * Delete the userCheck by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserCheck : {}", id);
        userCheckRepository.deleteById(id);
    }
    
    /**
     * Get one userCheck by Category,CheckedNeedId,CheckedUserId.
     *
     * @param Category the Category of the entity
     * 
     * @param CheckedNeedId the CheckedNeedId of the entity
     * 
     * @param CheckedUserId the CheckedUserId of the entity
     * 
     * @return the entity
     */
    public Optional<UserCheckDTO> findByCategoryAndCheckedNeedIdAndCheckedUserId(String category,Long checkedNeedId, Long checkedUserId){
    	return userCheckRepository.findByCategoryAndCheckedNeedIdAndCheckedUserId(category,checkedNeedId,checkedUserId)
    			.map(userCheckMapper::toDto);
    	
    }

    /**
     * Get all the userChecks by checkedNeedId.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	@Override
	public Page<UserCheckDTO> findAllUserChecksByCheckedNeedId(Pageable pageable, Long checkedNeedId) {
		
	        log.debug("Request to get all UserChecks");
	        return userCheckRepository.findAllUserChecksByCheckedNeedId(pageable,checkedNeedId)
	            .map(userCheckMapper::toDto);
	    }
	
	 /**
     * Get all the userChecks by category.
     *
     * @param pageable the pagination information,category to find
     * @return the list of entities
     */

	@Override
	public Page<UserCheckDTO> findAllUserCheckByCategory(Pageable pageable,String category) {
		  log.debug("Request to get all UserChecks by category");
	        return userCheckRepository.findAllUserCheckByCategory(pageable,category)
	            .map(userCheckMapper::toDto);
	   
	}

	 /**
     * Get all the userChecks by category.
     *
     * @param pageable the pagination information,violationId to find
     * @return the list of entities
     */
	@Override
	public Page<UserCheckDTO> findAllUserCheckByViolationId(Pageable pageable, Long violationId) {
		 log.debug("Request to get all UserChecks by category");
	        return userCheckRepository.findAllUserCheckByViolationId(pageable,violationId)
	            .map(userCheckMapper::toDto);
	   
	}

	 /**
     * Get all the userChecks by violationId and checkedUserId.
     *
     * @param pageable the pagination information,violationId to find, checkedUserId
     * @return the list of entities
     */
	@Override
	public Page<UserCheckDTO> findAllUserCheckByViolationIdAndCheckedUserId(Pageable pageable, Long violationId, Long checkedUserId) {
		 log.debug("Request to get all UserChecks by violation id and user id");
	        return userCheckRepository.findAllUserCheckByViolationIdAndCheckedUserId(pageable,violationId,checkedUserId)
	            .map(userCheckMapper::toDto);
	   
	}
	
	 /**
     * Get all the userChecks by commentId.
     *
     * @param pageable the pagination information,violationId to find
     * @return the list of entities
     */

	@Override
	public Page<UserCheckDTO> findAllUserCheckByCommentId(Pageable pageable, Long commentId) {
		 log.debug("Request to get all UserChecks by comment Id");
	        return userCheckRepository.findAllUserCheckByCommentId(pageable,commentId)
	            .map(userCheckMapper::toDto);
	   
	}

	  /**
     * Get all the userChecks by commentId.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	
	@Override
	public Page<UserCheckDTO> findAllUserChecksByCommentId(Long commentId, Pageable pageable) {
		log.debug("requset to get all user checks with comment Id:",commentId);
		
		return userCheckRepository.findByCommentIdIs(commentId,pageable).map(userCheckMapper::toDto);
	}

	@Override
	public Page<UserCheckDTO> findAllUserCheckByReplyId(Pageable pageable, Long replyId) {
		
log.debug("requset to get all user checks with comment Id:",replyId);
		
		return userCheckRepository.findAllByReplyIdIs(replyId,pageable).map(userCheckMapper::toDto);
	}

}
