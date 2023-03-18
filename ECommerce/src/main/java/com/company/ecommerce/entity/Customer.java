package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String email;

    private String number;

    private String password;

    private String cardNo;

   @OneToOne(mappedBy = "customer")
   @JsonIgnore
   private Bucket bucket;


}
