package com.erp.model.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 50)
    private String sku;

    @Column(nullable = false,length = 100)
    private String name;

    private String description;

    @Column(name = "unit_price",precision = 15,scale = 2,nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "cost_price",precision = 15,scale = 2,nullable = false)
    private BigDecimal costPrice;

    @Column(name = "tax_rate",precision = 5,scale = 2)
    private BigDecimal taxRate = BigDecimal.ZERO;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
