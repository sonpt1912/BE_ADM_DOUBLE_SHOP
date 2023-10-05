package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

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

    /// thieues anh xa

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_color")
    private String productColor;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

}
