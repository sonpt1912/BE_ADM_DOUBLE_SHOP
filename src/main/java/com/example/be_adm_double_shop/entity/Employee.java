package com.example.be_adm_double_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "phone", length = 45)
    private String phone;


    @Column(name = "email", length = 45)
    private String email;


    @Column(name = "gender")
    private Integer gender;

    @Column(name = "birth_day", nullable = false, length = 45)
    private String birthDay;

    @Column(name = "role", nullable = false)
    private Integer role;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "district", length = 45)
    private String district;

    @Column(name = "provice", length = 45)
    private String provice;

    @Column(name = "city", length = 45)
    private String city;

    @Column(name = "description")
    private String description;
    @Column(name = "password", length = 145, nullable = false)
    private String password;

    @Column(name = "created_time", nullable = false, length = 45)
    private String createdTime;

    @Column(name = "updated_time", length = 45)
    private String updatedTime;

    @Column(name = "updated_by", nullable = false, length = 45)
    private String updatedBy;

    @Column(name = "created_by", length = 45)
    private String createdBy;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
