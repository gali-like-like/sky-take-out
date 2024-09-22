package com.sky.convert;

import com.sky.entity.AddressBook;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressBookConvert {

    AddressBookConvert INSTANCE = Mappers.getMapper(AddressBookConvert.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void copyProperties(AddressBook source, @MappingTarget AddressBook target);
}
