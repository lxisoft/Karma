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
package com.lxisoft.service.impl;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.Category;
import com.lxisoft.domain.Need;
import com.lxisoft.repository.ApprovalStatusRepository;
import com.lxisoft.repository.CategoryRepository;
import com.lxisoft.repository.NeedRepository;
import com.lxisoft.repository.UserCheckRepository;
import com.lxisoft.service.AggregateService;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.NeedDTO;
import com.lxisoft.service.dto.UserCheckDTO;
import com.lxisoft.service.mapper.ApprovalStatusMapper;
import com.lxisoft.service.mapper.CategoryMapper;
import com.lxisoft.service.mapper.NeedMapper;
import com.lxisoft.service.mapper.UserCheckMapper;



/**
 * TODO Provide a detailed description here 
 * @author Sarangi Balu
 * sarangibalu, sarangibalu.a@lxisoft.com
 */

/**
 * Service Implementation for managing all domains.
 */
@Service
@Transactional
public class AggregateServiceImpl implements AggregateService {
	
	private final Logger log = LoggerFactory.getLogger(AggregateServiceImpl.class);

	@Autowired
    NeedRepository needRepository;
	
	@Autowired
	ApprovalStatusRepository approvalStatusRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserCheckRepository userCheckRepository;

	@Autowired
    NeedMapper needMapper;
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Autowired
	ApprovalStatusMapper approvalStatusMapper;
	
	@Autowired
	UserCheckMapper userCheckMapper;
		
	
	 /**
     * Save a need.
     *
     * @param needDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
	@Override
	public NeedDTO saveNeed(NeedDTO needDTO) throws IOException {
		log.debug("Request to save Need : {}", needDTO);
		 Need need = needMapper.toEntity(needDTO);
	     need = needRepository.save(need);
	     return needMapper.toDto(need);
	}
	
	
	 /**
     * Save a need.
     *
     * @param needDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
    public NeedDTO saveNeedAsPending(NeedDTO needDTO) throws IOException {
        log.debug("Request to save Need : {}", needDTO);
        
        Set<CategoryDTO> categorySet=new HashSet<CategoryDTO>();
        
        if(needDTO.getApprovalStatusId()==null){
        	
        	Optional<ApprovalStatus> approvalStatus=approvalStatusRepository.findNeedByStatus("pending");
        	
        	Long id=approvalStatus.get().getId();
        	log.debug("***************{}"+id);
        	needDTO.setApprovalStatusId(approvalStatus.get().getId());
        }
        
        needDTO.setCategories(new HashSet<CategoryDTO>(needDTO.getCategoryList()));
        
        Set<CategoryDTO> categories=needDTO.getCategories();
        
       // System.out.println(categories);
        
        for(CategoryDTO categori:categories){
        	Long id=categori.getId();
        	Category categorie=categoryRepository.findById(id).orElse(null);
        	CategoryDTO categoree=categoryMapper.toDto(categorie);
           	categorySet.add(categoree);
            }
        needDTO.setCategories(categorySet);
        
        
         // parsing string to ISO_INSTANT format
         String parsedDate = needDTO.getDateInString().replaceAll(" ", "T").concat("Z");

         // creates a date instance of type instant from a string
     	needDTO.setDate(Instant.parse(parsedDate));

        Need need = needMapper.toEntity(needDTO);
        need = needRepository.save(need);
        return needMapper.toDto(need);
    }


    /**
     * Get all the needs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<NeedDTO> findAllNeeds(Pageable pageable) {
		log.debug("Request to get all Needs");
        return needRepository.findAll(pageable)
            .map(needMapper::toDto);
	}


    /**
     * Get all the Need with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
	@Override
	public Page<NeedDTO> findAllWithEagerRelationships(Pageable pageable) {
		return needRepository.findAllWithEagerRelationships(pageable).map(needMapper::toDto);
	}


	 /**
     * Get one need by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
	public Optional<NeedDTO> findOneNeed(Long id) {
		log.debug("Request to get Need : {}", id);
        return needRepository.findOneWithEagerRelationships(id)
        .map(needMapper::toDto);
	}


    /**
     * Delete the need by id.
     *
     * @param id the id of the entity
     */
    @Override
	public void deleteNeed(Long id) {
		log.debug("Request to delete Need : {}", id);
        needRepository.deleteById(id);		
	}

    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of approvedstatus entities
     */
    @Override
	public Page<NeedDTO> findAllNeedsByApprovedStatus(Pageable pageable, String approvalStatus) {
				
		    log.debug("Request to get all Needs by approval status");
		    
		    long approvalStatusId=findNeedByApprovalStatus(approvalStatus).get().getId();
	         	
	        Page<NeedDTO> page=findAllNeedsByApprovalStatusId(pageable,approvalStatusId);
	        
	        List<NeedDTO> needs = page.getContent();
	        
	        int count=0;
						
			for(NeedDTO need:needs)
			{
				
				log.info("*****************{}",need.getId());
				
				Page<UserCheckDTO> userCheckDTOs=findAllUserChecksByCheckedNeedId(pageable,need.getId());
				
				List<UserCheckDTO> userCheckDTOList = userCheckDTOs.getContent();
				
				for(UserCheckDTO userChecks:userCheckDTOList)
				{
					log.info("*************{}",userChecks.getId());
					
					if(userChecks.getVoteType()=="postive")
					{
						count=count+1;
					}
				}
				
				if(userCheckDTOList.size()==0)
					need.setPercentageOfGenuineness(null);
				else
				   need.setPercentageOfGenuineness(new Long((count/userCheckDTOList.size())*100));
												
									
			 }
			
			Page<NeedDTO> pagee = new PageImpl<NeedDTO>(needs, pageable, needs.size());
	         	
	   	    return pagee;
	}
    
