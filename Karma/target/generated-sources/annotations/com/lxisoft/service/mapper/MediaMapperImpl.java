package com.lxisoft.service.mapper;

import com.lxisoft.domain.Help;
import com.lxisoft.domain.Media;
import com.lxisoft.domain.Need;
import com.lxisoft.domain.NewsFeed;
import com.lxisoft.domain.Violation;
import com.lxisoft.service.dto.MediaDTO;
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
public class MediaMapperImpl implements MediaMapper {

    @Autowired
    private NeedMapper needMapper;
    @Autowired
    private HelpMapper helpMapper;
    @Autowired
    private NewsFeedMapper newsFeedMapper;
    @Autowired
    private ViolationMapper violationMapper;

    @Override
    public List<Media> toEntity(List<MediaDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Media> list = new ArrayList<Media>( dtoList.size() );
        for ( MediaDTO mediaDTO : dtoList ) {
            list.add( toEntity( mediaDTO ) );
        }

        return list;
    }

    @Override
    public List<MediaDTO> toDto(List<Media> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MediaDTO> list = new ArrayList<MediaDTO>( entityList.size() );
        for ( Media media : entityList ) {
            list.add( toDto( media ) );
        }

        return list;
    }

    @Override
    public MediaDTO toDto(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        Long id = mediaNeedId( media );
        if ( id != null ) {
            mediaDTO.setNeedId( id );
        }
        Long id1 = mediaNewsFeedId( media );
        if ( id1 != null ) {
            mediaDTO.setNewsFeedId( id1 );
        }
        Long id2 = mediaHelpId( media );
        if ( id2 != null ) {
            mediaDTO.setHelpId( id2 );
        }
        Long id3 = mediaViolationId( media );
        if ( id3 != null ) {
            mediaDTO.setViolationId( id3 );
        }
        mediaDTO.setId( media.getId() );
        mediaDTO.setFileName( media.getFileName() );
        mediaDTO.setUrl( media.getUrl() );
        mediaDTO.setExtension( media.getExtension() );

        return mediaDTO;
    }

    @Override
    public Media toEntity(MediaDTO mediaDTO) {
        if ( mediaDTO == null ) {
            return null;
        }

        Media media = new Media();

        media.setHelp( helpMapper.fromId( mediaDTO.getHelpId() ) );
        media.setNewsFeed( newsFeedMapper.fromId( mediaDTO.getNewsFeedId() ) );
        media.setNeed( needMapper.fromId( mediaDTO.getNeedId() ) );
        media.setViolation( violationMapper.fromId( mediaDTO.getViolationId() ) );
        media.setId( mediaDTO.getId() );
        media.setFileName( mediaDTO.getFileName() );
        media.setUrl( mediaDTO.getUrl() );
        media.setExtension( mediaDTO.getExtension() );

        return media;
    }

    private Long mediaNeedId(Media media) {
        if ( media == null ) {
            return null;
        }
        Need need = media.getNeed();
        if ( need == null ) {
            return null;
        }
        Long id = need.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long mediaNewsFeedId(Media media) {
        if ( media == null ) {
            return null;
        }
        NewsFeed newsFeed = media.getNewsFeed();
        if ( newsFeed == null ) {
            return null;
        }
        Long id = newsFeed.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long mediaHelpId(Media media) {
        if ( media == null ) {
            return null;
        }
        Help help = media.getHelp();
        if ( help == null ) {
            return null;
        }
        Long id = help.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long mediaViolationId(Media media) {
        if ( media == null ) {
            return null;
        }
        Violation violation = media.getViolation();
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
