package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.dto.request.MaterialRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Material;
import com.example.be_adm_double_shop.repository.MaterialRepository;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.StringUtil;
import io.swagger.models.auth.In;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaterialSer {
    @Autowired
    private MaterialRepository materialRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public ListResponse<Material> getAllByCondition(MaterialRequest request) {
        ListResponse listResponse = new ListResponse();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        sql.append("select * from material where 1 = 1");

        if(!StringUtil.stringIsNullOrEmty(request.getCode())) {
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

        Query query = entityManager.createNativeQuery(sql.toString(), Material.class);
        params.forEach(query::setParameter);

        listResponse.setListData(query.getResultList());

//
        sql = new StringBuilder();
        params = new HashMap<>();


        sql.append(" select count(*) from material where 1 = 1 ");

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

    public List<Material> getAll() {
        return materialRepository.findAll();
    }

    public List<Material> phanTrang(Integer viTri) {
        Pageable p = PageRequest.of(viTri, 5);
        return materialRepository.findAll(p).toList();
    }

    public Material chiTiet(Long id) {
        return materialRepository.findById(id).get();

    }

    public Material add(Material m){
        m.setCreatedTime(LocalDateTime.now().toString());
        m.setCreatedBy("TranTung");
        m.setStatus(Constant.ACTIVE);
        return materialRepository.save(m);
    }

    public Material update(Material m, Long id) {
        m.setId(id);
        m.setCreatedTime(LocalDateTime.now().toString());
        return materialRepository.save(m);
    }

    public Material delete(Long id) {
        Material material = materialRepository.findById(id).get();
        material.setStatus(0);
        return materialRepository.save(material);
    }
}
