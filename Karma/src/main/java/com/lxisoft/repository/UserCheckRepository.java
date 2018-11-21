package com.lxisoft.repository;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.UserCheck;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


/**
 * Spring Data  repository for the UserCheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCheckRepository extends JpaRepository<UserCheck, Long> {

	/**
	 * @param pageable
	 * @return
	 */
	Page<UserCheck> findAllUserChecksByCheckedNeedId(Pageable pageable,Long checkedNeedId);

	/**
	 * @param category
	 * @param checkedNeedId
	 * @param checkedUserId
	 * @return
	 */
	Optional<UserCheck> findUserCheckByCategoryAndCheckedNeedIdAndCheckedUserId(String category,Long checkedNeedId, Long checkedUserId);
	
	/**
	 * 
	 * @param checkedHelpId
	 * @param voteType
	 * @return
	 */
	@Query(value = "select count(u) from UserCheck u where u.checkedHelp.id=:checkedHelpId and u.voteType=:voteType")
	Integer countOfVoteTypeLike(@Param("voteType") String voteType, @Param("checkedHelpId") Long checkedHelpId);

	/**
	 * 
	 * @param checkedHelpId
	 * @param voteType
	 * @return
	 */
	@Query(value = "select count(u) from UserCheck u where u.checkedHelp.id=:checkedHelpId and u.voteType=:voteType")
	Integer countOfVoteTypeDislike(@Param("voteType") String voteType, @Param("checkedHelpId") Long checkedHelpId);
	
	/**
	 * 
	 * @param CheckedNeedId
	 * @param voteType
	 * @return
	 */
	@Query(value = "select count(u) from UserCheck u where u.checkedNeed.id=:CheckedNeedId and u.voteType=:voteType")
	Integer countOfVoteTypeGenuiness(@Param("voteType") String voteType, @Param("CheckedNeedId") Long CheckedNeedId);
	
}
