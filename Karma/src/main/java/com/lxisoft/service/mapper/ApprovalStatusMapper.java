package com.lxisoft.service.mapper;

import com.lxisoft.domain.*;
import com.lxisoft.service.dto.ApprovalStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ApprovalStatus and its DTO ApprovalStatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ApprovalStatusMapper extends EntityMapper<ApprovalStatusDTO, ApprovalStatus> {


    @Mapping(target = "needs", ignore = true)
    @Mapping(target = "helps", ignore = true)
    ApprovalStatus toEntity(ApprovalStatusDTO approvalStatusDTO);

    default ApprovalStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setId(id);
        return approvalStatus;
    }
}
