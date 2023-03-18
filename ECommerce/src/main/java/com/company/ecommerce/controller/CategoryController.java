package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Category;
import com.company.ecommerce.entity.Gender;
import com.company.ecommerce.repo.CategoryRepository;
import com.company.ecommerce.repo.GenderRepository;
import com.company.ecommerce.service.CategoryServiceImpl;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/categories",method = RequestMethod.GET)
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    private final GenderRepository genderRepository;

    private final CategoryRepository categoryRepository;


    private final EntityManager em;

    public CategoryController(CategoryServiceImpl categoryService, GenderRepository genderRepository, CategoryRepository categoryRepository, EntityManager em) {
        this.categoryService = categoryService;
        this.genderRepository = genderRepository;
        this.categoryRepository = categoryRepository;
        this.em = em;
    }

    /*
     List<Gender> existingGenders = new ArrayList<>();

    for (Gender gender : category.getGenders()) {
        Gender existingGender = genderRepository.findByName(gender.getName());

        if (existingGender == null) {
            // If the Gender object does not exist, create a new one
            existingGender = genderRepository.save(gender);
        }

        existingGenders.add(existingGender);
    }

    category.setGenders(existingGenders);

    Category savedCategory = categoryRepository.save(category);

    return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId()))
        .body(savedCategory);
}
     */
    @PostMapping(path = "/save-category")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        List<Gender> existingGenders = new ArrayList<>();
        for (Gender gender : category.getGenders()) {
            Gender existingGender = genderRepository.findByName(gender.getName());
            if (existingGender == null) {
                existingGender = em.merge(gender);
            }
            existingGenders.add(existingGender);
        }
        category.setGenders(existingGenders);
        Category userResponse = em.merge(category);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable("id") Long id){
        Category userResponse = categoryService.updateCategory(category,id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getCategories(){
        List<Category> categories = categoryService.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
