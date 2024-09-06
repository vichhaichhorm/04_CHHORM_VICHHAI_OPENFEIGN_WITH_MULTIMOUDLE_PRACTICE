package com.vichhaicoder.productservice.controller;

import com.vichhaicoder.productservice.entity.Product;
import com.vichhaicoder.productservice.entity.apiResponse.APIDeResponse;
import com.vichhaicoder.productservice.entity.apiResponse.APIResponse;
import com.vichhaicoder.productservice.entity.dto.dtoRequest.ProductRequest;
import com.vichhaicoder.productservice.entity.dto.dtoResponse.ProductResponse;
import com.vichhaicoder.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        ProductResponse saveProduct = productService.createProduct(productRequest);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Create product successful .")
                .payload(saveProduct)
                .status(HttpStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<?> getList(){
        List<ProductResponse> productResponseList = productService.getList();
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get all product successful .")
                .payload(productResponseList)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductResponse>> getProductById(@PathVariable Long id){
        ProductResponse productResponse = productService.getProductById(id);
        APIResponse<ProductResponse> apiResponse = APIResponse.<ProductResponse>builder()
                .message("Get product with id "+id+"successful .")
                .payload(productResponse)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest ){
        ProductResponse productResponse = productService.updateProduct(id,productRequest);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Updated product with id "+id+" successful .")
                .payload(productResponse)
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        Product product = productService.deleteProduct(id);
        APIDeResponse apiDeResponse = APIDeResponse.builder()
                .message("Deleted product with id "+id+" successful .")
                .status(HttpStatus.OK)
                .dateTime(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiDeResponse);
    }

}
