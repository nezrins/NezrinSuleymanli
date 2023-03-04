package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.company.ecommerce.entity.Size;

import java.util.List;

@Entity
@Data
public class PerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double price;

    @OneToMany(mappedBy = "perProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Photo> photos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colorId", referencedColumnName = "id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sizeId", referencedColumnName = "id")
    private Size size;


    @OneToMany(mappedBy = "perProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Rate> rate;
}
