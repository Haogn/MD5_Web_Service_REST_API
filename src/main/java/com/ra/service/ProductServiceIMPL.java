package com.ra.service;

import com.ra.dto.response.ProductResponse;
import com.ra.entity.Product;
import com.ra.repository.ProductRepository;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements ProductService{
    @Autowired
    private ProductRepository productRepository ;
    @Override
    public List<Product> findAll() {
        List<Product> list = (List<Product>) productRepository.findAll() ;
        return list;
    }

    @Override
    public Page<ProductResponse> findAllByProductNameContainingIgnoreCase(String name, Pageable pageable) throws UserException {
        return productRepository.findAllByProductNameContainingIgnoreCase(name,pageable).map(item ->
                ProductResponse.builder()
                        .id(item.getId())
                        .productName(item.getProductName())
                        .productPrice(item.getProductPrice())
                        .categoryName(item.getCategory().getCategoryName())
                        .build());
    }

    @Override
    public Page<ProductResponse> findAllByProductPrice(Double a, Double b, Pageable pageable) {
        return productRepository.findAllByProductPrice(a,b, pageable).map(item ->
                ProductResponse.builder()
                        .id(item.getId())
                        .productName(item.getProductName())
                        .productPrice(item.getProductPrice())
                        .categoryName(item.getCategory().getCategoryName())
                        .build());
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id) ;
        return product.orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
