package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Timestamp;
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status = 1;

    @Column(name = "created_by")
    private String createdBy = "null";

    @Column(name = "updated_by")
    private String updated_by ;

    @Column(name = "created_time")
    private String createdTime = "null";

    @Column(name = "updated_time")
    private String updatedTime;

}
