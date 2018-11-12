package com.lxisoft.service.mapper;

import com.lxisoft.domain.Comment;
import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.Reply;
import com.lxisoft.service.dto.ReplyDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-12T09:35:45+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class ReplyMapperImpl implements ReplyMapper {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private LoggedUserMapper loggedUserMapper;

    @Override
    public List<Reply> toEntity(List<ReplyDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Reply> list = new ArrayList<Reply>( dtoList.size() );
        for ( ReplyDTO replyDTO : dtoList ) {
            list.add( toEntity( replyDTO ) );
        }

        return list;
    }

    @Override
    public List<ReplyDTO> toDto(List<Reply> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReplyDTO> list = new ArrayList<ReplyDTO>( entityList.size() );
        for ( Reply reply : entityList ) {
            list.add( toDto( reply ) );
        }

        return list;
    }

    @Override
    public ReplyDTO toDto(Reply reply) {
        if ( reply == null ) {
            return null;
        }

        ReplyDTO replyDTO = new ReplyDTO();

        Long id = replyCommentId( reply );
        if ( id != null ) {
            replyDTO.setCommentId( id );
        }
        Long id1 = replyRepliedUserId( reply );
        if ( id1 != null ) {
            replyDTO.setRepliedUserId( id1 );
        }
        replyDTO.setId( reply.getId() );
        replyDTO.setMessage( reply.getMessage() );
        replyDTO.setDate( reply.getDate() );

        return replyDTO;
    }

    @Override
    public Reply toEntity(ReplyDTO replyDTO) {
        if ( replyDTO == null ) {
            return null;
        }

        Reply reply = new Reply();

        reply.setComment( commentMapper.fromId( replyDTO.getCommentId() ) );
        reply.setRepliedUser( loggedUserMapper.fromId( replyDTO.getRepliedUserId() ) );
        reply.setId( replyDTO.getId() );
        reply.setMessage( replyDTO.getMessage() );
        reply.setDate( replyDTO.getDate() );

        return reply;
    }

    private Long replyCommentId(Reply reply) {
        if ( reply == null ) {
            return null;
        }
        Comment comment = reply.getComment();
        if ( comment == null ) {
            return null;
        }
        Long id = comment.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long replyRepliedUserId(Reply reply) {
        if ( reply == null ) {
            return null;
        }
        LoggedUser repliedUser = reply.getRepliedUser();
        if ( repliedUser == null ) {
            return null;
        }
        Long id = repliedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
