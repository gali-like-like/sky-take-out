package com.sky.convert;

import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderVO orderToOrderVo(Orders order);
}
