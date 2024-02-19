package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "color")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Color {

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

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

}