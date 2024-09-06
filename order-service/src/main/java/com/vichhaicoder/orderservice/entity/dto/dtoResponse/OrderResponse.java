package com.vichhaicoder.orderservice.entity.dto.dtoResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private CustomerResponse customerResponse;
    private List<ProductResponse> productsResponse;
    private LocalDate orderDate;

}
