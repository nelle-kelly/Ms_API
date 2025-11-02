package com.commerce.msproduct.dto;

import com.commerce.msproduct.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResDto {
    private Long id;
    private String productName;
    private Category category;
    private double price;
}
