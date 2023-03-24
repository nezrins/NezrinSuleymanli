package com.company.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

//    private Long productNumber;

//    @ManyToMany(mappedBy = "buckets",cascade = CascadeType.ALL)
//    private List<PerProduct> perProduct;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "perProduct_id", referencedColumnName = "id")
    private PerProduct perProduct;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    private Size size;

    private int amountOfProduct;

}
