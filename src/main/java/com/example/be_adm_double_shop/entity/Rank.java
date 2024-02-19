package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_rank")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "description", length = 45)
    private String description;

    @Column(name = "from_value")
    private Long from;

    @Column(name = "to_value", nullable = false)
    private Long to;

    @Column(name = "percent")
    private Integer percent;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

    @Column(name = "updated_by", nullable = false, length = 45)
    private String updatedBy;

    @Column(name = "created_by", length = 45)
    private String createdBy;

}