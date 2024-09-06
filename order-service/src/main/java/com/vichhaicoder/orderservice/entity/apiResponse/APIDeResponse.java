package com.vichhaicoder.orderservice.entity.apiResponse;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class APIDeResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime dateTime;
}
