package com.example.ecommerceproject.product.api;

import com.example.ecommerceproject.product.dto.ProductListResponseDto;
import com.example.ecommerceproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    /**
     * 전체 상품 목록을 조회하는 API
     * @return 상품 목록 DTO 리스트를 포함한 ResponseEntity
     */
    @GetMapping("/api/products")
    public ResponseEntity<List<ProductListResponseDto>> getAllProducts() {
        log.info("API 요청: 전체 상품 목록 조회");

        // 1. 서비스를 호출하여 모든 상품 DTO 목록을 가져옵니다.
        List<ProductListResponseDto> productList = productService.findAllProducts();

        // 2. HTTP 200 OK 상태와 함께 DTO 목록을 응답 본문에 담아 반환합니다.
        return ResponseEntity.ok(productList);
    }
}