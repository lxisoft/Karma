package com.lxisoft.service.mapper;

import com.lxisoft.domain.Need;
import com.lxisoft.domain.UserCheck;
import com.lxisoft.service.dto.UserCheckDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-09-29T11:00:44+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class UserCheckMapperImpl implements UserCheckMapper {

    @Autowired
    private NeedMapper needMapper;

    @Override
    public List<UserCheck> toEntity(List<UserCheckDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserCheck> list = new ArrayList<UserCheck>( dtoList.size() );
        for ( UserCheckDTO userCheckDTO : dtoList ) {
            list.add( toEntity( userCheckDTO ) );
        }

        return list;
    }

    @Override
    public List<UserCheckDTO> toDto(List<UserCheck> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserCheckDTO> list = new ArrayList<UserCheckDTO>( entityList.size() );
        for ( UserCheck userCheck : entityList ) {
            list.add( toDto( userCheck ) );
        }

        return list;
    }

    @Override
    public UserCheckDTO toDto(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }

        UserCheckDTO userCheckDTO = new UserCheckDTO();

        Long id = userCheckMarkedUserId( userCheck );
        if ( id != null ) {
            userCheckDTO.setMarkedUserId( id );
        }
        userCheckDTO.setId( userCheck.getId() );
        userCheckDTO.setVoteType( userCheck.getVoteType() );
        userCheckDTO.setCategory( userCheck.getCategory() );

        return userCheckDTO;
    }

    @Override
    public UserCheck toEntity(UserCheckDTO userCheckDTO) {
        if ( userCheckDTO == null ) {
            return null;
        }

        UserCheck userCheck = new UserCheck();

        userCheck.setMarkedUser( needMapper.fromId( userCheckDTO.getMarkedUserId() ) );
        userCheck.setId( userCheckDTO.getId() );
        userCheck.setVoteType( userCheckDTO.getVoteType() );
        userCheck.setCategory( userCheckDTO.getCategory() );

        return userCheck;
    }

    private Long userCheckMarkedUserId(UserCheck userCheck) {
        if ( userCheck == null ) {
            return null;
        }
        Need markedUser = userCheck.getMarkedUser();
        if ( markedUser == null ) {
            return null;
        }
        Long id = markedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
