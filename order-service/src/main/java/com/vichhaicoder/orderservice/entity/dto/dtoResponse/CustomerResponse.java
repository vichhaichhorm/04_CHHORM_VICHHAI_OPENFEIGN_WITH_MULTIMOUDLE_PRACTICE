package com.vichhaicoder.orderservice.entity.dto.dtoResponse;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
}
