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

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "provice", nullable = false)
    private String provice;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "birth_day", nullable = false)
    private String birthDay;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_time", nullable = false)
    private String createdTime;

    @Column(name = "updated_time")
    private String updatedTime;

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
