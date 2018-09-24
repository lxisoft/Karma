package com.lxisoft.repository;

import com.lxisoft.domain.ApprovalStatus;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ApprovalStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Long> {

}
