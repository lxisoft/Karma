package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.UserCheckDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserCheck and its DTO UserCheckDTO.
 */
@Mapper(componentModel = "spring", uses = {NeedMapper.class, LoggedUserMapper.class})
public interface UserCheckMapper extends EntityMapper<UserCheckDTO, UserCheck> {

    @Mapping(source = "checkedNeed.id", target = "checkedNeedId")
    @Mapping(source = "checkedUser.id", target = "checkedUserId")
    UserCheckDTO toDto(UserCheck userCheck);

    @Mapping(source = "checkedNeedId", target = "checkedNeed")
    @Mapping(source = "checkedUserId", target = "checkedUser")
    UserCheck toEntity(UserCheckDTO userCheckDTO);

    default UserCheck fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserCheck userCheck = new UserCheck();
        userCheck.setId(id);
        return userCheck;
    }
}
