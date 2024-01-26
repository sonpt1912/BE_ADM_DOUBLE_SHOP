package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.repository.CollarRepository;
import com.example.be_adm_double_shop.repository.PromotionRepository;
import com.example.be_adm_double_shop.service.PromotionService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository repository;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ListResponse<Promotion> getAll(SizeRequest request) {
            return null;
    }

    @Override
    public Promotion getOneId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Promotion delete(Long id) {
        Promotion promotion = repository.findById(id).get();
        promotion.setStatus(0);
        return repository.save(promotion);
    }

    @Override
    public String save(Promotion promotion, String username) {
        if (StringUtil.stringIsNullOrEmty(promotion.getName())) {
            int i = 1;
            while (true) {
                String nameGen = Constant.PROMOTION.PROMOTION + i;
                if (StringUtil.stringIsNullOrEmty(repository.checkNameExits(nameGen))) {
                    promotion.setName(nameGen);
                    break;
                }
                i++;
            }
        }
        promotion.setStatus(Math.toIntExact(Constant.ACTIVE));
        promotion.setCreatedBy(username);
        promotion.setCreatedTime(DateUtil.dateToString4(new Date()));
        try {
            repository.save(promotion);
            return Constant.SUCCESS;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Object update(Promotion promotion, String username) {
        Promotion promotion1 = repository.getPromotionByName(promotion.getName());
        if (!StringUtil.stringIsNullOrEmty(promotion1)) {
            promotion1.setUpdatedBy(username);
            promotion1.setUpdatedTime(DateUtil.dateToString4(new Date()));
            promotion1.setStatus(promotion.getStatus());
            promotion1.setUpdatedBy(username);
            repository.save(promotion1);
            return Constant.SUCCESS;
        } else {
            return "khong tim thay promotion";
        }
    }
}
