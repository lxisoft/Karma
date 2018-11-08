package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.SeverityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Severity and its DTO SeverityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SeverityMapper extends EntityMapper<SeverityDTO, Severity> {


    @Mapping(target = "needs", ignore = true)
    Severity toEntity(SeverityDTO severityDTO);

    default Severity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Severity severity = new Severity();
        severity.setId(id);
        return severity;
    }
}
