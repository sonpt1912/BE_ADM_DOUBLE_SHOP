package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;


@Entity
@Table(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_color")
    private String productColor;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

}
