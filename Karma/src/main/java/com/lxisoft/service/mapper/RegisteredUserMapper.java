package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.RegisteredUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RegisteredUser and its DTO RegisteredUserDTO.
 */
@Mapper(componentModel = "spring", uses = {MediaMapper.class})
public interface RegisteredUserMapper extends EntityMapper<RegisteredUserDTO, RegisteredUser> {

    @Mapping(source = "profilePic.id", target = "profilePicId")
    RegisteredUserDTO toDto(RegisteredUser registeredUser);

    @Mapping(source = "profilePicId", target = "profilePic")
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "needs", ignore = true)
    @Mapping(target = "helps", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "feeds", ignore = true)
    @Mapping(target = "checkedNeeds", ignore = true)
    @Mapping(target = "verificationTeams", ignore = true)
    @Mapping(target = "followingUsers", ignore = true)
    RegisteredUser toEntity(RegisteredUserDTO registeredUserDTO);

    default RegisteredUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setId(id);
        return registeredUser;
    }
}
