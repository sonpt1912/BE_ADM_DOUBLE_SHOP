package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_voucher")
    private Voucher voucher;

    @Column(name = "code")
    private String code;

    @Column(name = "phone")
    private String phone;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "discount_amout")
    private BigDecimal discountAmout;

    @Column(name = "total_amout")
    private BigDecimal totalAmout;

    @Column(name = "confirmDate")
    private Date confirmDate;

    @Column(name = "ship_date")
    private Date shipDate;

    @Column(name = "receive_date")
    private Date recevieDate;

    @Column(name = "note")
    private String note;

    @Column(name = "money_ship")
    private BigDecimal moneyShip;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;


}
