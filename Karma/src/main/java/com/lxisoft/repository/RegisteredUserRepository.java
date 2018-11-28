package com.lxisoft.repository;

import com.lxisoft.domain.RegisteredUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RegisteredUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

    @Query(value = "select distinct registered_user from RegisteredUser registered_user left join fetch registered_user.followers",
        countQuery = "select count(distinct registered_user) from RegisteredUser registered_user")
    Page<RegisteredUser> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct registered_user from RegisteredUser registered_user left join fetch registered_user.followers")
    List<RegisteredUser> findAllWithEagerRelationships();

    @Query("select registered_user from RegisteredUser registered_user left join fetch registered_user.followers where registered_user.id =:id")
    Optional<RegisteredUser> findOneWithEagerRelationships(@Param("id") Long id);

    Optional<RegisteredUser> findRegisteredUserById(Long registeredUserId);

}
