package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Brand;
import com.company.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
}