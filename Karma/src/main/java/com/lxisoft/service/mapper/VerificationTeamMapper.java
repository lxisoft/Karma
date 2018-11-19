package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.VerificationTeamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VerificationTeam and its DTO VerificationTeamDTO.
 */
@Mapper(componentModel = "spring", uses = {RegisteredUserMapper.class})
public interface VerificationTeamMapper extends EntityMapper<VerificationTeamDTO, VerificationTeam> {


    @Mapping(target = "needs", ignore = true)
    VerificationTeam toEntity(VerificationTeamDTO verificationTeamDTO);

    default VerificationTeam fromId(Long id) {
        if (id == null) {
            return null;
        }
        VerificationTeam verificationTeam = new VerificationTeam();
        verificationTeam.setId(id);
        return verificationTeam;
    }
}
