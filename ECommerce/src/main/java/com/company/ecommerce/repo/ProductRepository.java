package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
            value = "SELECT * FROM Product u WHERE u.category_id = 1",
            nativeQuery = true)
    Collection<Product> findAllActiveUsersNative(Long categoryId);
}
