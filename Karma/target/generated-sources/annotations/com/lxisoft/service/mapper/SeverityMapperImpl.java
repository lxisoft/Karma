package com.lxisoft.service.mapper;

import com.lxisoft.domain.Severity;
import com.lxisoft.service.dto.SeverityDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-09T15:16:07+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class SeverityMapperImpl implements SeverityMapper {

    @Override
    public SeverityDTO toDto(Severity entity) {
        if ( entity == null ) {
            return null;
        }

        SeverityDTO severityDTO = new SeverityDTO();

        severityDTO.setId( entity.getId() );
        severityDTO.setSeverityLevel( entity.getSeverityLevel() );

        return severityDTO;
    }

    @Override
    public List<Severity> toEntity(List<SeverityDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Severity> list = new ArrayList<Severity>( dtoList.size() );
        for ( SeverityDTO severityDTO : dtoList ) {
            list.add( toEntity( severityDTO ) );
        }

        return list;
    }

    @Override
    public List<SeverityDTO> toDto(List<Severity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SeverityDTO> list = new ArrayList<SeverityDTO>( entityList.size() );
        for ( Severity severity : entityList ) {
            list.add( toDto( severity ) );
        }

        return list;
    }

    @Override
    public Severity toEntity(SeverityDTO severityDTO) {
        if ( severityDTO == null ) {
            return null;
        }

        Severity severity = new Severity();

        severity.setId( severityDTO.getId() );
        severity.setSeverityLevel( severityDTO.getSeverityLevel() );

        return severity;
    }
}
