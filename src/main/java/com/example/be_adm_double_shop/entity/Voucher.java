package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "voucher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Voucher {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "id_promotion")
//    private Promotion promotion;

    @Column(name = "code")
    private String code;

//    @Column(name = "name")
//    private String name;

    @Column(name = "discountAmount")
    private Long discountAmount;

    @Column(name = "discountPercent")
    private Integer discountPercent;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "status")
    private Long status;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updated_by;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

}
