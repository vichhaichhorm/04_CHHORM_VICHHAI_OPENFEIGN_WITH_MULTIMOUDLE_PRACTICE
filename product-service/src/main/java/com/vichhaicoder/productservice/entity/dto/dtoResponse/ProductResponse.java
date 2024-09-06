package com.vichhaicoder.productservice.entity.dto.dtoResponse;

import com.vichhaicoder.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    public void responseProduct(Product product){
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }

}
