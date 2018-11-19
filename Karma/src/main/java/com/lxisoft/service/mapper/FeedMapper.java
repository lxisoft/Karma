package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.FeedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Feed and its DTO FeedDTO.
 */
@Mapper(componentModel = "spring", uses = {RegisteredUserMapper.class})
public interface FeedMapper extends EntityMapper<FeedDTO, Feed> {

    @Mapping(source = "registeredUser.id", target = "registeredUserId")
    FeedDTO toDto(Feed feed);

    @Mapping(source = "registeredUserId", target = "registeredUser")
    Feed toEntity(FeedDTO feedDTO);

    default Feed fromId(Long id) {
        if (id == null) {
            return null;
        }
        Feed feed = new Feed();
        feed.setId(id);
        return feed;
    }
}
