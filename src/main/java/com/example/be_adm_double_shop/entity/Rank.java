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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "since")
    private Long since;

    @Column(name = "until")
    private Long until;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updated_by;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "updated_time")
    private LocalDateTime updatedTime;
}
