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
    @OneToOne(mappedBy = "size", cascade = CascadeType.ALL)
    private Bucket bucket;

    @JsonIgnore
    @OneToMany(mappedBy ="size",fetch =FetchType.EAGER ,cascade = CascadeType.ALL)
    private List<ProductSizes> productSizes;



}
