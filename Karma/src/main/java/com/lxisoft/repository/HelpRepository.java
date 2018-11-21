package com.lxisoft.repository;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.Help;
import com.lxisoft.service.dto.HelpDTO;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Help entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HelpRepository extends JpaRepository<Help, Long> {

	/**
	 * @param pageable
	 * @param approvalStatusId
	 * @return
	 */
	Page<Help> findAllHelpsByApprovalStatusId(Pageable pageable, Long approvalStatusId);
	
	/**
	 * @param pageable
	 * @param fulfilledNeedId
	 * @return
	 */
	Page<Help> findAllHelpsByfulfilledNeedId(Pageable pageable, Long fulfilledNeedId);


	

}
