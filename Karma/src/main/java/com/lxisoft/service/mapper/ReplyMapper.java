package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.ReplyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reply and its DTO ReplyDTO.
 */
@Mapper(componentModel = "spring", uses = {CommentMapper.class, LoggedUserMapper.class})
public interface ReplyMapper extends EntityMapper<ReplyDTO, Reply> {

    @Mapping(source = "comment.id", target = "commentId")
    @Mapping(source = "repliedUser.id", target = "repliedUserId")
    ReplyDTO toDto(Reply reply);

    @Mapping(source = "commentId", target = "comment")
    @Mapping(source = "repliedUserId", target = "repliedUser")
    @Mapping(target = "userChecks", ignore = true)
    Reply toEntity(ReplyDTO replyDTO);

    default Reply fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reply reply = new Reply();
        reply.setId(id);
        return reply;
    }
}
