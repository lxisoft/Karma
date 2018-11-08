package com.lxisoft.repository;

import com.lxisoft.domain.VerificationTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the VerificationTeam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VerificationTeamRepository extends JpaRepository<VerificationTeam, Long> {

    @Query(value = "select distinct verification_team from VerificationTeam verification_team left join fetch verification_team.approvingUsers",
        countQuery = "select count(distinct verification_team) from VerificationTeam verification_team")
    Page<VerificationTeam> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct verification_team from VerificationTeam verification_team left join fetch verification_team.approvingUsers")
    List<VerificationTeam> findAllWithEagerRelationships();

    @Query("select verification_team from VerificationTeam verification_team left join fetch verification_team.approvingUsers where verification_team.id =:id")
    Optional<VerificationTeam> findOneWithEagerRelationships(@Param("id") Long id);

}
