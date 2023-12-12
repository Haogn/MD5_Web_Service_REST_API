package com.ra.dto.request;

public class ProductRequest {
    private String productName ;
    private Double productPrice ;
    private Integer categoryId ;

    public ProductRequest() {
    }

    public ProductRequest(String productName, Double productPrice, Integer categoryId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
