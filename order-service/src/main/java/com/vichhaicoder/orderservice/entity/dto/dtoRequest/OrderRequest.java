package com.vichhaicoder.orderservice.entity.dto.dtoRequest;

import com.vichhaicoder.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long customerId;
    private List<Long> productIds;
    private LocalDate orderDate;

    public void requestOrder(Order order) {
        order.setCustomerId(customerId);
        order.setOrderDate(orderDate);
        order.setProductIds(productIds);
    }
}
