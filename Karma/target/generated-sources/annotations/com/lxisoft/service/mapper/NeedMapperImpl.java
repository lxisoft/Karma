package com.lxisoft.service.mapper;

import com.lxisoft.domain.ApprovalStatus;
import com.lxisoft.domain.Category;
import com.lxisoft.domain.LoggedUser;
import com.lxisoft.domain.Need;
import com.lxisoft.domain.Severity;
import com.lxisoft.domain.VerificationTeam;
import com.lxisoft.service.dto.CategoryDTO;
import com.lxisoft.service.dto.NeedDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-07T12:23:31+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class NeedMapperImpl implements NeedMapper {

    @Autowired
    private SeverityMapper severityMapper;
    @Autowired
    private VerificationTeamMapper verificationTeamMapper;
    @Autowired
    private ApprovalStatusMapper approvalStatusMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private LoggedUserMapper loggedUserMapper;

    @Override
    public List<Need> toEntity(List<NeedDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Need> list = new ArrayList<Need>( dtoList.size() );
        for ( NeedDTO needDTO : dtoList ) {
            list.add( toEntity( needDTO ) );
        }

        return list;
    }

    @Override
    public List<NeedDTO> toDto(List<Need> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NeedDTO> list = new ArrayList<NeedDTO>( entityList.size() );
        for ( Need need : entityList ) {
            list.add( toDto( need ) );
        }

        return list;
    }

    @Override
    public NeedDTO toDto(Need need) {
        if ( need == null ) {
            return null;
        }

        NeedDTO needDTO = new NeedDTO();

        Long id = needPostedUserId( need );
        if ( id != null ) {
            needDTO.setPostedUserId( id );
        }
        Long id1 = needVerificationTeamId( need );
        if ( id1 != null ) {
            needDTO.setVerificationTeamId( id1 );
        }
        Long id2 = needSeverityId( need );
        if ( id2 != null ) {
            needDTO.setSeverityId( id2 );
        }
        Long id3 = needApprovalStatusId( need );
        if ( id3 != null ) {
            needDTO.setApprovalStatusId( id3 );
        }
        needDTO.setId( need.getId() );
        needDTO.setDescription( need.getDescription() );
        needDTO.setBeneficiaryType( need.getBeneficiaryType() );
        needDTO.setDate( need.getDate() );
        needDTO.setCategories( categorySetToCategoryDTOSet( need.getCategories() ) );

        return needDTO;
    }

    @Override
    public Need toEntity(NeedDTO needDTO) {
        if ( needDTO == null ) {
            return null;
        }

        Need need = new Need();

        need.setSeverity( severityMapper.fromId( needDTO.getSeverityId() ) );
        need.setApprovalStatus( approvalStatusMapper.fromId( needDTO.getApprovalStatusId() ) );
        need.setPostedUser( loggedUserMapper.fromId( needDTO.getPostedUserId() ) );
        need.setVerificationTeam( verificationTeamMapper.fromId( needDTO.getVerificationTeamId() ) );
        need.setId( needDTO.getId() );
        need.setDescription( needDTO.getDescription() );
        need.setBeneficiaryType( needDTO.getBeneficiaryType() );
        need.setDate( needDTO.getDate() );
        need.setCategories( categoryDTOSetToCategorySet( needDTO.getCategories() ) );

        return need;
    }

    private Long needPostedUserId(Need need) {
        if ( need == null ) {
            return null;
        }
        LoggedUser postedUser = need.getPostedUser();
        if ( postedUser == null ) {
            return null;
        }
        Long id = postedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long needVerificationTeamId(Need need) {
        if ( need == null ) {
            return null;
        }
        VerificationTeam verificationTeam = need.getVerificationTeam();
        if ( verificationTeam == null ) {
            return null;
        }
        Long id = verificationTeam.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long needSeverityId(Need need) {
        if ( need == null ) {
            return null;
        }
        Severity severity = need.getSeverity();
        if ( severity == null ) {
            return null;
        }
        Long id = severity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long needApprovalStatusId(Need need) {
        if ( need == null ) {
            return null;
        }
        ApprovalStatus approvalStatus = need.getApprovalStatus();
        if ( approvalStatus == null ) {
            return null;
        }
        Long id = approvalStatus.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<CategoryDTO> categorySetToCategoryDTOSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDTO> set1 = new HashSet<CategoryDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryMapper.toDto( category ) );
        }

        return set1;
    }

    protected Set<Category> categoryDTOSetToCategorySet(Set<CategoryDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new HashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryDTO categoryDTO : set ) {
            set1.add( categoryMapper.toEntity( categoryDTO ) );
        }

        return set1;
    }
}
