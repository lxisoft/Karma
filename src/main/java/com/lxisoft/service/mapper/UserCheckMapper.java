package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.UserCheckDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserCheck and its DTO UserCheckDTO.
 */
@Mapper(componentModel = "spring", uses = {NeedMapper.class})
public interface UserCheckMapper extends EntityMapper<UserCheckDTO, UserCheck> {

    @Mapping(source = "markedUser.id", target = "markedUserId")
    UserCheckDTO toDto(UserCheck userCheck);

    @Mapping(source = "markedUserId", target = "markedUser")
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
