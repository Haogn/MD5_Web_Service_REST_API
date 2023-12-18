package com.ra.repository;

import com.ra.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
//    Page<Product> findAllByProductNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findAllByProductNameContainingIgnoreCase(String name, Pageable pageable) ;
    @Query( value = "SELECT p FROM Product p WHERE p.productPrice BETWEEN :a AND :b")

    Page<Product> findAllByProductPrice(@Param("a") Double a , @Param("b") Double b, Pageable pageable);
}
