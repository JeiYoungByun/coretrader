package com.example.ecommerceproject.product.service;

import com.example.ecommerceproject.product.dto.ProductListResponseDto;
import java.util.List;

public interface ProductService {
    /**
     * 모든 상품 목록을 조회하여 DTO 리스트로 반환합니다.
     * @return 상품 목록 DTO 리스트
     */
    List<ProductListResponseDto> findAllProducts();
}