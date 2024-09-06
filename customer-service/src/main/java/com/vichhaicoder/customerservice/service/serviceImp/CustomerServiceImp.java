package com.vichhaicoder.customerservice.service.serviceImp;

import com.vichhaicoder.customerservice.entity.Customer;
import com.vichhaicoder.customerservice.entity.dto.dtoRequest.CustomerRequest;
import com.vichhaicoder.customerservice.entity.dto.dtoResponse.CustomerResponse;
import com.vichhaicoder.customerservice.repository.CustomerRepository;
import com.vichhaicoder.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;
    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customerRequest.requestCustomer(customer);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerResponse response = new CustomerResponse();
        response.responseCustomer(savedCustomer);
        return response;
    }

    @Override
    public List<CustomerResponse> list() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> response = new ArrayList<>();
        for (Customer customer : customers){
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.responseCustomer(customer);
            response.add(customerResponse);
        }
        return response;
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (!Objects.isNull(customer)){
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.responseCustomer(customer);
            return customerResponse;
        }
        return null;
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (!Objects.isNull(customer)){
            customerRequest.requestCustomer(customer);
            Customer saveCustomer = customerRepository.save(customer);
            CustomerResponse response = new CustomerResponse();
            response.responseCustomer(saveCustomer);
            return response;
        }
        return null;
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (!Objects.isNull(customer)){
            customerRepository.deleteById(id);
        }
        return null;
    }
}
