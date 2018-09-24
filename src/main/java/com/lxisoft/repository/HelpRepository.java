package com.lxisoft.repository;

import com.lxisoft.domain.Help;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Help entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HelpRepository extends JpaRepository<Help, Long> {

}
