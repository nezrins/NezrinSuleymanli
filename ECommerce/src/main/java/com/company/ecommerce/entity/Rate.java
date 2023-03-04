package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String comment;

    private Double point;//like 3.0 out of 5

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perProductId", referencedColumnName = "id")
    private PerProduct perProduct;
}
