package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.PromotionRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Material;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.repository.PromotionRepository;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PromotionSer {
    @Autowired
    private PromotionRepository promotionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ListResponse<Promotion> getAllByCondition(PromotionRequest request) {
        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append("select * from promotion where 1 = 1");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append((" and code like concat('%', :code, '%')"));
            params.put("code", request.getCode());
        }

        if(!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append((" and name like concat('%', :name, '%')"));
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" and status = :status ");
            params.put("status", request.getStatus());
        }

        sql.append(" ORDER BY id DESC");

        if (!StringUtil.stringIsNullOrEmty(request.getPage())) {
            sql.append(" LIMIT  :page, :size ");
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

//
        sql = new StringBuilder();
        params = new HashMap<>();


        sql.append(" select count(*) from promotion where 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append((" and code like concat('%', :code, '%')"));
            params.put("code", request.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append((" and name like concat('%', :name, '%')"));
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" and status = :status ");
            params.put("status", request.getStatus());
        }


        Query queryCount = entityManager.createNativeQuery(sql.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);

        return listResponse;
    }

    public List<Promotion> getAll(){
        return promotionRepository.getAll();
    }

    public Promotion add(Promotion p, String username) {
//        p.setCreatedTime(LocalDateTime.now().toString());
//        p.setCreatedBy("TranTung");
//        p.setStatus(Constant.ACTIVE);
//        return promotionRepository.save(p);
        if (StringUtil.stringIsNullOrEmty(p.getCode())) {
            int i = 1;
            while (true) {
                String codeGen = Constant.DETAIL_PRODUCT.SIZE + i;
                if (StringUtil.stringIsNullOrEmty(promotionRepository.checkCodeExits(codeGen))) {
                    p.setCode(codeGen);
                    break;
                }
                i++;
            }
        }
        p.setStatus(Constant.ACTIVE);
        p.setCreatedBy(username);
        p.setCreatedTime(DateUtil.dateToString4(new Date()));
        try {
            return promotionRepository.save(p);

        } catch (Exception e) {
            e.getMessage();
            return p;
        }
    }

    public Promotion getOneById(long id) {
        return promotionRepository.getOneById(id);
    }

    public Promotion update(Promotion p, String username) {
//        Promotion pro = promotionRepository.getOneById(p.getId());
//        p.setCreatedTime(pro.getCreatedTime());
//        p.setUpdatedTime(LocalDateTime.now().toString());
//        return promotionRepository.save(p);
        if (StringUtil.stringIsNullOrEmty(p.getCode())) {
            int i = 1;
            while (true) {
                String codeGen = Constant.DETAIL_PRODUCT.SIZE + i;
                if (StringUtil.stringIsNullOrEmty(promotionRepository.checkCodeExits(codeGen))) {
                    p.setCode(codeGen);
                    break;
                }
                i++;
            }
        }
        Promotion pro = promotionRepository.getOneById(p.getId());
        p.setCreatedTime(pro.getCreatedTime());
        p.setCreatedBy(pro.getCreatedBy());
        p.setStatus(Constant.ACTIVE);
        p.setUpdatedBy(username);
        p.setUpdatedTime(DateUtil.dateToString4(new Date()));
        try {
            return promotionRepository.save(p);
        } catch (Exception e) {
            e.getMessage();
            return p;
        }
    }

    public Promotion delete(Long id) {
        Promotion promotion = promotionRepository.getOneById(id);
        promotion.setStatus(0);
        return promotionRepository.save(promotion);
    }
}
