package com.lxisoft.service.mapper;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.service.dto.ApprovalStatusDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-09T09:42:20+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class ApprovalStatusMapperImpl implements ApprovalStatusMapper {

    @Override
    public ApprovalStatusDTO toDto(ApprovalStatus entity) {
        if ( entity == null ) {
            return null;
        }

        ApprovalStatusDTO approvalStatusDTO = new ApprovalStatusDTO();

        approvalStatusDTO.setId( entity.getId() );
        approvalStatusDTO.setStatus( entity.getStatus() );

        return approvalStatusDTO;
    }

    @Override
    public List<ApprovalStatus> toEntity(List<ApprovalStatusDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ApprovalStatus> list = new ArrayList<ApprovalStatus>( dtoList.size() );
        for ( ApprovalStatusDTO approvalStatusDTO : dtoList ) {
            list.add( toEntity( approvalStatusDTO ) );
        }

        return list;
    }

    @Override
    public List<ApprovalStatusDTO> toDto(List<ApprovalStatus> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ApprovalStatusDTO> list = new ArrayList<ApprovalStatusDTO>( entityList.size() );
        for ( ApprovalStatus approvalStatus : entityList ) {
            list.add( toDto( approvalStatus ) );
        }

        return list;
    }

    @Override
    public ApprovalStatus toEntity(ApprovalStatusDTO approvalStatusDTO) {
        if ( approvalStatusDTO == null ) {
            return null;
        }

        ApprovalStatus approvalStatus = new ApprovalStatus();

        approvalStatus.setId( approvalStatusDTO.getId() );
        approvalStatus.setStatus( approvalStatusDTO.getStatus() );

        return approvalStatus;
    }
}
