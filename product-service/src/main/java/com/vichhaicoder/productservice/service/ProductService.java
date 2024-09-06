package com.vichhaicoder.productservice.service;

import com.vichhaicoder.productservice.entity.Product;
import com.vichhaicoder.productservice.entity.dto.dtoRequest.ProductRequest;
import com.vichhaicoder.productservice.entity.dto.dtoResponse.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getList();

    ProductResponse getProductById(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    Product deleteProduct(Long id);
}
