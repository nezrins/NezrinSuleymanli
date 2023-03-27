package com.company.ecommerce.service;

import com.company.ecommerce.entity.*;
import com.company.ecommerce.repo.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    ProductSizesRepository productSizesRepo;
    @Autowired
    OrderRepository orderRepository;

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
    @Transactional
    public void deleteProductFromBucket(Long customerId, Long perProductId, Long sizeId) {
        Bucket bucketProduct = bucketRepository.findProduct(customerId, perProductId, sizeId);
        bucketRepository.deleteBucketById(bucketProduct.getId());
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

    @Override
    @Transactional
    public void purchaseProductsInBucket(Long customerId){
        List<Bucket> bucketProducts = bucketRepository.findBucketProductsByCustomerId(customerId);
        for(Bucket b : bucketProducts){
            bucketRepository.deleteById(b.getId());
        }
        Customer customer =  customerRepository.findById(customerId).get();
        Order order = Order.builder()
                .customer(customer)
                .date(LocalDate.now())
                .status(OrderStatus.ACCEPTED)
                .build();
        em.merge(order);
    }
}
