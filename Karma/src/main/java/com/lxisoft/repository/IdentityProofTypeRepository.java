package com.lxisoft.repository;

import com.lxisoft.domain.IdentityProofType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IdentityProofType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdentityProofTypeRepository extends JpaRepository<IdentityProofType, Long> {

}
