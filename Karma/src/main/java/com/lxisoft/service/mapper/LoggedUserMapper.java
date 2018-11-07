package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.LoggedUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LoggedUser and its DTO LoggedUserDTO.
 */
@Mapper(componentModel = "spring", uses = {MediaMapper.class})
public interface LoggedUserMapper extends EntityMapper<LoggedUserDTO, LoggedUser> {

    @Mapping(source = "profilePic.id", target = "profilePicId")
    LoggedUserDTO toDto(LoggedUser loggedUser);

    @Mapping(source = "profilePicId", target = "profilePic")
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "needs", ignore = true)
    @Mapping(target = "helps", ignore = true)
    @Mapping(target = "newsFeeds", ignore = true)
    @Mapping(target = "checkedNeeds", ignore = true)
    @Mapping(target = "verificationTeams", ignore = true)
    LoggedUser toEntity(LoggedUserDTO loggedUserDTO);

    default LoggedUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setId(id);
        return loggedUser;
    }
}
