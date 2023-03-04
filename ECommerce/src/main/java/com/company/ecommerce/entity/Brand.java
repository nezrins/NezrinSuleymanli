package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "brand_name")
    private String brandName;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products;

}
