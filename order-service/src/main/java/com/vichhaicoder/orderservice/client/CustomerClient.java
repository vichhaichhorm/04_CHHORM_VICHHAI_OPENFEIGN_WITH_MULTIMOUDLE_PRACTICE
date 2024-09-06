package com.vichhaicoder.orderservice.client;

import com.vichhaicoder.orderservice.entity.apiResponse.APIResponse;
import com.vichhaicoder.orderservice.entity.dto.dtoResponse.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8081/api/v1/customers")
public interface CustomerClient {

    @GetMapping("/{id}")
    ResponseEntity<APIResponse<CustomerResponse>> getCustomerById(@PathVariable Long id);
}
