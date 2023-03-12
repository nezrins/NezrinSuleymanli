package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Category;
import com.company.ecommerce.entity.Product;
import com.company.ecommerce.service.ProductService;
import com.company.ecommerce.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/products",method = RequestMethod.GET)
public class ProductController {
    private final ProductServiceImpl productService;
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping("/save-product")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        Product userResponse = productService.createProduct(product);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-product/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable("id") Long id){
        Product userResponse = productService.updateProduct(product,id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getProducts(){
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductByCategory(@PathVariable("categoryId") Long category) {
        List<Product> product = productService.getProductByCategory(category);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/searchByDescription")
    public ResponseEntity<?> searchByDescription(@RequestParam(value = "search") String search) {
        if (search == null || search.trim().isEmpty()) {
            throw new IllegalArgumentException("Search term cannot be empty.");
        }
        List<Product> product=productService.getProducts()
                .stream().filter(c->c.getDescription().toUpperCase().contains(search.toUpperCase())
               ).collect(Collectors.toList());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

}
