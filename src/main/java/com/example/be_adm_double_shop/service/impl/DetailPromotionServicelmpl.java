package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.DetailPromotionRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.entity.DetailPromotion;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.repository.DetailProductRepository;
import com.example.be_adm_double_shop.repository.DetailPromotionRepository;
import com.example.be_adm_double_shop.repository.PromotionRepository;
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
public class DetailPromotionServicelmpl {
    @Autowired
    private DetailPromotionRepository detailPromotionRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private DetailProductRepository detailProductRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ListResponse<DetailPromotion> getAllByCondition(DetailPromotionRequest request) {
        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append("select * from detail_promotion where 1 = 1");

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


        sql.append(" select count(*) from detail_promotion where 1 = 1 ");

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

    public List<DetailPromotion> getAll(){
        return detailPromotionRepository.findAll();
    }

    public DetailPromotion add(DetailPromotion p, String username) {
        p.getPromotion().setStatus(Constant.ACTIVE);
        p.getPromotion().setCreatedBy(username);
        p.getPromotion().setCreatedTime(DateUtil.dateToString4(new Date()));
        try {
            return detailPromotionRepository.save(p);

        } catch (Exception e) {
            e.getMessage();
            return p;
        }
    }

    public DetailPromotion getOneById(long id) {
        return detailPromotionRepository.findById(id).get();
    }

    public DetailPromotion update(DetailPromotion p, String username) {
        p.getPromotion().setUpdatedBy(username);
        p.getPromotion().setUpdatedTime(DateUtil.dateToString4(new Date()));
        try {
            return detailPromotionRepository.save(p);
        } catch (Exception e) {
            e.getMessage();
            return p;
        }
    }

    public DetailPromotion delete(Long idDetailPromotion) {
        DetailPromotion detailPromotion = detailPromotionRepository.findById(idDetailPromotion).get();
        detailPromotion.getPromotion().setStatus(0);
        return detailPromotionRepository.save(detailPromotion);
    }

}
