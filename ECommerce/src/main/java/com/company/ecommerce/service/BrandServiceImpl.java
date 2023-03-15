package com.company.ecommerce.service;

import com.company.ecommerce.entity.Brand;
import com.company.ecommerce.entity.Category;
import com.company.ecommerce.repo.BrandRepository;
import com.company.ecommerce.repo.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService{


    @Autowired
    BrandRepository brandRepo;

    @Override
    public Brand createBrand(Brand brand) {
        Brand savedBrand = brandRepo.save(brand);
        return savedBrand;
    }

    @Override
    public Brand updateBrand(Brand brand, Long id) {
        Brand updatedBrand = new Brand();
        Optional<Brand> savedBrand = brandRepo.findById(id);
        updatedBrand = savedBrand.get();
        if(savedBrand.isPresent()){
            BeanUtils.copyProperties(brand, updatedBrand/*, Utils.getNullPropertyName(product)*/);
        }
        updatedBrand=brandRepo.save(updatedBrand);
        return updatedBrand;
    }

    @Override
    public void deleteBrand(Long id) {
        Optional<Brand> brand = brandRepo.findById(id);
        if(brand.isPresent()){
            Brand deletedBrand = brand.get();
            brandRepo.delete(deletedBrand);
        }
    }

    @Override
    public List<Brand> getBrands() {
        List<Brand> brands = brandRepo.findAll();
        return brands;
    }

    @Override
    public Brand getBrandById(Long id) {
        Brand brand = new Brand();
        Optional<Brand> givenBrand = brandRepo.findById(id);
        return givenBrand.orElse(brand);
    }
}
