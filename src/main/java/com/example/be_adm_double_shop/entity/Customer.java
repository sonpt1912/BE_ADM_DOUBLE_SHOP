package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.mapper.Mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_rank", referencedColumnName = "id")
    private Rank rank;

    @Column(name = "username", length = 45)
    private String username;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "phone", length = 45)
    private String phone;

    @Column(name = "birth_day", length = 45)
    private String birthDay;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "password", length = 145)
    private String password;

    @Column(name = "status")
    private Long status;

    @Column(name = "created_by", nullable = false, length = 45)
    private String createdBy;

    @Column(name = "updated_by", length = 45)
    private String updatedBy;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> address;

}

