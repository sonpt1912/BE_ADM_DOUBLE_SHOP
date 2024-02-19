package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;

    @Column(name = "district", length = 45, nullable = false)
    private String district;

    @Column(name = "province", length = 45, nullable = false)
    private String province;

    @Column(name = "city", length = 45, nullable = false)
    private String city;

    @Column(name = "created_by", length = 45, nullable = false)
    private String createdBy;

    @Column(name = "created_time", length = 45, nullable = false)
    private String createdTime;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;


}