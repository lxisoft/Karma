package com.lxisoft.repository;

import com.lxisoft.domain.UserCheck;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCheckRepository extends JpaRepository<UserCheck, Long> {

}
