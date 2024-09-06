package com.vichhaicoder.orderservice.entity.dto.dtoResponse;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
//    public Product toProduct() {
//        Product product = new Product();
//        product.setId(product.getId());
//        product.setName(product.getName());
//        product.setPrice(price);
//        return product;
//    }
}
