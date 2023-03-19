package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductSizes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sizeId", referencedColumnName = "id")
    private Size size;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perProductId", referencedColumnName = "id")
    private PerProduct perProduct;


    private int numbers;


}
