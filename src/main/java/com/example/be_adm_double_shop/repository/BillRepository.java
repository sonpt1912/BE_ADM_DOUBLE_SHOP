package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    boolean existsByCode(String code);

}