    /**
     * Get all the approvedstatus needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of approvedstatus entities
     */ 
    @Override
    public Page<NeedDTO> findAllNeedsByApprovalStatusId(Pageable pageable,Long approvalStatusId){
    	
    	 log.debug("Request to get all Needs by approval status id{}",approvalStatusId);
    	
         return needRepository.findAllNeedsByApprovalStatusId(pageable,approvalStatusId)
             .map(needMapper::toDto);
    
    }
    
    /**
     * Get all the SeverityId needs.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
    @Override
	public Page<NeedDTO> findAllNeedsBySeverityId(Pageable pageable,Long severityId){
		
		 log.debug("Request to get all Needs by severity id{}",severityId);
	    	
		 return needRepository.findAllNeedsBySeverityId(pageable, severityId)
				 .map(needMapper::toDto);
		
	}
	
	 /**
     * get need by approvalstatus
     *
     * @param status the status of the entity
     */
    @Override
    public Optional<ApprovalStatusDTO> findNeedByApprovalStatus(String approvalStatus){
    	log.debug("Request to retreive pending status: {}",approvalStatus);
    	return approvalStatusRepository.findNeedByStatus(approvalStatus)
    			.map(approvalStatusMapper::toDto);
    		  
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
     * Get all the approvalStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
	public Page<ApprovalStatusDTO> findAllApprovalStatuses(Pageable pageable) {
    	log.debug("Request to get all ApprovalStatuses");
        return approvalStatusRepository.findAll(pageable)
        .map(approvalStatusMapper::toDto);
	}


    /**
     * Get one approvalStatus by id.
     *
     * @param id the id of the entity
     * 
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
	public Optional<ApprovalStatusDTO> findOneApprovalStatus(Long id) {
    	log.debug("Request to get ApprovalStatus : {}", id);
        return approvalStatusRepository.findById(id)
        .map(approvalStatusMapper::toDto);
	}


    /**
     * Get all the categories.
     *
     * @param pageable the pagination information
     * 
     * @return the list of entities
     */
	public Page<CategoryDTO> findAllCategories(Pageable pageable) {
		log.debug("Request to get all Categories");
        return categoryRepository.findAll(pageable)
            .map(categoryMapper::toDto);
	}


	



}
