package com.lxisoft.repository;

import com.lxisoft.domain.NewsFeed;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NewsFeed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NewsFeedRepository extends JpaRepository<NewsFeed, Long> {

}
