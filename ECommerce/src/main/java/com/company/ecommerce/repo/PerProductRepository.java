package com.company.ecommerce.repo;

import com.company.ecommerce.entity.PerProduct;
import com.company.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerProductRepository extends JpaRepository<PerProduct, Long> {
    void delete(PerProduct perProduct);
}
