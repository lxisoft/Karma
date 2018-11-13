package com.lxisoft.service.mapper;

import com.lxisoft.domain.Violation;
import com.lxisoft.service.dto.ViolationDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-13T11:32:41+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class ViolationMapperImpl implements ViolationMapper {

    @Override
    public ViolationDTO toDto(Violation entity) {
        if ( entity == null ) {
            return null;
        }

        ViolationDTO violationDTO = new ViolationDTO();

        violationDTO.setId( entity.getId() );
        violationDTO.setDescription( entity.getDescription() );
        violationDTO.setIsAnonymous( entity.isIsAnonymous() );
        violationDTO.setDate( entity.getDate() );

        return violationDTO;
    }

    @Override
    public List<Violation> toEntity(List<ViolationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Violation> list = new ArrayList<Violation>( dtoList.size() );
        for ( ViolationDTO violationDTO : dtoList ) {
            list.add( toEntity( violationDTO ) );
        }

        return list;
    }

    @Override
    public List<ViolationDTO> toDto(List<Violation> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ViolationDTO> list = new ArrayList<ViolationDTO>( entityList.size() );
        for ( Violation violation : entityList ) {
            list.add( toDto( violation ) );
        }

        return list;
    }

    @Override
    public Violation toEntity(ViolationDTO violationDTO) {
        if ( violationDTO == null ) {
            return null;
        }

        Violation violation = new Violation();

        violation.setId( violationDTO.getId() );
        violation.setDescription( violationDTO.getDescription() );
        violation.setIsAnonymous( violationDTO.getIsAnonymous() );
        violation.setDate( violationDTO.getDate() );

        return violation;
    }
}
