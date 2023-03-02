package com.company.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "size_name")
    private String sizeName;
}
