package com.ra.service;

import com.ra.dto.request.OrderRequest;
import com.ra.dto.response.OrderResponse;
import com.ra.entity.Orders;
import com.ra.entity.User;
import com.ra.repository.OrderRepository;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceIMPL implements OrderService{
    @Autowired
    private OrderRepository orderRepository ;
    @Autowired
    private UserService userService ;
//    @Override
//    public List<OrderResponse> findAll() {
//        List<OrderResponse> list = orderRepository.findAll().stream().map(item-> OrderResponse.builder()
//                .id(item.getId())
//                .addressShip(item.getAddressShip())
//                .dateOrder(item.getDateOrder())
//                .note(item.getNote())
//                .phone(item.getPhone())
//                .status(item.isStatus())
//                .username(item.getUser().getFullName())
//                .build()).toList() ;
//        return list;
//    }

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Orders findById(Integer id) {
        Optional<Orders> orders = orderRepository.findById(id);
        return orders.orElse(null) ;
    }

    @Override
    public Orders saveByUserId(Integer idUser, OrderRequest orderRequest) throws UserException {
        User user = userService.findById(idUser) ;
        if (user != null) {
            Orders orders = Orders.builder()
                    .addressShip(orderRequest.getAddressShip())
                    .phone(orderRequest.getPhone())
                    .note(orderRequest.getNote())
                    .dateOrder(orderRequest.getDateOrder())
                    .status(false)
                    .user(user)
                    .build();
            return orderRepository.save(orders) ;
        }
        throw new UserException("Nguoi dung khong ton tai");
    }


    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
