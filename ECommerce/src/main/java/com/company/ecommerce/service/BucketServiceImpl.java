package com.company.ecommerce.service;

import com.company.ecommerce.entity.*;
import com.company.ecommerce.repo.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    ProductSizesRepository productSizesRepo;

    @Autowired
    PerProductRepository perProductRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BucketRepository bucketRepository;

    @Autowired
    EntityManager em;

    @Override
    @Transactional
    public void addToBucket(Long customerId, Long perProductId, Long sizeId, int count){
        Customer customer = customerRepository.findById(customerId).get();
        PerProduct perProduct = perProductRepository.findById(perProductId).get();
        Size size = sizeRepository.findById(sizeId).get();
        if(productSizesRepo.findProductCountBySizes(perProductId, sizeId) >= count){
            Bucket bucket = Bucket.builder()
                    .customer(customer)
                    .perProduct(perProduct)
                    .size(size)
                    .amountOfProduct(count)
                    .build();
            em.merge(bucket);
        }
    }


    @Override
    public List<Bucket> getBucket(Long customerId) {
        List<Bucket> buckets = bucketRepository.findBucketProductsByCustomerId(customerId);
        return buckets;
    }

    @Override
    public void deleteProductFromBucket(Long customerId, Long perProductId, Long sizeId) {
        Bucket bucketProduct = bucketRepository.findProduct(customerId, perProductId, sizeId);
        Long deletedProductId = bucketProduct.getId();
        bucketRepository.deleteById(deletedProductId);
    }

    @Override
    @Transactional
    public void incrementProductNumber(Long customerId, Long perProductId, Long sizeId){
        Bucket bucketProduct = bucketRepository.findProduct(customerId, perProductId, sizeId);
        if(productSizesRepo.findProductCountBySizes(perProductId, sizeId) > bucketProduct.getAmountOfProduct()) {
            bucketProduct.setAmountOfProduct(bucketProduct.getAmountOfProduct() + 1);
            em.merge(bucketProduct);
        }
    }

    @Override
    @Transactional
    public void decrementProductNumber(Long customerId, Long perProductId, Long sizeId){
        Bucket bucketProduct = bucketRepository.findProduct(customerId, perProductId, sizeId);
        if(bucketProduct.getAmountOfProduct() > 1) {
            bucketProduct.setAmountOfProduct(bucketProduct.getAmountOfProduct() - 1);
            em.merge(bucketProduct);
        }
    }
}
