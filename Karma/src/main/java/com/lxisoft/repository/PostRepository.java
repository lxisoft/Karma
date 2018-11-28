package com.lxisoft.repository;

import com.lxisoft.domain.Post;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Post entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	   //neeraja
	
		@Query(value="select count(p) from Post p where p.registeredUser.id=:registeredUserId")
		Long findCountOfPostsByRegisteredUserId(@Param("registeredUserId") Long registeredUserId);

		//neeraja


}
