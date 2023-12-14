package com.ra.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailRequest {
    private Integer productId ;
    private Integer quantity ;
    private Double price ;
}
