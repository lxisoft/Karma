package com.lxisoft.repository;

import com.lxisoft.domain.Violation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Violation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ViolationRepository extends JpaRepository<Violation, Long> {

}
