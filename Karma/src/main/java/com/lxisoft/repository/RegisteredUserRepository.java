package com.lxisoft.repository;

import com.lxisoft.domain.RegisteredUser;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RegisteredUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

	//Code Starts : Dheeraj Das
	
	Page<RegisteredUser> findTop5ByOrderBySocialQuotientDesc(Pageable pageable);
	
	//Code Ends : Dheeraj Das
	
	//Code Starts : Dheeraj Das
	
	Page<RegisteredUser> findTop5ByOrderByEmotionalQuotientDesc(Pageable pageable);

	//Code Starts : Dheeraj Das
	
	Page<RegisteredUser> findAllRegisteredUsersByFirstNameStartingWith(Pageable pageable,String name);
		
	
}
