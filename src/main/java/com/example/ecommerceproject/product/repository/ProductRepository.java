package com.example.ecommerceproject.product.repository;

import com.example.ecommerceproject.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository를 상속받는 것만으로 기본적인 CRUD 메소드가 자동 생성됩니다.
    // 추후 '카테고리별 상품 조회' 등 필요한 메소드를 여기에 추가할 수 있습니다.
}