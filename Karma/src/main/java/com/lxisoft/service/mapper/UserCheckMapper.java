package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.UserCheckDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserCheck and its DTO UserCheckDTO.
 */
@Mapper(componentModel = "spring", uses = {NeedMapper.class, RegisteredUserMapper.class, CommentMapper.class, ReplyMapper.class, PostMapper.class, HelpMapper.class})
public interface UserCheckMapper extends EntityMapper<UserCheckDTO, UserCheck> {

    @Mapping(source = "checkedNeed.id", target = "checkedNeedId")
    @Mapping(source = "checkedUser.id", target = "checkedUserId")
    @Mapping(source = "comment.id", target = "commentId")
    @Mapping(source = "reply.id", target = "replyId")
    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "checkedHelp.id", target = "checkedHelpId")
    UserCheckDTO toDto(UserCheck userCheck);

    @Mapping(source = "checkedNeedId", target = "checkedNeed")
    @Mapping(source = "checkedUserId", target = "checkedUser")
    @Mapping(source = "commentId", target = "comment")
    @Mapping(source = "replyId", target = "reply")
    @Mapping(source = "postId", target = "post")
    @Mapping(source = "checkedHelpId", target = "checkedHelp")
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
