package com.ra.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderResponse {
    private Integer id;

    private String addressShip;

    private String phone;

    private String note;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOrder;

    private Boolean status;

    private String username;
}
