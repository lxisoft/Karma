package com.lxisoft.repository;

import com.lxisoft.domain.Violation;
import com.lxisoft.service.dto.ViolationDTO;

import java.time.Instant;
import java.time.LocalDateTime;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Violation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {

	public Page<Violation> findViolationByIsAnonymous(Pageable pageable,Boolean isAnonymous);

	public Page<Violation> findViolationByDateAfter(Pageable pageable, Instant date);

	public Page<Violation> findViolationByDateBefore(Pageable pageable, Instant date);

	public Page<Violation> findViolationByDateBetween(Pageable pageable, Instant startDate, Instant endDate);
}
