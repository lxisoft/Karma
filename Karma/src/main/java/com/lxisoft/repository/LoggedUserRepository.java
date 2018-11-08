package com.lxisoft.repository;

import com.lxisoft.domain.LoggedUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoggedUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoggedUserRepository extends JpaRepository<LoggedUser, Long> {

}
