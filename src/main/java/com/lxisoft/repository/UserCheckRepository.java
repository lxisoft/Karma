package com.lxisoft.repository;

import com.lxisoft.domain.UserCheck;
import com.lxisoft.service.dto.UserCheckDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UserCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCheckRepository extends JpaRepository<UserCheck, Long> {

	/**
	 * @param pageable
	 * @return
	 */
	Page<UserCheck> findAllUserChecksByCheckedNeedId(Pageable pageable);

}
