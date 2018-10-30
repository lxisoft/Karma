package com.lxisoft.repository;

import com.lxisoft.domain.Media;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Spring Data  repository for the Media entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
	
	Optional<Media> findByFileName(String fileName);
	
    Page<Media> findAllUrlByNeedId(Long needId,Pageable pageable);
	
	Page<Media> findAllUrlByHelpId(Long helpId,Pageable pageable);

}
