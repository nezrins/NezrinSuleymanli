package com.company.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Data
@Table(name = "sizes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "size_name")
    private String sizeName;

    @JsonIgnore
    @OneToMany(mappedBy = "size",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PerProduct> perProducts;


}
