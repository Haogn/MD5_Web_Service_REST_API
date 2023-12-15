package com.ra.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private Integer id ;
    private String productName ;
    private Double productPrice ;
    private String categoryName ;


}
