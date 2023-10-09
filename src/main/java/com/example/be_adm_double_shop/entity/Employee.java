package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "district")
    private String district;

    @Column(name = "provice")
    private String provice;

    @Column(name = "city")
    private String city;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "birth_day")
    private Date birtDay;

    @Column(name = "role")
    private String role;


    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updated_by;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;


}
