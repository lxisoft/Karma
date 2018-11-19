/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lxisoft.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.HelpDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.UserCheckDTO;

/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

/**
 * Service Interface for managing all domains.
 */
public interface AggregateService {
		
	/**
     * Save a need.
     *
     * @param needDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
    NeedDTO saveNeed(NeedDTO needDTO) throws IOException;
    
	/**
     * Save a need.
     *
     * @param needDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
    NeedDTO saveNeedAsPending(NeedDTO needDTO) throws IOException;
    
    /**
     * Get all the needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
    Page<NeedDTO> findAllNeeds(Pageable pageable);

    /**
     * Get all the Need with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NeedDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" need.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
    Optional<NeedDTO> findOneNeed(Long id);

    /**
     * Delete the "id" need.
     *
     * @param id the id of the entity
     */
    void deleteNeed(Long id);
    
    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of approvedstatus entities
     */
    Page<NeedDTO> findAllNeedsByApprovedStatus(Pageable pageable, String approvalStatus);
    
    /**
     * Get all the SeverityId needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
    Page<NeedDTO> findAllNeedsBySeverityId(Pageable pageable,Long severityId);
    
    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of approvedstatus entities
     */
	Page<NeedDTO> findAllNeedsByApprovalStatusId(Pageable pageable,Long approvalStatusId);
	
	/**
     * find pending status id.
     *
     * @param status the status of the entity
     */
    Optional<ApprovalStatusDTO> findNeedByApprovalStatus(String approvalStatus);
    
    /**
	 * @param pageable
	 * @param checkedNeedId
	 * @return
	 */
	Page<UserCheckDTO> findAllUserChecksByCheckedNeedId(Pageable pageable, Long checkedNeedId);

	/**
     * Get all the approvalStatuses.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<ApprovalStatusDTO> findAllApprovalStatuses(Pageable pageable);

	 /**
     * Get the "id" approvalStatus.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
	Optional<ApprovalStatusDTO> findOneApprovalStatus(Long id);

	/**
     * Get all the categories.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<CategoryDTO> findAllCategories(Pageable pageable);

	 /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
	HelpDTO saveHelpAsIncomplete(HelpDTO helpDTO);
    
	 /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * 
     * @return the persisted entity
     * 
     * @throws IOException 
     */
	HelpDTO saveHelpAsComplete(HelpDTO helpDTO);

	/**
     * Get all the helps.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	Page<HelpDTO> findAllHelps(Pageable pageable);

	/**
     * Get the "id" help.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
	Optional<HelpDTO> findOneHelp(Long id);

	/**
     * Delete the "id" help.
     *
     * @param id the id of the entity
     */
	void deleteHelp(Long id);

	/**
   	 * @param pageable
   	 * @param approvalStatus
   	 * @return
   	 */
	Page<HelpDTO> findAllHelpsByApprovedStatus(Pageable pageable, String approvalStatus);



}
