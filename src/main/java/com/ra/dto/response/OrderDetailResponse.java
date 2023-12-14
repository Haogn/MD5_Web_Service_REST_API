package com.ra.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailResponse {
    private Integer odId ;
    private String userName  ;
    private String productName;
    private Integer quantity ;
    private Double price ;

}
