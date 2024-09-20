package com.sky.convert;

import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T15:26:28+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
public class OrderConvertImpl implements OrderConvert {

    @Override
    public OrderVO orderToOrderVo(Orders order, OrderVO orderVO) {
        if ( order == null ) {
            return orderVO;
        }

        if ( order.getId() != null ) {
            orderVO.setId( order.getId() );
        }
        if ( order.getNumber() != null ) {
            orderVO.setNumber( order.getNumber() );
        }
        if ( order.getStatus() != null ) {
            orderVO.setStatus( order.getStatus() );
        }
        if ( order.getUserId() != null ) {
            orderVO.setUserId( order.getUserId() );
        }
        if ( order.getAddressBookId() != null ) {
            orderVO.setAddressBookId( order.getAddressBookId() );
        }
        if ( order.getOrderTime() != null ) {
            orderVO.setOrderTime( order.getOrderTime() );
        }
        if ( order.getCheckoutTime() != null ) {
            orderVO.setCheckoutTime( order.getCheckoutTime() );
        }
        if ( order.getPayMethod() != null ) {
            orderVO.setPayMethod( order.getPayMethod() );
        }
        if ( order.getPayStatus() != null ) {
            orderVO.setPayStatus( order.getPayStatus() );
        }
        if ( order.getAmount() != null ) {
            orderVO.setAmount( order.getAmount() );
        }
        if ( order.getRemark() != null ) {
            orderVO.setRemark( order.getRemark() );
        }
        if ( order.getUserName() != null ) {
            orderVO.setUserName( order.getUserName() );
        }
        if ( order.getPhone() != null ) {
            orderVO.setPhone( order.getPhone() );
        }
        if ( order.getAddress() != null ) {
            orderVO.setAddress( order.getAddress() );
        }
        if ( order.getConsignee() != null ) {
            orderVO.setConsignee( order.getConsignee() );
        }
        if ( order.getCancelReason() != null ) {
            orderVO.setCancelReason( order.getCancelReason() );
        }
        if ( order.getRejectionReason() != null ) {
            orderVO.setRejectionReason( order.getRejectionReason() );
        }
        if ( order.getCancelTime() != null ) {
            orderVO.setCancelTime( order.getCancelTime() );
        }
        if ( order.getEstimatedDeliveryTime() != null ) {
            orderVO.setEstimatedDeliveryTime( order.getEstimatedDeliveryTime() );
        }
        if ( order.getDeliveryStatus() != null ) {
            orderVO.setDeliveryStatus( order.getDeliveryStatus() );
        }
        if ( order.getDeliveryTime() != null ) {
            orderVO.setDeliveryTime( order.getDeliveryTime() );
        }
        orderVO.setPackAmount( order.getPackAmount() );
        orderVO.setTablewareNumber( order.getTablewareNumber() );
        if ( order.getTablewareStatus() != null ) {
            orderVO.setTablewareStatus( order.getTablewareStatus() );
        }

        return orderVO;
    }
}
