package com.company.ecommerce.service;

import com.company.ecommerce.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<Product> getProducts(int pageNo, int pageSize);
    Product getProductById(Long id);
    List<Product> getProductByCategory(Long id,int pageNo, int pageSize);
}
