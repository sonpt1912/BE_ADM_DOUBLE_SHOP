package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.request.VoucherRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Collar;
import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.entity.Voucher;
import com.example.be_adm_double_shop.repository.CollarRepository;
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

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getDiscountAmount())) {
            sql.append("and discountamount = :discountamount");
            params.put("discountamount", request.getDiscountAmount());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getQuantity())){
            sql.append("and quantity=:quantity");
            params.put("quantity",request.getQuantity());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getStartDate())) {
            sql.append(" and to_char('start_date','dd/mm/yyyy') = ? ");
            params.put("start_date", request.getStartDate());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEndDate())) {
            sql.append(" and to_char('end_date','dd/mm/yyyy') = ? ");
            params.put("end_date", request.getEndDate());
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

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getDiscountAmount())) {
            sql.append("and discountamount = :discountamount");
            params.put("discountamount", request.getDiscountAmount());
        }
        if(!StringUtil.stringIsNullOrEmty(request.getQuantity())){
            sql.append("and quantity=:quantity");
            params.put("quantity",request.getQuantity());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getStartDate())) {
            sql.append(" and to_char('start_date','dd/mm/yyyy') = ? ");
            params.put("start_date", request.getStartDate());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEndDate())) {
            sql.append(" and to_char('end_date','dd/mm/yyyy') = ? ");
            params.put("end_date", request.getEndDate());
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
        voucher.setStatus(Constant.ACTIVE);

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

            voucher.setDiscountAmount(voucherRequest.getDiscountAmount());
            voucher.setStatus(voucherRequest.getStatus());

            voucher.setUpdatedTime(DateUtil.dateToString4(new Date()));
            voucher.setCreatedBy(voucherRequest.getCreatedBy());
            voucher.setCreatedTime(voucherRequest.getCreatedTime());
            voucher.setQuantity(voucherRequest.getQuantity());
            voucher.setUpdated_by(username);

            repository.save(voucher);
            return Constant.SUCCESS;
        } else {
            return "khong tim thay voucher";
        }
    }

}

