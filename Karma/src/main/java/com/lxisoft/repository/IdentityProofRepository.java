package com.lxisoft.repository;

import com.lxisoft.domain.IdentityProof;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IdentityProof entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdentityProofRepository extends JpaRepository<IdentityProof, Long> {

}
