package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    //perproducts and customer

}
