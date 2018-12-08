package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.MediaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Media and its DTO MediaDTO.
 */
@Mapper(componentModel = "spring", uses = {NeedMapper.class, HelpMapper.class, PostMapper.class})
public interface MediaMapper extends EntityMapper<MediaDTO, Media> {

    @Mapping(source = "need.id", target = "needId")
    @Mapping(source = "help.id", target = "helpId")
    @Mapping(source = "post.id", target = "postId")
    MediaDTO toDto(Media media);

    @Mapping(source = "needId", target = "need")
    @Mapping(source = "helpId", target = "help")
    @Mapping(source = "postId", target = "post")
    Media toEntity(MediaDTO mediaDTO);

    default Media fromId(Long id) {
        if (id == null) {
            return null;
        }
        Media media = new Media();
        media.setId(id);
        return media;
    }
}
