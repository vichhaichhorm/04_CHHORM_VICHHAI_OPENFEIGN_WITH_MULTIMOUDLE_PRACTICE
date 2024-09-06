package com.vichhaicoder.productservice.entity.apiResponse;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@Builder
public class APIResponse <T>{
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime dateTime;
}
