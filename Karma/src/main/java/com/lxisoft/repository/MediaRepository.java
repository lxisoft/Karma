package com.lxisoft.repository;

import com.lxisoft.domain.Media;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Media entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	
    Page<Media> findAllUrlByNeedId(Long needId,Pageable pageable);
	
	Page<Media> findAllUrlByHelpId(Long helpId,Pageable pageable);

}
