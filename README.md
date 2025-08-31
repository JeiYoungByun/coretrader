# ecommerce site project by spring boot

## erd diagram

```mermaid
erDiagram
    USERS ||--o{ PRODUCTS : "sells"
    USERS ||--|{ ORDERS : "places"
    USERS ||--o{ LIKES : "likes"
    PRODUCTS ||--o{ LIKES : "is liked by"
    PRODUCTS ||--|{ ORDER_ITEMS : "are part of"
    PRODUCTS ||--|{ PRODUCT_IMAGES : "has"
    CATEGORIES {
        int id PK
        varchar name UK
    }
    PRODUCTS }o--|| CATEGORIES : "belongs to"
    ORDERS ||--|{ ORDER_ITEMS : "contains"


    USERS {
        bigint id PK
        varchar username UK
        varchar password
        varchar email UK
        varchar name
        varchar role
    }

    CATEGORIES {
        int id PK
        varchar name UK
    }

    PRODUCTS {
        bigint id PK
        bigint seller_id FK
        int category_id FK
        varchar name
        bigint price
        text description
        int stock
        datetime created_at
        datetime updated_at
    }

    PRODUCT_IMAGES {
        bigint id PK
        bigint product_id FK
        varchar image_url
        boolean is_thumbnail
    }

    ORDERS {
        bigint id PK
        bigint user_id FK
        varchar status
        bigint total_price
        datetime order_date
    }

    ORDER_ITEMS {
        bigint id PK
        bigint order_id FK
        bigint product_id FK
        int quantity
        bigint price_per_item
    }

    LIKES {
        bigint user_id FK
        bigint product_id FK
    }
```
