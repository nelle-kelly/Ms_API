package com.commerce.msproduct.web;

import com.commerce.msproduct.dto.ProductReqDto;
import com.commerce.msproduct.dto.ProductResDto;
import com.commerce.msproduct.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResDto getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public ProductResDto addProduct(@RequestBody ProductReqDto productReqDto) {
        return productService.addProduct(productReqDto);
    }

}
