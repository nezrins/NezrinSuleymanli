package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "color_name")
    private String colorName;

    @OneToMany(mappedBy = "color",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PerProduct> perProducts;
}
