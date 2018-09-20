package com.bytatech.repository;

import com.bytatech.domain.Need;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Need entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NeedRepository extends JpaRepository<Need, Long> {
    @Query("select distinct need from Need need left join fetch need.categories left join fetch need.willingUsers")
    List<Need> findAllWithEagerRelationships();

    @Query("select need from Need need left join fetch need.categories left join fetch need.willingUsers where need.id =:id")
    Need findOneWithEagerRelationships(@Param("id") Long id);

}
