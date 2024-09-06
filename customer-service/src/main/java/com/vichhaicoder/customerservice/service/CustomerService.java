package com.vichhaicoder.customerservice.service;

import com.vichhaicoder.customerservice.entity.Customer;
import com.vichhaicoder.customerservice.entity.dto.dtoRequest.CustomerRequest;
import com.vichhaicoder.customerservice.entity.dto.dtoResponse.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse saveCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> list();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);

    Customer deleteCustomer(Long id);
}
