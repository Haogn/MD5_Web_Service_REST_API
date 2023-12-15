package com.ra.controller;

import com.ra.dto.request.OrderDetailRequest;
import com.ra.dto.response.OrderDetailResponse;
import com.ra.dto.response.OrderResponse;
import com.ra.entity.OrderDetail;
import com.ra.service.OrderDetailService;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/od-detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService ;

    @GetMapping("")
    public ResponseEntity<List<OrderDetailResponse>> getAll(){
        List<OrderDetailResponse> list = orderDetailService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK) ;
    }

    @PostMapping("/{idOrder}")
    public ResponseEntity<OrderDetail> createOrderDetail(@PathVariable Integer idOrder, @RequestBody OrderDetailRequest orderDetailRequest) throws UserException {
        return new ResponseEntity<>(orderDetailService.saveByOrderId(idOrder,orderDetailRequest), HttpStatus.CREATED);
    }
}
