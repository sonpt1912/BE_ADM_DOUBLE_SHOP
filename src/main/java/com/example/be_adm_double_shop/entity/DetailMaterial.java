package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_material")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_detail_product", referencedColumnName = "id")
    private DetailProduct detailProduct;

    @ManyToOne
    @JoinColumn(name = "id_material", referencedColumnName = "id")
    private Material material;


}
