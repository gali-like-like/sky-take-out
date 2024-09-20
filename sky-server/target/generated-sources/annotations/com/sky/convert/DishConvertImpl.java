package com.sky.convert;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T15:26:28+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class DishConvertImpl implements DishConvert {

    @Override
    public DishVO dishToDishVO(Dish dish) {
        if ( dish == null ) {
            return null;
        }

        DishVO.DishVOBuilder dishVO = DishVO.builder();

        dishVO.id( dish.getId() );
        dishVO.name( dish.getName() );
        dishVO.categoryId( dish.getCategoryId() );
        dishVO.price( dish.getPrice() );
        dishVO.image( dish.getImage() );
        dishVO.description( dish.getDescription() );
        dishVO.status( dish.getStatus() );
        dishVO.updateTime( dish.getUpdateTime() );

        return dishVO.build();
    }

    @Override
    public Dish dishDTOToDish(DishDTO dishDTO, Dish dish) {
        if ( dishDTO == null ) {
            return dish;
        }

        if ( dishDTO.getId() != null ) {
            dish.setId( dishDTO.getId() );
        }
        if ( dishDTO.getName() != null ) {
            dish.setName( dishDTO.getName() );
        }
        if ( dishDTO.getCategoryId() != null ) {
            dish.setCategoryId( dishDTO.getCategoryId() );
        }
        if ( dishDTO.getPrice() != null ) {
            dish.setPrice( dishDTO.getPrice() );
        }
        if ( dishDTO.getImage() != null ) {
            dish.setImage( dishDTO.getImage() );
        }
        if ( dishDTO.getDescription() != null ) {
            dish.setDescription( dishDTO.getDescription() );
        }
        if ( dishDTO.getStatus() != null ) {
            dish.setStatus( dishDTO.getStatus() );
        }

        return dish;
    }
}
