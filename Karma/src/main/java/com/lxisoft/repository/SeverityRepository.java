package com.lxisoft.repository;

import com.lxisoft.domain.Severity;
import com.lxisoft.service.dto.SeverityDTO;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Severity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeverityRepository extends JpaRepository<Severity, Long> {

	Optional<Severity> findBySeverityLevel(String severityLevel);

}
