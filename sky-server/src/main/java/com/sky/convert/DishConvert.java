package com.sky.convert;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DishConvert {

    DishConvert INSTANCE = Mappers.getMapper(DishConvert.class);

    DishVO dishToDishVO(Dish dish);

    // 定义映射方法，忽略 null 属性
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Dish dishDTOToDish(DishDTO dishDTO, @MappingTarget Dish dish);
}
