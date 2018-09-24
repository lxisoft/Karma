package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.HelpDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Help and its DTO HelpDTO.
 */
@Mapper(componentModel = "spring", uses = {ApprovalStatusMapper.class, LoggedUserMapper.class, NeedMapper.class})
public interface HelpMapper extends EntityMapper<HelpDTO, Help> {

    @Mapping(source = "approvalStatus.id", target = "approvalStatusId")
    @Mapping(source = "providedUser.id", target = "providedUserId")
    @Mapping(source = "fulfilledNeed.id", target = "fulfilledNeedId")
    HelpDTO toDto(Help help);

    @Mapping(source = "approvalStatusId", target = "approvalStatus")
    @Mapping(source = "providedUserId", target = "providedUser")
    @Mapping(source = "fulfilledNeedId", target = "fulfilledNeed")
    Help toEntity(HelpDTO helpDTO);

    default Help fromId(Long id) {
        if (id == null) {
            return null;
        }
        Help help = new Help();
        help.setId(id);
        return help;
    }
}
