package com.lxisoft.service.mapper;

import com.lxisoft.domain.Address;
import com.lxisoft.domain.LoggedUser;
import com.lxisoft.service.dto.AddressDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-11-12T11:36:40+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Autowired
    private LoggedUserMapper loggedUserMapper;

    @Override
    public List<Address> toEntity(List<AddressDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Address> list = new ArrayList<Address>( dtoList.size() );
        for ( AddressDTO addressDTO : dtoList ) {
            list.add( toEntity( addressDTO ) );
        }

        return list;
    }

    @Override
    public List<AddressDTO> toDto(List<Address> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AddressDTO> list = new ArrayList<AddressDTO>( entityList.size() );
        for ( Address address : entityList ) {
            list.add( toDto( address ) );
        }

        return list;
    }

    @Override
    public AddressDTO toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDTO addressDTO = new AddressDTO();

        Long id = addressLoggedUserId( address );
        if ( id != null ) {
            addressDTO.setLoggedUserId( id );
        }
        addressDTO.setId( address.getId() );
        addressDTO.setHouseName( address.getHouseName() );
        addressDTO.setPlace( address.getPlace() );
        addressDTO.setCity( address.getCity() );
        addressDTO.setState( address.getState() );
        addressDTO.setCountry( address.getCountry() );
        addressDTO.setZip( address.getZip() );

        return addressDTO;
    }

    @Override
    public Address toEntity(AddressDTO addressDTO) {
        if ( addressDTO == null ) {
            return null;
        }

        Address address = new Address();

        address.setLoggedUser( loggedUserMapper.fromId( addressDTO.getLoggedUserId() ) );
        address.setId( addressDTO.getId() );
        address.setHouseName( addressDTO.getHouseName() );
        address.setPlace( addressDTO.getPlace() );
        address.setCity( addressDTO.getCity() );
        address.setState( addressDTO.getState() );
        address.setCountry( addressDTO.getCountry() );
        address.setZip( addressDTO.getZip() );

        return address;
    }

    private Long addressLoggedUserId(Address address) {
        if ( address == null ) {
            return null;
        }
        LoggedUser loggedUser = address.getLoggedUser();
        if ( loggedUser == null ) {
            return null;
        }
        Long id = loggedUser.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
