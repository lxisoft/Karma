package com.lxisoft.repository;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.service.dto.ApprovalStatusDTO;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApprovalStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovalStatusRepository extends JpaRepository<ApprovalStatus, Long> {

	Optional<ApprovalStatus> findByStatus(String status);
}
