package com.sky.convert;

import com.sky.entity.DishFlavor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DishFlavorConvert {

    DishFlavorConvert INSTANCE = Mappers.getMapper(DishFlavorConvert.class);

}
