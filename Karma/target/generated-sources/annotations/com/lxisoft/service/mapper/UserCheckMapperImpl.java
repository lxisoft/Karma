package com.lxisoft.service.mapper;

import com.lxisoft.domain.Comment;
import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.Need;
import com.lxisoft.domain.NewsFeed;
import com.lxisoft.domain.Reply;
import com.lxisoft.domain.UserCheck;
import com.lxisoft.domain.Violation;
import com.lxisoft.service.dto.UserCheckDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-09T12:30:24+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class UserCheckMapperImpl implements UserCheckMapper {

    @Autowired
    private NeedMapper needMapper;
    @Autowired
    private LoggedUserMapper loggedUserMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private NewsFeedMapper newsFeedMapper;
    @Autowired
    private ViolationMapper violationMapper;

    @Override
    public List<UserCheck> toEntity(List<UserCheckDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserCheck> list = new ArrayList<UserCheck>( dtoList.size() );
        for ( UserCheckDTO userCheckDTO : dtoList ) {
            list.add( toEntity( userCheckDTO ) );
        }

        return list;
    }

    @Override
    public List<UserCheckDTO> toDto(List<UserCheck> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserCheckDTO> list = new ArrayList<UserCheckDTO>( entityList.size() );
        for ( UserCheck userCheck : entityList ) {
            list.add( toDto( userCheck ) );
        }

        return list;
    }

    @Override
    public UserCheckDTO toDto(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }

        UserCheckDTO userCheckDTO = new UserCheckDTO();

        Long id = userCheckNewsFeedId( userCheck );
        if ( id != null ) {
            userCheckDTO.setNewsFeedId( id );
        }
        Long id1 = userCheckCheckedUserId( userCheck );
        if ( id1 != null ) {
            userCheckDTO.setCheckedUserId( id1 );
        }
        Long id2 = userCheckCheckedNeedId( userCheck );
        if ( id2 != null ) {
            userCheckDTO.setCheckedNeedId( id2 );
        }
        Long id3 = userCheckReplyId( userCheck );
        if ( id3 != null ) {
            userCheckDTO.setReplyId( id3 );
        }
        Long id4 = userCheckCommentId( userCheck );
        if ( id4 != null ) {
            userCheckDTO.setCommentId( id4 );
        }
        Long id5 = userCheckViolationId( userCheck );
        if ( id5 != null ) {
            userCheckDTO.setViolationId( id5 );
        }
        userCheckDTO.setId( userCheck.getId() );
        userCheckDTO.setVoteType( userCheck.getVoteType() );
        userCheckDTO.setCategory( userCheck.getCategory() );

        return userCheckDTO;
    }

    @Override
    public UserCheck toEntity(UserCheckDTO userCheckDTO) {
        if ( userCheckDTO == null ) {
            return null;
        }

        UserCheck userCheck = new UserCheck();

        userCheck.setViolation( violationMapper.fromId( userCheckDTO.getViolationId() ) );
        userCheck.setCheckedNeed( needMapper.fromId( userCheckDTO.getCheckedNeedId() ) );
        userCheck.setComment( commentMapper.fromId( userCheckDTO.getCommentId() ) );
        userCheck.setCheckedUser( loggedUserMapper.fromId( userCheckDTO.getCheckedUserId() ) );
        userCheck.setReply( replyMapper.fromId( userCheckDTO.getReplyId() ) );
        userCheck.setNewsFeed( newsFeedMapper.fromId( userCheckDTO.getNewsFeedId() ) );
        userCheck.setId( userCheckDTO.getId() );
        userCheck.setVoteType( userCheckDTO.getVoteType() );
        userCheck.setCategory( userCheckDTO.getCategory() );

        return userCheck;
    }

    private Long userCheckNewsFeedId(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }
        NewsFeed newsFeed = userCheck.getNewsFeed();
        if ( newsFeed == null ) {
            return null;
        }
        Long id = newsFeed.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userCheckCheckedUserId(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }
        LoggedUser checkedUser = userCheck.getCheckedUser();
        if ( checkedUser == null ) {
            return null;
        }
        Long id = checkedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userCheckCheckedNeedId(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }
        Need checkedNeed = userCheck.getCheckedNeed();
        if ( checkedNeed == null ) {
            return null;
        }
        Long id = checkedNeed.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userCheckReplyId(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }
        Reply reply = userCheck.getReply();
        if ( reply == null ) {
            return null;
        }
        Long id = reply.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userCheckCommentId(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }
        Comment comment = userCheck.getComment();
        if ( comment == null ) {
            return null;
        }
        Long id = comment.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userCheckViolationId(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }
        Violation violation = userCheck.getViolation();
        if ( violation == null ) {
            return null;
        }
        Long id = violation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
