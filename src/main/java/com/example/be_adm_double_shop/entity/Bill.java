package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_voucher", referencedColumnName = "id")
    private Voucher voucher;

    @Column(name = "code", unique = true, nullable = false, length = 45)
    private String code;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "order_date", nullable = false, length = 45)
    private String orderDate;

    @Column(name = "discount_amount")
    private Long discountAmount;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Column(name = "confirm_date", nullable = false, length = 45)
    private String confirmDate;

    @Column(name = "ship_date", length = 45)
    private String shipDate;

    @Column(name = "receive_date", length = 45)
    private String receiveDate;

    @Column(name = "note", length = 45)
    private String note;

    @Column(name = "payment", length = 45)
    private String payment;

    @Column(name = "money_ship")
    private Long moneyShip;

    @Column(name = "status", nullable = false)
    private Integer status;


}
