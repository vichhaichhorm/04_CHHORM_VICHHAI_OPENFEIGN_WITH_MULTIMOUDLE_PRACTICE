package com.vichhaicoder.productservice.service.serviceImp;

import com.vichhaicoder.productservice.entity.Product;
import com.vichhaicoder.productservice.entity.dto.dtoRequest.ProductRequest;
import com.vichhaicoder.productservice.entity.dto.dtoResponse.ProductResponse;
import com.vichhaicoder.productservice.repository.ProductRepository;
import com.vichhaicoder.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        productRequest.requestProduct(product);
        Product savedProduct = productRepository.save(product);
        ProductResponse response = new ProductResponse();
        response.responseProduct(savedProduct);
        return response;
    }

    @Override
    public List<ProductResponse> getList() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponseList = new ArrayList<>();
        for (Product product : products){
            ProductResponse response = new ProductResponse();
            response.responseProduct(product);
            productResponseList.add(response);
        }
        return productResponseList;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (!Objects.isNull(product)){
            ProductResponse productResponse = new ProductResponse();
            productResponse.responseProduct(product);
            return productResponse;
        }
        return null;
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);
        if (!Objects.isNull(product)){
            productRequest.requestProduct(product);
            Product savedProduct = productRepository.save(product);
            ProductResponse response = new ProductResponse();
            response.responseProduct(savedProduct);
            return response;
        }
        return null;
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (!Objects.isNull(product)){
            productRepository.deleteById(id);
        }
        return null;
    }
}
