package com.example.be_adm_double_shop.service.impl;


import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.repository.SizeRepository;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.SizeService;
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
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public ListResponse<Size> getAllByConditon(SizeRequest request) {

        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append(" SELECT * FROM size WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append(" AND CODE LIKE CONCAT('%', :code ,'%') ");
            params.put("code", request.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT('%', :name ,'%') ");
            params.put("name", request.getName());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getStatus())) {
            sql.append(" AND STATUS = :status ");
            params.put("status", request.getStatus());
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


        Query query = entityManager.createNativeQuery(sql.toString(), Size.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());


        sql = new StringBuilder();
        params = new HashMap<>();


        sql.append(" SELECT COUNT(*) FROM size WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getCode())) {
            sql.append(" AND CODE LIKE CONCAT('%', :code ,'%') ");
            params.put("code", request.getCode());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getName())) {
            sql.append(" AND NAME LIKE CONCAT('%', :name ,'%') ");
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

    @Override
    public String save(Size size, String username) {
        if (StringUtil.stringIsNullOrEmty(size.getCode())) {
            int i = 1;
            while (true) {
                String codeGen = Constant.DETAIL_PRODUCT.SIZE + i;
                if (StringUtil.stringIsNullOrEmty(sizeRepository.checkCodeExits(codeGen))) {
                    size.setCode(codeGen);
                    break;
                }
                i++;
            }
        }
        size.setStatus(Math.toIntExact(Constant.ACTIVE));
        size.setCreatedBy(username);
        size.setCreatedTime(DateUtil.dateToString4(new Date()));
        try {
            sizeRepository.save(size);
            return Constant.SUCCESS;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Object update(Size sizeRequest, String username) {
        Size size = sizeRepository.getSizeByCode(sizeRequest.getCode());
        if (!StringUtil.stringIsNullOrEmty(size)) {
            size.setName(sizeRequest.getName());
            size.setDescription(sizeRequest.getDescription());
            size.setStatus(sizeRequest.getStatus());
            size.setUpdatedBy(username);
            size.setUpdatedTime(DateUtil.dateToString4(new Date()));
            sizeRepository.save(size);
            return Constant.SUCCESS;
        } else {
            return "khong tim thay size";
        }
    }
}
