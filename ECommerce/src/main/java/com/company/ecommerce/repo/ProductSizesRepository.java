package com.company.ecommerce.repo;

import com.company.ecommerce.entity.PerProduct;
import com.company.ecommerce.entity.ProductSizes;
import com.company.ecommerce.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizesRepository extends JpaRepository<ProductSizes, Long> {
    @Query(value = "SELECT u.numbers FROM ProductSizes u WHERE u.perProduct.id = ?1 AND u.size.id = ?2")
    Integer findProductCountBySizes(Long perProduct, Long size);
}
