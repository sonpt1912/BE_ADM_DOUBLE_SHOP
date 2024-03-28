package com.example.be_adm_double_shop.repository;

import com.example.be_adm_double_shop.entity.Bill;
import com.example.be_adm_double_shop.entity.BillHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillHistoryRepository extends JpaRepository<BillHistory, Long> {
}
