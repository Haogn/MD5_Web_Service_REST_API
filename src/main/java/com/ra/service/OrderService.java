package com.ra.service;

import com.ra.dto.request.OrderRequest;
import com.ra.dto.response.OrderResponse;
import com.ra.entity.Orders;
import com.ra.util.exception.UserException;

import java.util.List;

public interface OrderService {
//    List<OrderResponse> findAll() ;
    List<Orders> findAll();
    Orders findById(Integer id) ;

    Orders saveByUserId(Integer idUser , OrderRequest orderRequest) throws UserException;

    void delete(Integer id) ;
}
