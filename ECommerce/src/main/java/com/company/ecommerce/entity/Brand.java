package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "brand_id", nullable = false)
    private Long brandId;

    @Column(name = "brand_name")
    private String brandName;


    @JsonIgnore
    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products;

}
