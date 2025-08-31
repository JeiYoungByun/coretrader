package com.example.ecommerceproject.product.entity;

import com.example.ecommerceproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계: 여러 Product가 하나의 User에 속함
    @JoinColumn(name = "seller_id", nullable = false) // 외래 키(FK) 컬럼 지정
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY) // 다대일 관계: 여러 Product가 하나의 Category에 속함
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // 일대다 관계
    private List<ProductImage> productImages;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Lob // TEXT 타입과 매핑
    private String description;

    @Column(nullable = false)
    private Integer stock;

    @CreationTimestamp // 엔티티 생성 시 자동으로 현재 시간 저장
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // 엔티티 수정 시 자동으로 현재 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
