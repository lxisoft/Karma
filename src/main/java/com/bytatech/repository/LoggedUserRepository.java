package com.bytatech.repository;

import com.bytatech.domain.LoggedUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the LoggedUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoggedUserRepository extends JpaRepository<LoggedUser, Long> {

}
