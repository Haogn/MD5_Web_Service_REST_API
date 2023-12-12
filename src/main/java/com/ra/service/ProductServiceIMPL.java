package com.ra.service;

import com.ra.dto.response.ProductResponse;
import com.ra.entity.Product;
import com.ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
