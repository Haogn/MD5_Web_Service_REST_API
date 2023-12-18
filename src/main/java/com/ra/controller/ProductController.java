package com.ra.controller;

import com.ra.dto.request.ProductRequest;
import com.ra.dto.response.ProductResponse;
import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import com.ra.util.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService ;
    @Autowired
    private CategoryService categoryService ;
    @GetMapping("")
    public ResponseEntity<List<ProductResponse>> getALl() {
        List<Product> productList = productService.findAll();
        List<ProductResponse> responseList = new ArrayList<>() ;
        for(Product pro : productList) {
            ProductResponse productResponse = new ProductResponse() ;
            productResponse.setId(pro.getId());
            productResponse.setProductName(pro.getProductName());
            productResponse.setProductPrice(pro.getProductPrice());
            productResponse.setCategoryName(pro.getCategory().getCategoryName());
            responseList.add(productResponse) ;
        }
        return new  ResponseEntity<>(responseList, HttpStatus.OK) ;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> getAllProductByProductName(@RequestParam String name,
                                                                            @PageableDefault(size = 2, page = 0, sort = "productName",direction = Sort.Direction.ASC) Pageable pageable) throws UserException {
        if (!name.isEmpty()) {
            return new ResponseEntity<>(productService.findAllByProductNameContainingIgnoreCase(name,pageable), HttpStatus.OK) ;
        }
        throw new UserException("Khong tim thay san pham trung khop") ;
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity<Page<ProductResponse>> getAllProductByPrice(@RequestParam Double a ,
                                                                      @RequestParam Double b ,
                                                                      @PageableDefault(size = 2, page = 0, sort = "productPrice", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(productService.findAllByProductPrice(a, b, pageable), HttpStatus.OK) ;
    }



    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable("id") Integer id) {
        Product product = productService.findById(id) ;
        ProductResponse productResponse = new ProductResponse() ;
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setProductPrice(product.getProductPrice());
        productResponse.setCategoryName(product.getCategory().getCategoryName());
        if (product != null ) {
            return new  ResponseEntity<>(productResponse, HttpStatus.OK) ;
         }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
    }

//    @PostMapping()
//    public ResponseEntity<Product> createProduct(@ModelAttribute Product product) {
//        Product newProduct = productService.save(product) ;
//        if ( newProduct != null) {
//            return new ResponseEntity<>(newProduct, HttpStatus.OK) ;
//        }
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
//    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        String newName = productRequest.getProductName() ;
        Double newPrice = productRequest.getProductPrice();
        Integer newCategoryId = productRequest.getCategoryId() ;
        Category category = categoryService.findById(newCategoryId) ;
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
        Product product = new Product() ;
        product.setProductName(newName);
        product.setProductPrice(newPrice);
        product.setCategory(category);

        Product createProduct = productService.save(product);
        if (createProduct == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
        return new ResponseEntity<>(createProduct, HttpStatus.OK) ;
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Product> editProduct(@PathVariable("id") Integer id ,@ModelAttribute Product product) {
//        Product editProduct = productService.findById(id) ;
//        if (editProduct != null) {
//            Product updateProduct = productService.save(product) ;
//            return new ResponseEntity<>(updateProduct, HttpStatus.OK) ;
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> editProduct(@PathVariable("id") Integer id ,@RequestBody ProductRequest productRequest) {
        Product product = productService.findById(id) ;
        String newName = productRequest.getProductName() ;
        Double newPrice = productRequest.getProductPrice();
        Integer newCategoryId = productRequest.getCategoryId() ;
        Category category = categoryService.findById(newCategoryId) ;
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        }
        if (product != null) {
            product.setId(id);
            product.setProductName(newName);
            product.setProductPrice(newPrice);
            product.setCategory(category);

           Product updateProduct = productService.save(product) ;

           ProductResponse newProduct = new ProductResponse() ;
           newProduct.setId(updateProduct.getId());
           newProduct.setProductName(updateProduct.getProductName());
           newProduct.setProductPrice(updateProduct.getProductPrice());
           newProduct.setCategoryName(updateProduct.getCategory().getCategoryName());
           return new ResponseEntity<>(newProduct, HttpStatus.OK) ;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id ){
        if (productService.findById(id) != null) {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
    }


}
