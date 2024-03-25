package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.BrandRequest;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.entity.Size;
import com.example.be_adm_double_shop.repository.BrandRepository;
import com.example.be_adm_double_shop.repository.SizeRepository;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.BrandService;
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
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public ListResponse<Brand> getAllByConditon(BrandRequest request) {

        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        sql.append(" SELECT * FROM brand WHERE 1 = 1 ");

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
    public String save(Brand brand, String username) {
        if (StringUtil.stringIsNullOrEmty(brand.getCode())) {
            int i = 1;
            while (true) {
                String codeGen = Constant.DETAIL_PRODUCT.BRAND + i;
                if (StringUtil.stringIsNullOrEmty(brandRepository.checkCodeExits(codeGen))) {
                    brand.setCode(codeGen);
                    break;
                }
                i++;
            }
        }
        brand.setStatus(Constant.ACTIVE);
        brand.setCreatedBy(username);
        brand.setCreatedTime(DateUtil.dateToString4(new Date()));
        try {
            brandRepository.save(brand);
            return Constant.SUCCESS;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Object update(Brand branRequest, String username) {
        Brand brand = brandRepository.getBrandByCode(branRequest.getCode());
        if (!StringUtil.stringIsNullOrEmty(brand)) {
            brand.setName(branRequest.getName());
            brand.setDescription(branRequest.getDescription());
            brand.setStatus(branRequest.getStatus());
            brand.setUpdatedBy(username);
            brand.setUpdatedTime(DateUtil.dateToString4(new Date()));
            brandRepository.save(brand);
            return Constant.SUCCESS;
        } else {
            return "khong tim thay size";
        }
    }
}
