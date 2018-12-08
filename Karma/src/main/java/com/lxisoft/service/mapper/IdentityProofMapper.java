package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.IdentityProofDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity IdentityProof and its DTO IdentityProofDTO.
 */
@Mapper(componentModel = "spring", uses = {IdentityProofTypeMapper.class})
public interface IdentityProofMapper extends EntityMapper<IdentityProofDTO, IdentityProof> {

    @Mapping(source = "identityProofType.id", target = "identityProofTypeId")
    IdentityProofDTO toDto(IdentityProof identityProof);

    @Mapping(source = "identityProofTypeId", target = "identityProofType")
    @Mapping(target = "owner", ignore = true)
    IdentityProof toEntity(IdentityProofDTO identityProofDTO);

    default IdentityProof fromId(Long id) {
        if (id == null) {
            return null;
        }
        IdentityProof identityProof = new IdentityProof();
        identityProof.setId(id);
        return identityProof;
    }
}
