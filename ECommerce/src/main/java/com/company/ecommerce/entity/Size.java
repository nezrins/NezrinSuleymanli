package com.company.ecommerce.entity;

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

    @OneToMany(mappedBy = "size",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PerProduct> perProducts;


}
