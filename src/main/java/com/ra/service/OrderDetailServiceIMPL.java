package com.ra.service;

import com.ra.dto.request.OrderDetailRequest;
import com.ra.dto.response.OrderDetailResponse;
import com.ra.entity.OrderDetail;
import com.ra.entity.Orders;
import com.ra.entity.Product;
import com.ra.repository.OrderDetailRepository;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceIMPL implements OrderDetailService{
    @Autowired
    private OrderDetailRepository orderDetailRepository ;

    @Autowired
    private OrderService orderService ;

    @Autowired
    private ProductService productService ;

    @Override
    public List<OrderDetailResponse> findAll() {
        List<OrderDetailResponse> list = orderDetailRepository.findAll().stream().map(item ->
                OrderDetailResponse.builder()
                        .odId(item.getOdId())
                        .userName(item.getOrders().getUser().getFullName())
                        .productName(item.getProduct().getProductName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build()).toList();
        return list;
    }

    @Override
    public OrderDetail findById(Integer id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id) ;
        return orderDetail.orElse(null);
    }

    @Override
    public OrderDetail saveByOrderId(Integer idOrder, OrderDetailRequest orderDetailRequest) throws UserException {

        Orders orders = orderService.findById(idOrder) ;
        if (orders != null ) {
            Product product = productService.findById(orderDetailRequest.getProductId());
            OrderDetail orderDetail = OrderDetail.builder()
                    .orders(orders)
                    .product(product)
                    .price(orderDetailRequest.getPrice())
                    .quantity(orderDetailRequest.getQuantity())
                    .build();
            return orderDetailRepository.save(orderDetail) ;
        }
        throw new UserException("Khong ton tai gio hang cua nguoi dung");
    }

    @Override
    public void delete(Integer id) {
        orderDetailRepository.deleteById(id);
    }
}
