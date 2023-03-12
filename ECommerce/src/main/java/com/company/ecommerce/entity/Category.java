package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "category_name")
    private String categoryName;

    @JsonManagedReference
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> products;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "category_gender",
            joinColumns = { @JoinColumn(name = "categoryId") },
            inverseJoinColumns = { @JoinColumn(name = "genderId") }
    )
    private List<Gender> genders;

    public List<Gender> getGenders() {
        Hibernate.initialize(genders);
        return genders;
    }

}
