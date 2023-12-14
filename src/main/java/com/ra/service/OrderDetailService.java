package com.ra.service;

import com.ra.dto.request.OrderDetailRequest;
import com.ra.dto.response.OrderDetailResponse;
import com.ra.entity.OrderDetail;
import com.ra.entity.Orders;
import com.ra.util.exception.UserException;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailResponse> findAll() ;
    OrderDetail findById(Integer id) ;

    OrderDetail saveByOrderId(Integer idOrder , OrderDetailRequest orderDetailRequest) throws UserException;

    void delete(Integer id) ;
}
