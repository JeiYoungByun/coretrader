package com.example.ecommerceproject.product.service.impl;

import com.example.ecommerceproject.product.dto.ProductListResponseDto;
import com.example.ecommerceproject.product.entity.Product;
import com.example.ecommerceproject.product.repository.ProductRepository;
import com.example.ecommerceproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true) // 데이터를 읽기만 하므로 readOnly = true 설정
    public List<ProductListResponseDto> findAllProducts() {
        // 1. Repository를 통해 모든 Product 엔티티를 DB에서 조회합니다.
        List<Product> productList = productRepository.findAll();

        // 2. 조회된 엔티티 리스트를 DTO 리스트로 변환합니다.
        //    Java Stream API를 사용하여 간결하게 처리합니다.
        List<ProductListResponseDto> dtoList = productList.stream()
                .map(product -> new ProductListResponseDto(product)) // 각 Product를 ProductListResponseDto로 변환
                .collect(Collectors.toList()); // 결과를 리스트로 수집

        // 3. 변환된 DTO 리스트를 반환합니다.
        return dtoList;
    }
}