package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.DetailPromotionRequest;
import com.example.be_adm_double_shop.dto.request.PromotionRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.DetailPromotion;
import com.example.be_adm_double_shop.entity.Promotion;
import com.example.be_adm_double_shop.repository.DetailProductRepository;
import com.example.be_adm_double_shop.repository.DetailPromotionRepository;
import com.example.be_adm_double_shop.repository.PromotionRepository;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

        sql.append("select detail_promotion.id, promotion.code, promotion.name, promotion.discount_amount, " +
                "promotion.discount_percent, promotion.start_date, promotion.end_date, promotion.status " +
                "from detail_promotion join promotion on detail_promotion.id_promotion = promotion.id " +
                "join detail_product on detail_promotion.id_detail_product = detail_product.id where 1 = 1");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append((" and promotion.code like concat('%', :code, '%')"));
            params.put("code", request.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append((" and promotion.name like concat('%', :name, '%')"));
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" and promotion.status = :status ");
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
            sql.append((" and promotion.code like concat('%', :code, '%')"));
            params.put("code", request.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append((" and promotion.name like concat('%', :name, '%')"));
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" and promotion.status = :status ");
            params.put("status", request.getStatus());
        }


        Query queryCount = entityManager.createNativeQuery(sql.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);

        return listResponse;
    }

    public List<DetailPromotion> getAll() {
        return detailPromotionRepository.findAll();
    }

//    public List<DetailPromotion> add(PromotionRequest p, String username) {
//        List<DetailPromotion> detailPromotions = new ArrayList<>();
//            p.getDetailProduct().stream().map(i -> detailPromotionRepository.save(DetailPromotion.builder()
//                    .detailProduct(detailProductRepository.findById(21L).get())
//                    .promotion(promotionRepository.save(Promotion.builder()
//                            .name(p.getName())
//                            .code(p.getCode())
//                            .discountAmount(p.getDiscountAmount())
//                            .discountPercent(p.getDiscountPercent())
//                            .startDate(p.getStartDate())
//                            .endDate(p.getEndDate())
//                            .status(p.getStatus())
//                            .createdBy(username)
//                            .createdTime(DateUtil.dateToString4(new Date()))
//                            .build())).build()
////            )).toList();
//        return detailPromotions;
//    }

    public DetailPromotion add(PromotionRequest p, String username) {

        if (DateUtil.stringToDate(p.getStartDate(), "yyyy-MM-dd").after(new Date()))
            p.setStatus(2);
        else if (DateUtil.stringToDate(p.getStartDate(), "yyyy-MM-dd").before(new Date()))
            p.setStatus(1);
        else if (DateUtil.stringToDate(p.getEndDate(), "yyyy-MM-dd").before(new Date()))
            p.setStatus(0);
        return detailPromotionRepository.save(DetailPromotion.builder()
                .detailProduct(detailProductRepository.findById(21L).get())
                .promotion(promotionRepository.save(Promotion.builder()
                        .name(p.getName())
                        .code(p.getCode())
                        .discountAmount(p.getDiscountAmount())
                        .discountPercent(p.getDiscountPercent())
                        .startDate(p.getStartDate())
                        .endDate(p.getEndDate())
                        .status(p.getStatus())
                        .createdBy(username)
                        .createdTime(DateUtil.dateToString4(new Date()))
                        .build()))
                .build());
//            )).toList();

    }

//    public DetailPromotion add(Promotion pp, DetailPromotion p, String username) {
//        pp.setUpdatedBy(username);
//        p.setDetailProduct(detailProductRepository.findById(21L).orElse(null));
//        p.setPromotion(promotionRepository.save(pp));
//        return detailPromotionRepository.save(p);
//    }

    public DetailPromotion getOneById(long id) {
        return detailPromotionRepository.findById(id).get();
    }

    public DetailPromotion update(PromotionRequest p, String username) {
        return detailPromotionRepository.save(DetailPromotion.builder()
                .detailProduct(detailProductRepository.findById(21L).get())
                .promotion(promotionRepository.save(Promotion.builder()
                        .name(p.getName())
                        .code(p.getCode())
                        .discountAmount(p.getDiscountAmount())
                        .discountPercent(p.getDiscountPercent())
                        .startDate(p.getStartDate())
                        .endDate(p.getEndDate())
                        .status(p.getStatus())
                        .updatedBy(username)
                        .updatedTime(DateUtil.dateToString4(new Date()))
                        .build()))
                .build());
    }

    public DetailPromotion delete(Long idDetailPromotion) {
        DetailPromotion detailPromotion = detailPromotionRepository.findById(idDetailPromotion).get();
        detailPromotion.getPromotion().setStatus(0);
        return detailPromotionRepository.save(detailPromotion);
    }

}
