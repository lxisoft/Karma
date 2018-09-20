package com.bytatech.repository;

import com.bytatech.domain.Severity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Severity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeverityRepository extends JpaRepository<Severity, Long> {

}
