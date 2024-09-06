package com.vichhaicoder.customerservice.controller;

import com.vichhaicoder.customerservice.entity.Customer;
import com.vichhaicoder.customerservice.entity.apiResponse.APIDeResponse;
import com.vichhaicoder.customerservice.entity.apiResponse.APIResponse;
import com.vichhaicoder.customerservice.entity.dto.dtoRequest.CustomerRequest;
import com.vichhaicoder.customerservice.entity.dto.dtoResponse.CustomerResponse;
import com.vichhaicoder.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse createCustomer = customerService.saveCustomer(customerRequest);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Created Customer by Successful .")
                .payload(createCustomer)
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> list(){
        List<CustomerResponse> getList = customerService.list();
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get all customer successful .")
                .payload(getList)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CustomerResponse>> getCustomerById(@PathVariable Long id){
        CustomerResponse getById = customerService.getCustomerById(id);
        APIResponse<CustomerResponse> apiResponse = APIResponse.<CustomerResponse>builder()
                .message("Get customer by id "+id+" successful .")
                .payload(getById)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id,@RequestBody CustomerRequest customerRequest){
        CustomerResponse updated = customerService.updateCustomer(id,customerRequest);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Updated Customer with id "+id+" successful .")
                .payload(updated)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        Customer customer = customerService.deleteCustomer(id);
        APIDeResponse apiDeResponse = APIDeResponse.builder()
                .message("Deleted customer with id "+id+" successful .")
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiDeResponse);
    }



}
