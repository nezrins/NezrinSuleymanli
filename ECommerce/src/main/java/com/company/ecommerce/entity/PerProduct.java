package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Entity
@Data
public class PerProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Double price;

    @JsonManagedReference
    @OneToMany(mappedBy = "perProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Photo> photos;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private Product product;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colorId", referencedColumnName = "id")
    private Color color;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sizeId", referencedColumnName = "id")
    private Size size;

    @JsonManagedReference
    @OneToMany(mappedBy = "perProduct",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Rate> rate;
}
