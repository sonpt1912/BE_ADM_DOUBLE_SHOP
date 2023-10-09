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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer stats;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updated_by;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;
}
