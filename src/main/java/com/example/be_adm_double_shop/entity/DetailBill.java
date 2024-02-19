package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detail_bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_bill", referencedColumnName = "id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_detail_product", referencedColumnName = "id")
    private DetailProduct detailProduct;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "status", nullable = false)
    private Integer status;
}
