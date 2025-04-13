package com.erp.repository.inventory;

import com.erp.model.inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsBySku(String sku);
}
