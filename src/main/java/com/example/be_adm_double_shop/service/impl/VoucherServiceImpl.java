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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        if (!StringUtil.stringIsNullOrEmty(request.getDiscountPercent())) {
            sql.append("and discount_percent = :discountPercent");
            params.put("discountPercent", request.getDiscountPercent());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStartDate())) {
            sql.append(" AND start_date like CONCAT('%', :startDate ,'%') ");
            params.put("startDate", request.getStartDate());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEndDate())) {
            sql.append(" AND end_date like CONCAT('%', :endDate ,'%') ");
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
        if (!StringUtil.stringIsNullOrEmty(request.getDiscountPercent())) {
            sql.append("and discount_percent = :discountPercent");
            params.put("discountPercent", request.getDiscountPercent());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStartDate())) {
            sql.append(" AND start_date like CONCAT('%', :startDate ,'%') ");
            params.put("startDate", request.getStartDate());
        }
        if (!StringUtil.stringIsNullOrEmty(request.getEndDate())) {
            sql.append(" AND end_date like CONCAT('%', :endDate ,'%') ");
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

            while (true) {
                String codeGen = UUID.randomUUID().toString();
                if (StringUtil.stringIsNullOrEmty(repository.checkCodeExits(codeGen))) {
                    voucher.setCode(codeGen);
                    break;
                }

            }
        }
        if(StringUtil.stringIsNullOrEmty(voucher.getDiscountPercent())){
            voucher.setDiscountPercent(0);
        }
        if(StringUtil.stringIsNullOrEmty(voucher.getDiscountAmount())){
            voucher.setDiscountAmount(0L);
        }

        voucher.setQuantity(1);
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
    public Object update(Voucher voucherRequest, String username) throws ParseException {
        Voucher voucher = repository.getVoucherByCode(voucherRequest.getCode());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (!StringUtil.stringIsNullOrEmty(voucher)) {
            if(voucherRequest.getDiscountPercent()==null)voucherRequest.setDiscountPercent(0);
            if(voucherRequest.getDiscountAmount()==null)voucherRequest.setDiscountAmount(0L);
            voucher.setDiscountAmount(voucherRequest.getDiscountAmount());
            voucher.setName(voucherRequest.getName());
            voucher.setUpdatedTime(DateUtil.dateToString4(new Date()));
            voucher.setDiscountPercent(voucherRequest.getDiscountPercent());
            voucher.setMinimumOrder(voucherRequest.getMinimumOrder());
            voucher.setStartDate(voucherRequest.getStartDate());

            voucher.setEndDate(voucherRequest.getEndDate());

            voucher.setQuantity(voucherRequest.getQuantity());
            voucher.setStatus(voucherRequest.getStatus());
            if(LocalDateTime.parse(voucherRequest.getEndDate(), formatter).isAfter(LocalDateTime.now())){
                voucher.setStatus(1);
            }

            voucher.setUpdatedBy(username);

            repository.save(voucher);
            return Constant.SUCCESS;
        } else {
            return "khong tim thay voucher";
        }
    }

    @Override
    public String saveAll(Voucher voucher, String username) {
        List<Voucher> savedVouchers = new ArrayList<>();

        for (int i = 1; i <= voucher.getQuantity(); i++) {
            Voucher newVoucher = new Voucher();
            // Sao chép các thông tin từ voucher gốc

            newVoucher.setName(voucher.getName());
            newVoucher.setDiscountAmount(voucher.getDiscountAmount());
            newVoucher.setDiscountPercent(voucher.getDiscountPercent());
            newVoucher.setMinimumOrder(voucher.getMinimumOrder());
            newVoucher.setStartDate(voucher.getStartDate());
            newVoucher.setEndDate(voucher.getEndDate());
            newVoucher.setStatus(Constant.ACTIVE);
            newVoucher.setCreatedBy(username);
            newVoucher.setCreatedTime(DateUtil.dateToString4(new Date()));
            newVoucher.setQuantity(1);
            if(StringUtil.stringIsNullOrEmty(voucher.getDiscountPercent())){
                newVoucher.setDiscountPercent(0);
            }
            if(StringUtil.stringIsNullOrEmty(voucher.getDiscountAmount())){
                newVoucher.setDiscountAmount(0L);
            }
            // Tạo mã code mới
            String codeGen;
            do {
                codeGen = UUID.randomUUID().toString();
            } while (!StringUtil.stringIsNullOrEmty(repository.checkCodeExits(codeGen)));
            newVoucher.setCode(codeGen);

            savedVouchers.add(repository.save(newVoucher));
        }

        // Trả về ResponseEntity với thông báo hoặc kết quả phù hợp
        return Constant.SUCCESS;

    }

}

