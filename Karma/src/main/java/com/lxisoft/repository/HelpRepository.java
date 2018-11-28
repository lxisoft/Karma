package com.lxisoft.repository;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.Help;
import com.lxisoft.service.dto.HelpDTO;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
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
	
	
	@Query(value = "select count(h) from Help h where h.fulfilledNeed.id=:needId and h.approvalStatus.status=:approvalStatus")
	public Integer countOfHelpsByfulfilledNeedId(@Param("needId") Long needId,@Param("approvalStatus") String approvalStatus); 

	//neeraja

	/**
	 * @param registeredUserId
	 * @return 
	 */
	@Query(value="select count(h) from Help h where h.providedUser.id=:registeredUserId")
	Long findCountOfHelpsByRegisteredUserId(@Param("registeredUserId") Long registeredUserId);

	//neeraja

}
