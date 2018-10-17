package com.lxisoft.repository;

import com.lxisoft.domain.Media;
import com.lxisoft.service.dto.MediaDTO;

import java.util.List;
import java.util.Optional;

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

	Optional<Media> findByFileName(String fileName);

	Page<Media> findAllUrlByNeedId(Long needId,Pageable pageable);
}
