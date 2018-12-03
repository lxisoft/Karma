package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.IdentityProofTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity IdentityProofType and its DTO IdentityProofTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IdentityProofTypeMapper extends EntityMapper<IdentityProofTypeDTO, IdentityProofType> {



    default IdentityProofType fromId(Long id) {
        if (id == null) {
            return null;
        }
        IdentityProofType identityProofType = new IdentityProofType();
        identityProofType.setId(id);
        return identityProofType;
    }
}
