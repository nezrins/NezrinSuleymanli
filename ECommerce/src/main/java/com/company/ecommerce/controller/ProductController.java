package com.company.ecommerce.controller;

import com.company.ecommerce.entity.Product;
import com.company.ecommerce.service.ProductServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.boot.SessionFactoryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/products",method = RequestMethod.GET)
public class ProductController {
    private final ProductServiceImpl productService;
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/save-product",consumes = "application/json; charset=utf-8")
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
    public ResponseEntity<?> getProducts(@RequestParam(defaultValue = "0") Integer pageNo,
                                         @RequestParam(defaultValue = "10") Integer pageSize){
        List<Product> products = productService.getProducts(pageNo,pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductByCategory(@PathVariable("categoryId") Long category,
                                                  @RequestParam(defaultValue = "0") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Product> product = productService.getProductByCategory(category,pageNo,pageSize);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

//    @GetMapping("/searchByDescription")
//    public ResponseEntity<?> searchByDescription(@RequestParam(value = "search") String search) {
//        if (search == null || search.trim().isEmpty()) {
//            throw new IllegalArgumentException("Search term cannot be empty.");
//        }
//        List<Product> product=productService.getProducts()
//                .stream().filter(c->c.getDescription().toUpperCase().contains(search.toUpperCase())
//               ).collect(Collectors.toList());
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }


//    @PostConstruct
//    public void init(){
//        Gender gender= Gender.builder().name("Qadın").build();
//        Category category= Category.builder().categoryName("Geyim").iconLink("https://cdn-icons-png.flaticon.com/512/3159/3159614.png")
//                .genders(Arrays.asList(gender))
//                .build();
//        Photo photo1=Photo.builder()
//                .link("https://strgimgr.umico.az/sized/840/442798-ef263b1305ab8316541b4a6f85ef6ad3.jpg")
//                .build();
//        Photo photo2=Photo.builder()
//                .link("https://strgimgr.umico.az/sized/1680/442798-7903ecfe1641de1c7b5a25d8ce99827f.jpg")
//                .build();
//        Photo photo3=Photo.builder()
//                .link("https://strgimgr.umico.az/sized/1680/442798-dd1d38f47e23278a06cff3fa4962f0b2.jpg")
//                .build();
//        Photo photo4=Photo.builder()
//                .link("https://strgimgr.umico.az/sized/840/442803-63dfa1da8417f7c026149654e8dd0e3c.jpg")
//                .build();
//        Photo photo5=Photo.builder()
//                .link("https://strgimgr.umico.az/sized/1680/442803-e9dc191b70f26004f73aca93d46bcb5a.jpg")
//                .build();
//        Photo photo6=Photo.builder()
//                .link("https://strgimgr.umico.az/sized/1680/442803-15fdd6a1a50fced69ca3e69c28bcadfd.jpg")
//                .build();
//        Rate rate1=Rate.builder().comment("Rəngi və material əla idi")
//                .point(4.5)
//                .userName("Lalə Əliyeva")
//                .build();
//        Rate rate2=Rate.builder().comment("Mavi rəngini də alıb bəyənmisdim ve yene yanıltmadı")
//                .point(4.7)
//                .userName("Lalə Əliyeva")
//                .build();
//        PerProduct perProduct=PerProduct.builder()
//                .price(24.25)
//                .code("MKIY100029")
//                .photos(Arrays.asList(photo1,photo2,photo3))
//                .color(Color.builder().colorName("mavi").build())
//                .stockNumbers(215)
//                .size(Size.builder().sizeName("L").build())
//                .rate(Arrays.asList(rate1))
//                .build();
//
//        PerProduct perProduct2=PerProduct.builder()
//                .price(24.25)
//                .code("MKIY100030")
//                .photos(Arrays.asList(photo4,photo5,photo6))
//                .color(Color.builder().colorName("ağ").build())
//                .stockNumbers(255)
//                .size(Size.builder().sizeName("M").build())
//                .rate(Arrays.asList(rate2))
//                .build();
//        Product product=Product.builder().productName("Uzunqol Ipekyolu köynək").description("Qadınlar üçün uzunqol mavi köynək")
//                .category(category)
//                .brand(Brand.builder().brandName("IpekYol").iconLink("https://cdn.dsmcdn.com/seller-store/uploads/1138/d3f6965f-8223-41e7-bfe8-e6795ca87908.png")
//                        .build())
//                .products(Arrays.asList(perProduct,perProduct2)).build();
//        productService.createProduct(product);
//    }
}
