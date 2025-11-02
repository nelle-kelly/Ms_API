package com.commerce.msproduct.services;

import com.commerce.msproduct.dto.ProductReqDto;
import com.commerce.msproduct.dto.ProductResDto;
import com.commerce.msproduct.entities.Product;
import com.commerce.msproduct.mappers.ProductMapper;
import com.commerce.msproduct.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    final ProductRepo productRepo;
    final ProductMapper mapper;

    public ProductServiceImp(ProductRepo productRepo, ProductMapper mapper) {
        this.productRepo = productRepo;
        this.mapper = mapper;
    }

    @Override
    public ProductResDto getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return mapper.todto(product);
        }
        else{
            throw new EntityNotFoundException("Product not found");
        }
    }

    @Override
    public ProductResDto addProduct(ProductReqDto product) {
        Product productEntity = mapper.toEntity(product);
        Product addedProduct = productRepo.save(productEntity);
        return mapper.todto(addedProduct);
    }

    @Override
    public ProductResDto updateProduct(ProductReqDto product) {
        return null;
    }
}
