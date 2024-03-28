package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.BillHistory;
import com.example.be_adm_double_shop.repository.BillHistoryRepository;
import com.example.be_adm_double_shop.service.BillHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillHistoryServiceImpl implements BillHistoryService {

    @Autowired
    private BillHistoryRepository billHistoryRepository;

    @Override
    public Object createBillHistory(BillHistory billHistory) {
        return billHistoryRepository.save(billHistory);
    }
}
