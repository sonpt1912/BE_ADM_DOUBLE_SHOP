package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "material")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true, nullable = false, length = 45)
    private String code;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

}