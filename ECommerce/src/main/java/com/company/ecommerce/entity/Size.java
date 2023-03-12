package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@Data
@Table(name = "sizes")
public class Size {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "size_name")
    private String sizeName;

    @JsonManagedReference
    @OneToMany(mappedBy = "size",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PerProduct> perProducts;


}
