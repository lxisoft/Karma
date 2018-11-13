package com.lxisoft.service.mapper;

import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.Media;
import com.lxisoft.service.dto.LoggedUserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-13T09:47:36+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class LoggedUserMapperImpl implements LoggedUserMapper {

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public List<LoggedUser> toEntity(List<LoggedUserDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<LoggedUser> list = new ArrayList<LoggedUser>( dtoList.size() );
        for ( LoggedUserDTO loggedUserDTO : dtoList ) {
            list.add( toEntity( loggedUserDTO ) );
        }

        return list;
    }

    @Override
    public List<LoggedUserDTO> toDto(List<LoggedUser> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LoggedUserDTO> list = new ArrayList<LoggedUserDTO>( entityList.size() );
        for ( LoggedUser loggedUser : entityList ) {
            list.add( toDto( loggedUser ) );
        }

        return list;
    }

    @Override
    public LoggedUserDTO toDto(LoggedUser loggedUser) {
        if ( loggedUser == null ) {
            return null;
        }

        LoggedUserDTO loggedUserDTO = new LoggedUserDTO();

        Long id = loggedUserProfilePicId( loggedUser );
        if ( id != null ) {
            loggedUserDTO.setProfilePicId( id );
        }
        loggedUserDTO.setId( loggedUser.getId() );
        loggedUserDTO.setEmail( loggedUser.getEmail() );
        loggedUserDTO.setFirstName( loggedUser.getFirstName() );
        loggedUserDTO.setLastName( loggedUser.getLastName() );
        loggedUserDTO.setRating( loggedUser.getRating() );
        loggedUserDTO.setDescription( loggedUser.getDescription() );
        loggedUserDTO.setProfession( loggedUser.getProfession() );
        loggedUserDTO.setGender( loggedUser.getGender() );
        loggedUserDTO.setDob( loggedUser.getDob() );
        loggedUserDTO.setBloodGroup( loggedUser.getBloodGroup() );
        loggedUserDTO.setEmotionalQuotient( loggedUser.getEmotionalQuotient() );
        loggedUserDTO.setSocialQuotient( loggedUser.getSocialQuotient() );
        loggedUserDTO.setHappinessIndex( loggedUser.getHappinessIndex() );

        return loggedUserDTO;
    }

    @Override
    public LoggedUser toEntity(LoggedUserDTO loggedUserDTO) {
        if ( loggedUserDTO == null ) {
            return null;
        }

        LoggedUser loggedUser = new LoggedUser();

        loggedUser.setProfilePic( mediaMapper.fromId( loggedUserDTO.getProfilePicId() ) );
        loggedUser.setId( loggedUserDTO.getId() );
        loggedUser.setEmail( loggedUserDTO.getEmail() );
        loggedUser.setFirstName( loggedUserDTO.getFirstName() );
        loggedUser.setLastName( loggedUserDTO.getLastName() );
        loggedUser.setRating( loggedUserDTO.getRating() );
        loggedUser.setDescription( loggedUserDTO.getDescription() );
        loggedUser.setProfession( loggedUserDTO.getProfession() );
        loggedUser.setGender( loggedUserDTO.getGender() );
        loggedUser.setDob( loggedUserDTO.getDob() );
        loggedUser.setBloodGroup( loggedUserDTO.getBloodGroup() );
        loggedUser.setEmotionalQuotient( loggedUserDTO.getEmotionalQuotient() );
        loggedUser.setSocialQuotient( loggedUserDTO.getSocialQuotient() );
        loggedUser.setHappinessIndex( loggedUserDTO.getHappinessIndex() );

        return loggedUser;
    }

    private Long loggedUserProfilePicId(LoggedUser loggedUser) {
        if ( loggedUser == null ) {
            return null;
        }
        Media profilePic = loggedUser.getProfilePic();
        if ( profilePic == null ) {
            return null;
        }
        Long id = profilePic.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
