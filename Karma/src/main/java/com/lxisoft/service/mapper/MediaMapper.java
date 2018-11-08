package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.MediaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Media and its DTO MediaDTO.
 */
@Mapper(componentModel = "spring", uses = {NeedMapper.class, HelpMapper.class, NewsFeedMapper.class, ViolationMapper.class})
public interface MediaMapper extends EntityMapper<MediaDTO, Media> {

    @Mapping(source = "need.id", target = "needId")
    @Mapping(source = "help.id", target = "helpId")
    @Mapping(source = "newsFeed.id", target = "newsFeedId")
    @Mapping(source = "violation.id", target = "violationId")
    MediaDTO toDto(Media media);

    @Mapping(source = "needId", target = "need")
    @Mapping(source = "helpId", target = "help")
    @Mapping(source = "newsFeedId", target = "newsFeed")
    @Mapping(source = "violationId", target = "violation")
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
