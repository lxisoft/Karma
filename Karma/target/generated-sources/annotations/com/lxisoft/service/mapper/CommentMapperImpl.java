package com.lxisoft.service.mapper;

import com.lxisoft.domain.Comment;
import com.lxisoft.domain.Help;
import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.Need;
import com.lxisoft.domain.NewsFeed;
import com.lxisoft.domain.Violation;
import com.lxisoft.service.dto.CommentDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-12T12:18:30+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Autowired
    private NeedMapper needMapper;
    @Autowired
    private HelpMapper helpMapper;
    @Autowired
    private NewsFeedMapper newsFeedMapper;
    @Autowired
    private LoggedUserMapper loggedUserMapper;
    @Autowired
    private ViolationMapper violationMapper;

    @Override
    public List<Comment> toEntity(List<CommentDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( dtoList.size() );
        for ( CommentDTO commentDTO : dtoList ) {
            list.add( toEntity( commentDTO ) );
        }

        return list;
    }

    @Override
    public List<CommentDTO> toDto(List<Comment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommentDTO> list = new ArrayList<CommentDTO>( entityList.size() );
        for ( Comment comment : entityList ) {
            list.add( toDto( comment ) );
        }

        return list;
    }

    @Override
    public CommentDTO toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        Long id = commentNeedId( comment );
        if ( id != null ) {
            commentDTO.setNeedId( id );
        }
        Long id1 = commentNewsFeedId( comment );
        if ( id1 != null ) {
            commentDTO.setNewsFeedId( id1 );
        }
        Long id2 = commentHelpId( comment );
        if ( id2 != null ) {
            commentDTO.setHelpId( id2 );
        }
        Long id3 = commentCommentedUserId( comment );
        if ( id3 != null ) {
            commentDTO.setCommentedUserId( id3 );
        }
        Long id4 = commentViolationId( comment );
        if ( id4 != null ) {
            commentDTO.setViolationId( id4 );
        }
        commentDTO.setId( comment.getId() );
        commentDTO.setMessage( comment.getMessage() );
        commentDTO.setDate( comment.getDate() );

        return commentDTO;
    }

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setHelp( helpMapper.fromId( commentDTO.getHelpId() ) );
        comment.setNeed( needMapper.fromId( commentDTO.getNeedId() ) );
        comment.setCommentedUser( loggedUserMapper.fromId( commentDTO.getCommentedUserId() ) );
        comment.setViolation( violationMapper.fromId( commentDTO.getViolationId() ) );
        comment.setNewsFeed( newsFeedMapper.fromId( commentDTO.getNewsFeedId() ) );
        comment.setId( commentDTO.getId() );
        comment.setMessage( commentDTO.getMessage() );
        comment.setDate( commentDTO.getDate() );

        return comment;
    }

    private Long commentNeedId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Need need = comment.getNeed();
        if ( need == null ) {
            return null;
        }
        Long id = need.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentNewsFeedId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        NewsFeed newsFeed = comment.getNewsFeed();
        if ( newsFeed == null ) {
            return null;
        }
        Long id = newsFeed.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentHelpId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Help help = comment.getHelp();
        if ( help == null ) {
            return null;
        }
        Long id = help.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentCommentedUserId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        LoggedUser commentedUser = comment.getCommentedUser();
        if ( commentedUser == null ) {
            return null;
        }
        Long id = commentedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentViolationId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Violation violation = comment.getViolation();
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
