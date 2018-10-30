package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.NeedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Need and its DTO NeedDTO.
 */
@Mapper(componentModel = "spring", uses = {SeverityMapper.class, VerificationTeamMapper.class, ApprovalStatusMapper.class, CategoryMapper.class, LoggedUserMapper.class})
public interface NeedMapper extends EntityMapper<NeedDTO, Need> {

    @Mapping(source = "severity.id", target = "severityId")
    @Mapping(source = "verificationTeam.id", target = "verificationTeamId")
    @Mapping(source = "approvalStatus.id", target = "approvalStatusId")
    @Mapping(source = "postedUser.id", target = "postedUserId")
    NeedDTO toDto(Need need);

    @Mapping(target = "proofs", ignore = true)
    @Mapping(target = "helps", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(source = "severityId", target = "severity")
    @Mapping(source = "verificationTeamId", target = "verificationTeam")
    @Mapping(source = "approvalStatusId", target = "approvalStatus")
    @Mapping(source = "postedUserId", target = "postedUser")
    @Mapping(target = "userChecks", ignore = true)
    Need toEntity(NeedDTO needDTO);

    default Need fromId(Long id) {
        if (id == null) {
            return null;
        }
        Need need = new Need();
        need.setId(id);
        return need;
    }
}
