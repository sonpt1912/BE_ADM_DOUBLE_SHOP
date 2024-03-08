package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_promotion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DetailPromotion {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_detail_product", referencedColumnName = "id")
    private DetailProduct detailProduct;

    @ManyToOne
    @JoinColumn(name = "id_promotion", referencedColumnName = "id")
    private Promotion promotion;
}
