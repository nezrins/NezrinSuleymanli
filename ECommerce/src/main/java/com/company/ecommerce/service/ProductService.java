package com.company.ecommerce.service;

import com.company.ecommerce.entity.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<Product> getProducts();
    Product getProductById(Long id);
    List<Product> getProductByCategory(Long id);
}
