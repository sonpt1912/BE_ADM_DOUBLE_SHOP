package com.example.be_adm_double_shop.service.impl;


import com.example.be_adm_double_shop.dto.ListResponse;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.repository.SizeRepository;
import com.example.be_adm_double_shop.service.SizeService;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ListResponse<Size> getAllbByConditon(SizeRequest request) {


        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append(" SELECT * FROM size WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append(" AND CODE LIKE CONCAT(%, :code ,%) ");
            params.put("code", request.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT(%, :name ,%) ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
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


        Query query = entityManager.createNativeQuery(sql.toString(), Size.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());

//
        sql = new StringBuilder();
        params = new HashMap<>();


        sql.append(" SELECT COUNT(*) FROM size WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append(" AND CODE LIKE CONCAT(%, :code ,%) ");
            params.put("code", request.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT(%, :name ,%) ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
        }


        Query queryCount = entityManager.createNativeQuery(sql.toString());
        params.forEach(queryCount::setParameter);

        Integer countData = ((Long) queryCount.getSingleResult()).intValue();

        listResponse.setTotalRecord(countData);
        return listResponse;
    }
}
