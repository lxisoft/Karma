package com.lxisoft.repository;

import com.lxisoft.domain.ApprovalStatus;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApprovalStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Long> {
		

	/**
	 * @param approvalStatus
	 * @return
	 */
	Optional<ApprovalStatus> findNeedByStatus(String approvalStatus);

}
