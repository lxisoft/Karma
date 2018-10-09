package com.lxisoft.repository;

import com.lxisoft.domain.Need;
import com.lxisoft.service.dto.NeedDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Need entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NeedRepository extends JpaRepository<Need, Long> {

    @Query(value = "select distinct need from Need need left join fetch need.categories",
        countQuery = "select count(distinct need) from Need need")
    Page<Need> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct need from Need need left join fetch need.categories")
    List<Need> findAllWithEagerRelationships();

    @Query("select need from Need need left join fetch need.categories where need.id =:id")
    Optional<Need> findOneWithEagerRelationships(@Param("id") Long id);
    
    Page<Need> findAllNeedsByApprovalStatusId(Pageable pageable, Long approvalStatusId);
    
    public Page<Need> findAllNeedsBySeverity(Pageable pageable,Long severityId);

}
