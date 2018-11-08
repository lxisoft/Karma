package com.lxisoft.service.mapper;

import com.lxisoft.domain.Media;
import com.lxisoft.domain.Need;
import com.lxisoft.service.dto.MediaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-09-29T11:00:48+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class MediaMapperImpl implements MediaMapper {

    @Autowired
    private NeedMapper needMapper;

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

        media.setNeed( needMapper.fromId( mediaDTO.getNeedId() ) );
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
}
