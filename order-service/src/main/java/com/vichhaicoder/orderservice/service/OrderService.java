package com.vichhaicoder.orderservice.service;

import com.vichhaicoder.orderservice.entity.dto.dtoRequest.OrderRequest;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse saveOrder(OrderRequest orderRequest);

    List<OrderResponse> getList();

    OrderResponse getOrderById(Long id);

    OrderResponse updateOrder(Long id, OrderRequest orderRequest);

    void deleteOrder(Long id);
}
