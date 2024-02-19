package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false, length = 45)
    private String name;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

}