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
    @JoinColumn(name = "id_color", referencedColumnName = "id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id_size", referencedColumnName = "id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_branch", referencedColumnName = "id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_collar", referencedColumnName = "id")
    private Collar collar;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id")
    private Category category;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

}
