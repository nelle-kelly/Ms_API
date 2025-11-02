package com.commerce.msproduct.services;

import com.commerce.msproduct.dto.ProductReqDto;
import com.commerce.msproduct.dto.ProductResDto;

public interface ProductService {
    ProductResDto getProductById(Long id);
    ProductResDto addProduct(ProductReqDto product);
    ProductResDto updateProduct(ProductReqDto product);
}
