package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "promotion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Promotion {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Integer value;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "created_by")
    private String createdBy = "TranTung";

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

    @Column(name = "status")
    private String status;

}
