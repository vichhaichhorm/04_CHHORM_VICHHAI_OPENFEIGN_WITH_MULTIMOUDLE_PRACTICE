package com.vichhaicoder.orderservice.controller;

import com.vichhaicoder.orderservice.entity.Order;
import com.vichhaicoder.orderservice.entity.apiResponse.APIDeResponse;
import com.vichhaicoder.orderservice.entity.apiResponse.APIResponse;
import com.vichhaicoder.orderservice.entity.dto.dtoRequest.OrderRequest;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.OrderResponse;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.ProductResponse;
import com.vichhaicoder.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.saveOrder(orderRequest);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Create order successful .")
                .payload(orderResponse)
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping
    public ResponseEntity<APIResponse<List<OrderResponse>>> getList(){
        List<OrderResponse> orderResponseList = orderService.getList();
        APIResponse<List<OrderResponse>> apiResponse = APIResponse.<List<OrderResponse>>builder()
                .message("Get all order successful .")
                .payload(orderResponseList)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<OrderResponse>> getOrderById(@PathVariable Long id){
        OrderResponse orderResponse = orderService.getOrderById(id);
        APIResponse<OrderResponse> apiResponse = APIResponse.<OrderResponse>builder()
                .message("Get order by id successful .")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<OrderResponse>> updateOrder(
            @PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.updateOrder(id, orderRequest);
        APIResponse<OrderResponse> apiResponse = APIResponse.<OrderResponse>builder()
                .message("Updated order with id "+id+" successful .")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        APIDeResponse apiDeResponse = APIDeResponse.builder()
                .message("Deleted order with id "+id+" successful .")
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiDeResponse);
    }




}
