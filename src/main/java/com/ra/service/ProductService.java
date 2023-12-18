package com.ra.service;

import com.ra.dto.response.ProductResponse;
import com.ra.entity.Product;
import com.ra.util.exception.UserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll() ;
    Page<ProductResponse> findAllByProductNameContainingIgnoreCase(String name , Pageable pageable) throws UserException;
    Page<ProductResponse> findAllByProductPrice(Double a , Double b, Pageable pageable ) ;
    Product findById(Integer id) ;
    Product save(Product product) ;
    void delete(Integer id) ;

}
