package com.vichhaicoder.orderservice.service.serviceImp;

import com.vichhaicoder.orderservice.client.CustomerClient;
import com.vichhaicoder.orderservice.client.ProductClient;
import com.vichhaicoder.orderservice.entity.Order;
import com.vichhaicoder.orderservice.entity.apiResponse.APIResponse;
import com.vichhaicoder.orderservice.entity.dto.dtoRequest.OrderRequest;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.CustomerResponse;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.OrderResponse;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.ProductResponse;
import com.vichhaicoder.orderservice.repository.OrderRepository;
import com.vichhaicoder.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final CustomerClient customerClient;

    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        Order order = new Order();
        ResponseEntity<APIResponse<CustomerResponse>> customerResponse = customerClient.getCustomerById(orderRequest.getCustomerId());
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Long productId : orderRequest.getProductIds()) {
            ResponseEntity<APIResponse<ProductResponse>> productResponse = productClient.getProductById(productId);
            productResponseList.add(productResponse.getBody().getPayload());

        }
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setOrderDate(orderRequest.getOrderDate());
        orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderId(order.getId());
        orderResponse.setCustomerResponse(customerResponse.getBody().getPayload());
        orderResponse.setProductsResponse(productResponseList);
        orderResponse.setOrderDate(order.getOrderDate());
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getList() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orderList) {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getId());
            response.setOrderDate(order.getOrderDate());

            ResponseEntity<APIResponse<CustomerResponse>> customerResponseEntity = customerClient.getCustomerById(order.getCustomerId());
            response.setCustomerResponse(Objects.requireNonNull(customerResponseEntity.getBody()).getPayload());

            List<ProductResponse> productResponseList = new ArrayList<>();
            for (Long productId : order.getProductIds()) {
                ResponseEntity<APIResponse<ProductResponse>> productResponseEntity = productClient.getProductById(productId);
                productResponseList.add(Objects.requireNonNull(productResponseEntity.getBody()).getPayload());
            }
            response.setProductsResponse(productResponseList);
            orderResponseList.add(response);
        }
        return orderResponseList;
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (!Objects.isNull(order)) {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getId());
            response.setOrderDate(order.getOrderDate());

            ResponseEntity<APIResponse<CustomerResponse>> customerResponseEntity = customerClient.getCustomerById(order.getCustomerId());
            response.setCustomerResponse(customerResponseEntity.getBody().getPayload());

            List<ProductResponse> productResponses = new ArrayList<>();
            for (Long productId : order.getProductIds()){
                ResponseEntity<APIResponse<ProductResponse>> productResponseEntity = productClient.getProductById(productId);
                productResponses.add(productResponseEntity.getBody().getPayload());
            }
            response.setProductsResponse(productResponses);
            return response;
        }
        return null;
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(id).orElse(null);

        if (order != null) {
            order.setCustomerId(orderRequest.getCustomerId());
            order.setProductIds(orderRequest.getProductIds());
            order.setOrderDate(orderRequest.getOrderDate());

            orderRepository.save(order);

            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getId());
            response.setOrderDate(order.getOrderDate());

            ResponseEntity<APIResponse<CustomerResponse>> customerResponseEntity = customerClient.getCustomerById(order.getCustomerId());
            response.setCustomerResponse(customerResponseEntity.getBody().getPayload());

            List<ProductResponse> productResponses = new ArrayList<>();

            for (Long productId : order.getProductIds()) {
                ResponseEntity<APIResponse<ProductResponse>> productResponseEntity = productClient.getProductById(productId);
                productResponses.add(productResponseEntity.getBody().getPayload());
            }

            response.setProductsResponse(productResponses);

            return response;
        }
        throw new RuntimeException("Order not found");
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (!Objects.isNull(order)){
            orderRepository.deleteById(id);
        }
    }


}
