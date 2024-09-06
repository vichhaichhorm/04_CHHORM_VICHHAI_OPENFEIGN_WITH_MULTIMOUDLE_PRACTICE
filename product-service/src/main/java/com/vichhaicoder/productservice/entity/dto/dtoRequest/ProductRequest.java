package com.vichhaicoder.productservice.entity.dto.dtoRequest;

import com.vichhaicoder.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private Double price;
    public void requestProduct(Product product){
        product.setName(name);
        product.setPrice(price);
    }

}
