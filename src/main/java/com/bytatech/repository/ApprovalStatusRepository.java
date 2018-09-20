package com.bytatech.repository;

import com.bytatech.domain.ApprovalStatus;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ApprovalStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Long> {

}
