package com.lxisoft.service;

import com.lxisoft.service.dto.HelpDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Optional;

/**
 * Service Interface for managing Help.
 */
public interface HelpService {

    /**
     * Save a help.
     *
     * @param helpDTO the entity to save
     * @return the persisted entity
     * @throws IOException 
     */
    HelpDTO save(HelpDTO helpDTO) throws IOException;

    /**
     * Get all the helps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HelpDTO> findAll(Pageable pageable);


    /**
     * Get the "id" help.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HelpDTO> findOne(Long id);

    /**
     * Delete the "id" help.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
   	 * @param pageable
   	 * @param approvalStatus
   	 * @return
   	 */
   	Page<HelpDTO> findAllHelpsByApprovedStatus(Pageable pageable, String approvalStatus);
   	
   	/**
   	 * @param pageable
   	 * @param approvalStatus
   	 * @return
   	 */
   	
   	Page<HelpDTO> findAllHelpsByApprovedStatusId(Pageable pageable, Long approvalStatusId);



   	
	/**
   	 * @param helpDto
   	 * @return
   	 */
   	public void countComments(HelpDTO helpDTO);
   	

   	/**
   	 * @param pageable
   	 *
   	 * @return
   	 */
	Page<HelpDTO> findAllHelps(Pageable pageable);

}
