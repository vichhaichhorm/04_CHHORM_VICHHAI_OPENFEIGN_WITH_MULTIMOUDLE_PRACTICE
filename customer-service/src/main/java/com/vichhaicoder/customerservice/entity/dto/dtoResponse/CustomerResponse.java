package com.vichhaicoder.customerservice.entity.dto.dtoResponse;

import com.vichhaicoder.customerservice.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;

    public void responseCustomer(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
    }


}
