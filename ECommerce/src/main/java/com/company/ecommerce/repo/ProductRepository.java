package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT u FROM Product u WHERE u.category.id = ?1")
    Collection<Product> findAllActiveUsersNative(Long categoryId);


}