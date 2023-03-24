package com.company.ecommerce.service;

import com.company.ecommerce.entity.*;
import com.company.ecommerce.repo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private PerProductRepository perProductRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private ProductSizesRepository productSizesRepository;
    @Autowired
    private RateRepository rateRepository;


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
    @Transactional
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            Product deletedProduct = product.get();
            List<PerProduct> perProducts = deletedProduct.getProducts();
            for (PerProduct perProduct : perProducts) {
                for (Photo p:perProduct.getPhotos()){
                    photoRepository.deletePhotoById(p.getId());
                }
                for (ProductSizes ps:perProduct.getProductSizes()){
                    productSizesRepository.deleteProductSizesById(ps.getId());
                }
                for (Rate r:perProduct.getRate()){
                    rateRepository.deleteRateById(r.getId());
                }
                perProductRepository.deletePerProductById(perProduct.getId());
            }
            productRepo.deleteProductById(deletedProduct.getId());
        }

    }
    @Override
    public List<Product> getProducts(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepo.findAll(pageRequest);
        return products.getContent();
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
    public List<Product> getProductByCategory(Long id,int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepo.findByCategory(id, pageRequest);
        return products.getContent();
    }


}
