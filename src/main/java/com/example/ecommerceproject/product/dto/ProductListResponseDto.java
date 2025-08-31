package com.example.ecommerceproject.product.dto;

import com.example.ecommerceproject.product.entity.Product;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ProductListResponseDto {

    private final Long id;
    private final String name;
    private final Long price;
    private final String thumbnailUrl;
    private final String sellerName; // 판매자 이름 추가
    private final LocalDateTime createdAt; // 등록일 추가

    public ProductListResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.sellerName = product.getSeller().getUsername(); // 연관된 Seller(User)의 이름
        this.createdAt = product.getCreatedAt();
        this.thumbnailUrl = product.getProductImages().stream()
                .filter(img -> img.isThumbnail())
                .findFirst()
                .map(img -> img.getImageUrl())
                .orElse(null);
    }
}