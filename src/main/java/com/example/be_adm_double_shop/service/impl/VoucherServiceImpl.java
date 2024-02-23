package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.VoucherRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Voucher;
import com.example.be_adm_double_shop.repository.VoucherRepository;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.VoucherService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class VoucherServiceImpl implements VoucherService {
    @Autowired
    private VoucherRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public ListResponse<Voucher> getAll(VoucherRequest request) {

        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append(" SELECT * FROM voucher WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append(" AND CODE LIKE CONCAT('%', :code ,'%') ");
            params.put("code", request.getCode());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getName())){
            sql.append(" AND name LIKE CONCAT('%', :name ,'%') ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getDiscountAmount())) {
            sql.append("and discount_amount = :discountAmount");
            params.put("discountAmount", request.getDiscountAmount());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getQuantity())){
            sql.append("and quantity = :quantities ");
            params.put("quantities", request.getQuantity());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getStartDate())) {
            sql.append(" AND start_date = CONCAT('%', :startDate ,'%') ");
            params.put("startDate", request.getStartDate());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEndDate())) {
            sql.append(" AND end_date = CONCAT('%', :endDate ,'%') ");
            params.put("endDate", request.getEndDate());
        }

            if (!StringUtil.stringIsNullOrEmty(request.getPage())) {
                sql.append(" LIMIT  :page, :size  ");
                if (request.getPage() == 0) {
                    params.put("page", 0);
                } else {
                    params.put("page", (request.getPage() * request.getPageSize()));
                }
                params.put("size", request.getPageSize());
            }

            Query query = entityManager.createNativeQuery(sql.toString(), Voucher.class);
            params.forEach(query::setParameter);

            listResponse.setListData(query.getResultList());

            sql = new StringBuilder();
            params = new HashMap<>();

            sql.append(" SELECT COUNT(*) FROM voucher WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append(" AND CODE LIKE CONCAT('%', :code ,'%') ");
            params.put("code", request.getCode());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getName())){
            sql.append(" AND name LIKE CONCAT('%', :name ,'%') ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getDiscountAmount())) {
            sql.append("and discount_amount = :discountAmount");
            params.put("discountAmount", request.getDiscountAmount());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getQuantity())){
            sql.append("and quantity = :quantities ");
            params.put("quantities", request.getQuantity());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getStartDate())) {
            sql.append(" AND start_date = CONCAT('%', :startDate ,'%') ");
            params.put("startDate", request.getStartDate());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEndDate())) {
            sql.append(" AND end_date = CONCAT('%', :endDate ,'%') ");
            params.put("endDate", request.getEndDate());
        }

                Query queryCount = entityManager.createNativeQuery(sql.toString());
                params.forEach(queryCount::setParameter);

                Integer countData = ((Long) queryCount.getSingleResult()).intValue();

                listResponse.setTotalRecord(countData);
                return listResponse;
            }

    @Override
    public Voucher getOneId(Long id) {
        return repository.findById(id).get();
    }


    @Override
    public String save(Voucher voucher, String username) {

        if (StringUtil.stringIsNullOrEmty(voucher.getCode())) {
            int i = 1;
            while (true) {
                String codeGen = "VOUCHER" + i;
                if (StringUtil.stringIsNullOrEmty(repository.checkCodeExits(codeGen))) {
                    voucher.setCode(codeGen);
                    break;
                }
                i++;
            }
        }
        if(StringUtil.stringIsNullOrEmty(voucher.getDiscountPercent())){
            voucher.setDiscountPercent(0);
        }
        if(StringUtil.stringIsNullOrEmty(voucher.getDiscountAmount())){
            voucher.setDiscountAmount(0L);
        }

        voucher.setStatus(Math.toIntExact(Constant.ACTIVE));
        voucher.setCreatedBy(username);
        voucher.setCreatedTime(DateUtil.dateToString4(new Date()));

        try {
            repository.save(voucher);
            return Constant.SUCCESS;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Object update(Voucher voucherRequest, String username) {
        Voucher voucher = repository.getVoucherByCode(voucherRequest.getCode());
        if (!StringUtil.stringIsNullOrEmty(voucher)) {
            if(voucher.getDiscountAmount()>100000){
                return Constant.FAIL;
            }
            if(voucher.getDiscountPercent()>10){
                return Constant.FAIL;
            }
            voucher.setDiscountAmount(voucherRequest.getDiscountAmount());
            voucher.setStatus(voucherRequest.getStatus());
            voucher.setUpdatedTime(DateUtil.dateToString4(new Date()));


            voucher.setQuantity(voucherRequest.getQuantity());


            voucher.setUpdatedBy(username);

            repository.save(voucher);
            return Constant.SUCCESS;
        } else {
            return "khong tim thay voucher";
        }
    }

}

