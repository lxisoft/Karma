package com.lxisoft.repository;

import com.lxisoft.domain.Feed;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Feed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

	Page<Feed> findAllFeedsByRegisteredUserId(Pageable pageable, Long registeredUserId);

}
