package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.PostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Post and its DTO PostDTO.
 */
@Mapper(componentModel = "spring", uses = {RegisteredUserMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Mapping(source = "registeredUser.id", target = "registeredUserId")
    PostDTO toDto(Post post);

    @Mapping(target = "attachments", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(source = "registeredUserId", target = "registeredUser")
    @Mapping(target = "userChecks", ignore = true)
    Post toEntity(PostDTO postDTO);

    default Post fromId(Long id) {
        if (id == null) {
            return null;
        }
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
