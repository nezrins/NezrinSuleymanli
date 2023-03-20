package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
    @Query(value = "SELECT u FROM Bucket u WHERE u.customer.id = ?1")
    List<Bucket> findBucketProductsByCustomerId(Long customerId);

    @Query(value = "SELECT u FROM Bucket u WHERE u.customer.id = ?1 AND u.perProduct.id = ?2 AND u.size.id = ?3")
    Bucket findProduct(Long customerId, Long perProductId, Long sizeId);

}
