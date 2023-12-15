package com.ra.controller;

import com.ra.dto.request.OrderRequest;
import com.ra.dto.response.OrderResponse;
import com.ra.entity.Orders;
import com.ra.service.OrderService;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService ;

//    @GetMapping("")
//    public ResponseEntity<List<OrderResponse>> getAll(){
//        List<OrderResponse> list = orderService.findAll();
//        return new ResponseEntity<>(list, HttpStatus.OK) ;
//    }
@GetMapping("")
public ResponseEntity<List<Orders>> getAll(){
    List<Orders> list = orderService.findAll();
    return new ResponseEntity<>(list, HttpStatus.OK) ;
}

    @PostMapping("/{idUser}")
    public ResponseEntity<Orders> createOrderByUserId(@PathVariable("idUser") Integer idUser,
                                                      @RequestBody OrderRequest orderRequest) throws UserException {
        return new ResponseEntity<>(orderService.saveByUserId(idUser, orderRequest), HttpStatus.OK);
    }
}
