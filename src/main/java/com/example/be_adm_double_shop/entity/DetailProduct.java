package com.example.be_adm_double_shop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "detail_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetailProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_collar")
    private Collar collar;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

}
