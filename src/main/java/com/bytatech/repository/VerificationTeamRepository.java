package com.bytatech.repository;

import com.bytatech.domain.VerificationTeam;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the VerificationTeam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VerificationTeamRepository extends JpaRepository<VerificationTeam, Long> {
    @Query("select distinct verification_team from VerificationTeam verification_team left join fetch verification_team.approvingUsers")
    List<VerificationTeam> findAllWithEagerRelationships();

    @Query("select verification_team from VerificationTeam verification_team left join fetch verification_team.approvingUsers where verification_team.id =:id")
    VerificationTeam findOneWithEagerRelationships(@Param("id") Long id);

}
