package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.NewsFeedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NewsFeed and its DTO NewsFeedDTO.
 */
@Mapper(componentModel = "spring", uses = {LoggedUserMapper.class})
public interface NewsFeedMapper extends EntityMapper<NewsFeedDTO, NewsFeed> {

    @Mapping(source = "loggedUser.id", target = "loggedUserId")
    NewsFeedDTO toDto(NewsFeed newsFeed);

    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(source = "loggedUserId", target = "loggedUser")
    @Mapping(target = "userChecks", ignore = true)
    NewsFeed toEntity(NewsFeedDTO newsFeedDTO);

    default NewsFeed fromId(Long id) {
        if (id == null) {
            return null;
        }
        NewsFeed newsFeed = new NewsFeed();
        newsFeed.setId(id);
        return newsFeed;
    }
}
