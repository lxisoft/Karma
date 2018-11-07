package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.ViolationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Violation and its DTO ViolationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ViolationMapper extends EntityMapper<ViolationDTO, Violation> {


    @Mapping(target = "proofs", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "userChecks", ignore = true)
    Violation toEntity(ViolationDTO violationDTO);

    default Violation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Violation violation = new Violation();
        violation.setId(id);
        return violation;
    }
}
