package com.lxisoft.service.mapper;

import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.NewsFeed;
import com.lxisoft.service.dto.NewsFeedDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-07T12:23:31+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class NewsFeedMapperImpl implements NewsFeedMapper {

    @Autowired
    private LoggedUserMapper loggedUserMapper;

    @Override
    public List<NewsFeed> toEntity(List<NewsFeedDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NewsFeed> list = new ArrayList<NewsFeed>( dtoList.size() );
        for ( NewsFeedDTO newsFeedDTO : dtoList ) {
            list.add( toEntity( newsFeedDTO ) );
        }

        return list;
    }

    @Override
    public List<NewsFeedDTO> toDto(List<NewsFeed> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NewsFeedDTO> list = new ArrayList<NewsFeedDTO>( entityList.size() );
        for ( NewsFeed newsFeed : entityList ) {
            list.add( toDto( newsFeed ) );
        }

        return list;
    }

    @Override
    public NewsFeedDTO toDto(NewsFeed newsFeed) {
        if ( newsFeed == null ) {
            return null;
        }

        NewsFeedDTO newsFeedDTO = new NewsFeedDTO();

        Long id = newsFeedLoggedUserId( newsFeed );
        if ( id != null ) {
            newsFeedDTO.setLoggedUserId( id );
        }
        newsFeedDTO.setId( newsFeed.getId() );
        newsFeedDTO.setDescription( newsFeed.getDescription() );
        newsFeedDTO.setDate( newsFeed.getDate() );

        return newsFeedDTO;
    }

    @Override
    public NewsFeed toEntity(NewsFeedDTO newsFeedDTO) {
        if ( newsFeedDTO == null ) {
            return null;
        }

        NewsFeed newsFeed = new NewsFeed();

        newsFeed.setLoggedUser( loggedUserMapper.fromId( newsFeedDTO.getLoggedUserId() ) );
        newsFeed.setId( newsFeedDTO.getId() );
        newsFeed.setDescription( newsFeedDTO.getDescription() );
        newsFeed.setDate( newsFeedDTO.getDate() );

        return newsFeed;
    }

    private Long newsFeedLoggedUserId(NewsFeed newsFeed) {
        if ( newsFeed == null ) {
            return null;
        }
        LoggedUser loggedUser = newsFeed.getLoggedUser();
        if ( loggedUser == null ) {
            return null;
        }
        Long id = loggedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
