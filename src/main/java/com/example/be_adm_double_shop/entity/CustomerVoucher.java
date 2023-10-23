package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Table(name = "customer_voucher")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerVoucher {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "usage_date")
    private Date usageDate;

}
