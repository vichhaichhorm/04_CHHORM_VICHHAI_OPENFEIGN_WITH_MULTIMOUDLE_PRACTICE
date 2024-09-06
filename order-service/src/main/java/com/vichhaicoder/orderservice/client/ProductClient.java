package com.vichhaicoder.orderservice.client;

import com.vichhaicoder.orderservice.entity.apiResponse.APIResponse;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8082/api/v1/products")
public interface ProductClient {
    @GetMapping("/{id}")
    ResponseEntity<APIResponse<ProductResponse>> getProductById(@PathVariable Long id);
}
