package com.lxisoft.service.mapper;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.Help;
import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.Need;
import com.lxisoft.service.dto.HelpDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-13T11:32:41+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_171 (Oracle Corporation)"
)
@Component
public class HelpMapperImpl implements HelpMapper {

    @Autowired
    private ApprovalStatusMapper approvalStatusMapper;
    @Autowired
    private LoggedUserMapper loggedUserMapper;
    @Autowired
    private NeedMapper needMapper;

    @Override
    public List<Help> toEntity(List<HelpDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Help> list = new ArrayList<Help>( dtoList.size() );
        for ( HelpDTO helpDTO : dtoList ) {
            list.add( toEntity( helpDTO ) );
        }

        return list;
    }

    @Override
    public List<HelpDTO> toDto(List<Help> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<HelpDTO> list = new ArrayList<HelpDTO>( entityList.size() );
        for ( Help help : entityList ) {
            list.add( toDto( help ) );
        }

        return list;
    }

    @Override
    public HelpDTO toDto(Help help) {
        if ( help == null ) {
            return null;
        }

        HelpDTO helpDTO = new HelpDTO();

        Long id = helpProvidedUserId( help );
        if ( id != null ) {
            helpDTO.setProvidedUserId( id );
        }
        Long id1 = helpApprovalStatusId( help );
        if ( id1 != null ) {
            helpDTO.setApprovalStatusId( id1 );
        }
        Long id2 = helpFulfilledNeedId( help );
        if ( id2 != null ) {
            helpDTO.setFulfilledNeedId( id2 );
        }
        helpDTO.setId( help.getId() );
        helpDTO.setTime( help.getTime() );
        helpDTO.setDescription( help.getDescription() );

        return helpDTO;
    }

    @Override
    public Help toEntity(HelpDTO helpDTO) {
        if ( helpDTO == null ) {
            return null;
        }

        Help help = new Help();

        help.setApprovalStatus( approvalStatusMapper.fromId( helpDTO.getApprovalStatusId() ) );
        help.setFulfilledNeed( needMapper.fromId( helpDTO.getFulfilledNeedId() ) );
        help.setProvidedUser( loggedUserMapper.fromId( helpDTO.getProvidedUserId() ) );
        help.setId( helpDTO.getId() );
        help.setTime( helpDTO.getTime() );
        help.setDescription( helpDTO.getDescription() );

        return help;
    }

    private Long helpProvidedUserId(Help help) {
        if ( help == null ) {
            return null;
        }
        LoggedUser providedUser = help.getProvidedUser();
        if ( providedUser == null ) {
            return null;
        }
        Long id = providedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long helpApprovalStatusId(Help help) {
        if ( help == null ) {
            return null;
        }
        ApprovalStatus approvalStatus = help.getApprovalStatus();
        if ( approvalStatus == null ) {
            return null;
        }
        Long id = approvalStatus.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long helpFulfilledNeedId(Help help) {
        if ( help == null ) {
            return null;
        }
        Need fulfilledNeed = help.getFulfilledNeed();
        if ( fulfilledNeed == null ) {
            return null;
        }
        Long id = fulfilledNeed.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
