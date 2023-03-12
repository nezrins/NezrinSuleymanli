package com.company.ecommerce.service;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.repo.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;
    public Product createProduct(Product product){
        Product savedProduct = productRepo.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product product, Long id) {
        Product updatedProduct = new Product();
        Optional<Product> savedProduct = productRepo.findById(id);
        updatedProduct = savedProduct.get();
        if(savedProduct.isPresent()){
            BeanUtils.copyProperties(product, updatedProduct/*, Utils.getNullPropertyName(product)*/);
        }
        updatedProduct=productRepo.save(updatedProduct);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()){
            Product deletedProduct = product.get();
            productRepo.delete(deletedProduct);
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public  Product getProductById(Long id) {
        Product product = new Product();
        Optional<Product> givenProduct = productRepo.findById(id);
        if(givenProduct.isPresent())
            return givenProduct.get();
        return product;
    }

    @Override
    public List<Product> getProductByCategory(Long id) {
        List<Product> products = (List<Product>) productRepo.findAllActiveUsersNative(id);
        return products;
    }


}
