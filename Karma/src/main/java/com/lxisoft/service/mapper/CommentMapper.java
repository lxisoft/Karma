package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.CommentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Comment and its DTO CommentDTO.
 */
@Mapper(componentModel = "spring", uses = {NeedMapper.class, HelpMapper.class, PostMapper.class, RegisteredUserMapper.class})
public interface CommentMapper extends EntityMapper<CommentDTO, Comment> {

    @Mapping(source = "need.id", target = "needId")
    @Mapping(source = "help.id", target = "helpId")
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "commentedUser.id", target = "commentedUserId")
    CommentDTO toDto(Comment comment);

    @Mapping(source = "needId", target = "need")
    @Mapping(source = "helpId", target = "help")
    @Mapping(source = "postId", target = "post")
    @Mapping(target = "replies", ignore = true)
    @Mapping(source = "commentedUserId", target = "commentedUser")
    @Mapping(target = "userChecks", ignore = true)
    Comment toEntity(CommentDTO commentDTO);

    default Comment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(id);
        return comment;
    }
}
