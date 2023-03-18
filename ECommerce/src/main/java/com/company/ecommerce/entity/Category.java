package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "category_name")
    private String categoryName;

    private String iconLink;

    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Product> products;


    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Sub_category> sub_categories;

    @ManyToMany(cascade = { CascadeType.ALL},fetch = FetchType.EAGER)
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
