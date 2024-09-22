package com.sky.convert;

import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderVO orderToOrderVo(Orders order, @MappingTarget OrderVO orderVO);
}
