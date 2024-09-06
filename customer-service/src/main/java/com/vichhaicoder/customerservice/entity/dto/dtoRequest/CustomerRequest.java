package com.vichhaicoder.customerservice.entity.dto.dtoRequest;

import com.vichhaicoder.customerservice.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String email;

    public void requestCustomer(Customer customer){
        customer.setName(this.name);
        customer.setEmail(this.email);
    }

}
