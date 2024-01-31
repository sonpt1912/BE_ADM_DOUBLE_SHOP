package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.PromotionRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.repository.PromotionRepository;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.PromotionService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository repository;
    @Autowired
    private JwtProvider jwtProvider;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ListResponse<Promotion> getAll(PromotionRequest request) {
        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append(" SELECT * FROM promotion WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT('%', :name ,'%') ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getStartDate())){
            sql.append(" and to_char('start_date','dd/mm/yyyy') = :sd ");
            params.put("sd",request.getStartDate());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getEndDate())){
            sql.append(" and to_char('end_date','dd/mm/yyyy') = :ed ");
            params.put("ed",request.getEndDate());
        }

        sql.append("order by created_time desc");

        if (!StringUtil.stringIsNullOrEmty(request.getPage())) {
            sql.append(" LIMIT  :page, :size  ");
            if (request.getPage() == 0) {
                params.put("page", 0);
            } else {
                params.put("page", (request.getPage() * request.getPageSize()));
            }
            params.put("size", request.getPageSize());
        }

        Query query = entityManager.createNativeQuery(sql.toString(), Promotion.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());

        sql = new StringBuilder();
        params = new HashMap<>();

        sql.append(" SELECT COUNT(*) FROM promotion WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT('%', :name ,'%') ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getStartDate())){
            sql.append(" and to_char('start_date','dd/mm/yyyy') = :sd ");
            params.put("sd",request.getStartDate());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getEndDate())){
            sql.append(" and to_char('end_date','dd/mm/yyyy') = :ed ");
            params.put("ed",request.getEndDate());
        }

        Query queryCount = entityManager.createNativeQuery(sql.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);
        return listResponse;
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
                String nameGen = "PROMOTION" + i;
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
