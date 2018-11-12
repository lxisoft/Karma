package com.lxisoft.service.mapper;

import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.VerificationTeam;
import com.lxisoft.service.dto.LoggedUserDTO;
import com.lxisoft.service.dto.VerificationTeamDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-12T11:36:39+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class VerificationTeamMapperImpl implements VerificationTeamMapper {

    @Autowired
    private LoggedUserMapper loggedUserMapper;

    @Override
    public VerificationTeamDTO toDto(VerificationTeam entity) {
        if ( entity == null ) {
            return null;
        }

        VerificationTeamDTO verificationTeamDTO = new VerificationTeamDTO();

        verificationTeamDTO.setId( entity.getId() );
        verificationTeamDTO.setApprovalLevel( entity.getApprovalLevel() );
        verificationTeamDTO.setApprovingUsers( loggedUserSetToLoggedUserDTOSet( entity.getApprovingUsers() ) );

        return verificationTeamDTO;
    }

    @Override
    public List<VerificationTeam> toEntity(List<VerificationTeamDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<VerificationTeam> list = new ArrayList<VerificationTeam>( dtoList.size() );
        for ( VerificationTeamDTO verificationTeamDTO : dtoList ) {
            list.add( toEntity( verificationTeamDTO ) );
        }

        return list;
    }

    @Override
    public List<VerificationTeamDTO> toDto(List<VerificationTeam> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<VerificationTeamDTO> list = new ArrayList<VerificationTeamDTO>( entityList.size() );
        for ( VerificationTeam verificationTeam : entityList ) {
            list.add( toDto( verificationTeam ) );
        }

        return list;
    }

    @Override
    public VerificationTeam toEntity(VerificationTeamDTO verificationTeamDTO) {
        if ( verificationTeamDTO == null ) {
            return null;
        }

        VerificationTeam verificationTeam = new VerificationTeam();

        verificationTeam.setId( verificationTeamDTO.getId() );
        verificationTeam.setApprovalLevel( verificationTeamDTO.getApprovalLevel() );
        verificationTeam.setApprovingUsers( loggedUserDTOSetToLoggedUserSet( verificationTeamDTO.getApprovingUsers() ) );

        return verificationTeam;
    }

    protected Set<LoggedUserDTO> loggedUserSetToLoggedUserDTOSet(Set<LoggedUser> set) {
        if ( set == null ) {
            return null;
        }

        Set<LoggedUserDTO> set1 = new HashSet<LoggedUserDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( LoggedUser loggedUser : set ) {
            set1.add( loggedUserMapper.toDto( loggedUser ) );
        }

        return set1;
    }

    protected Set<LoggedUser> loggedUserDTOSetToLoggedUserSet(Set<LoggedUserDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<LoggedUser> set1 = new HashSet<LoggedUser>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( LoggedUserDTO loggedUserDTO : set ) {
            set1.add( loggedUserMapper.toEntity( loggedUserDTO ) );
        }

        return set1;
    }
}
