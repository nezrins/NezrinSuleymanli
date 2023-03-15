package com.company.ecommerce.service;

import com.company.ecommerce.entity.Category;
import com.company.ecommerce.entity.Product;
import com.company.ecommerce.repo.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepo;

    @Override
    public Category createCategory(Category category) {
        Category savedCategory = categoryRepo.save(category);
        return savedCategory;
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category updatedCategory = new Category();
        Optional<Category> savedCategory = categoryRepo.findById(id);
        updatedCategory = savedCategory.get();
        if(savedCategory.isPresent()){
            BeanUtils.copyProperties(category, updatedCategory/*, Utils.getNullPropertyName(product)*/);
        }
        updatedCategory=categoryRepo.save(updatedCategory);
        return updatedCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> product = categoryRepo.findById(id);
        if(product.isPresent()){
            Category deletedCategory = product.get();
            categoryRepo.delete(deletedCategory);
        }
    }

    @Override
    public List<Category> getCategories() {
        List<Category> products = categoryRepo.findAll();
        return products;
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = new Category();
        Optional<Category> givenCategory = categoryRepo.findById(id);
        return givenCategory.orElse(category);
    }
}
