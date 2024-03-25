package com.example.be_adm_double_shop.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.be_adm_double_shop.dto.ValidationException;
import com.example.be_adm_double_shop.dto.request.ColorRequest;
import com.example.be_adm_double_shop.dto.request.ProductRequest;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.*;
import com.example.be_adm_double_shop.repository.DetailProductRepository;
import com.example.be_adm_double_shop.repository.ProductRepository;
import com.example.be_adm_double_shop.repository.SizeRepository;
import com.example.be_adm_double_shop.service.ProductService;
import com.example.be_adm_double_shop.util.Constant;
import com.example.be_adm_double_shop.util.DateUtil;
import com.example.be_adm_double_shop.util.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SizeRepository sizeRepository;


    @Autowired
    private DetailProductRepository detailProductRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Object getAllProductByCondition(ProductRequest request) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        ListResponse<Product> listProductResponse = new ListResponse<>();

        sql.append(" SELECT p.* FROM product p ");
        sql.append(" INNER JOIN detail_product dp ON p.id = dp.id_product ");
        sql.append(" INNER JOIN color c ON dp.id_color = c.id ");
        sql.append(" INNER JOIN collar cl ON dp.id_collar = cl.id ");
        sql.append(" INNER JOIN size s ON dp.id_size = s.id ");
        sql.append(" INNER JOIN brand b ON dp.id_brand = b.id ");
        sql.append(" INNER JOIN category ct ON dp.id_category =  ct.id");
        sql.append(" INNER JOIN material mt ON dp.id_material = mt.id ");
        sql.append(" WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            sql.append(" AND c.id = :id ");
            params.put("id", request.getIdSize());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            sql.append(" AND cl.id = :id ");
            params.put("id", request.getIdCollar());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            sql.append(" AND s.id = :id ");
            params.put("id", request.getIdSize());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            sql.append(" AND b.id = :id ");
            params.put("id", request.getIdBrand());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            sql.append(" AND ct.id = :id ");
            params.put("id", request.getIdCategory());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdMaterial())) {
            sql.append(" AND mt.id = :id ");
            params.put("id", request.getIdMaterial());
        }

        sql.append(" ORDER BY p.created_time DESC ");

        if (!StringUtil.stringIsNullOrEmty(request.getPage())) {
            sql.append(" LIMIT :page, :pageSize");
            if (request.getPage() == 0) {
                params.put("page", 0);
            } else {
                params.put("page", (request.getPage() * request.getPageSize()));
            }
            params.put("pageSize", request.getPageSize());
        }


        Query query = entityManager.createNativeQuery(sql.toString(), Product.class);
        params.forEach(query::setParameter);

        listProductResponse.setListData(query.getResultList());

        sql.setLength(0);
        params.clear();

        sql.append(" SELECT COUNT(*) FROM product p ");
        sql.append(" INNER JOIN detail_product dp ON p.id = dp.id_product ");
        sql.append(" INNER JOIN color c ON dp.id_color = c.id ");
        sql.append(" INNER JOIN collar cl ON dp.id_collar = cl.id ");
        sql.append(" INNER JOIN brand b ON dp.id_brand = b.id ");
        sql.append(" INNER JOIN category ct ON dp.id_category = ct.id  ");
        sql.append(" INNER JOIN material mt ON dp.id_material = mt.id ");
        sql.append(" WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            sql.append(" AND c.id = :id ");
            params.put("id", request.getIdSize());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            sql.append(" AND cl.id = :id ");
            params.put("id", request.getIdCollar());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            sql.append(" AND s.id = :id ");
            params.put("id", request.getIdSize());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            sql.append(" AND b.id = :id ");
            params.put("id", request.getIdBrand());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            sql.append(" AND ct.id = :id ");
            params.put("id", request.getIdCategory());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdMaterial())) {
            sql.append(" AND mt.id = :id ");
            params.put("id", request.getIdMaterial());
        }

        Query totalQuery = entityManager.createNativeQuery(sql.toString());
        params.forEach(totalQuery::setParameter);

        int totalRecord = ((Long) totalQuery.getSingleResult()).intValue();

        listProductResponse.setTotalRecord(totalRecord);
        return listProductResponse;
    }

    @Override
    public Object getAllProduct(ProductRequest request) throws Exception {
        int totalRecord = request.getPageSize();
        ListResponse<Product> listResponse = (ListResponse<Product>) getAllProductByCondition(request);
        if (listResponse.getTotalRecord() < request.getPageSize()) {
            totalRecord = listResponse.getTotalRecord();
        }
        for (int i = 0; i < totalRecord; i++) {
            listResponse.getListData().get(i).setListImages(cloudinary.search().expression("folder:double_shop/product/" + listResponse.getListData().get(i).getCode() + "/*").maxResults(500).execute());
        }
        return listResponse;
    }

    @Override
    public Object createProduct(ProductRequest request, String username) throws Exception {

        String code = Constant.PRODUCT.PRODUCT;
        while (true) {
            code += UUID.randomUUID();
            if (productRepository.existsByCode(code) == false) {
                break;
            }
        }
        // tạo product
        String folderPath = "";
        Product product = Product.builder()
                .code(code)
                .name(request.getName())
                .createdBy(username)
                .createdTime(DateUtil.dateToString4(new Date()))
                .status(Constant.ACTIVE)
                .build();
        product = productRepository.save(product);

        Set<DetailProduct> listDetailProduct = new HashSet<>();

        if (product != null) {
            for (SizeRequest sizeRequest : request.getListSize()) {
                for (ColorRequest colorRequest : sizeRequest.getListColor()) {
                    DetailProduct detailProduct = DetailProduct.builder()
                            .product(product)
                            .size(sizeRepository.findById(sizeRequest.getId()).get())
                            .collar(Collar.builder().id(request.getIdCollar()).build())
                            .brand(Brand.builder().id(request.getIdBrand()).build())
                            .category(Category.builder().id(request.getIdBrand()).build())
                            .material(Material.builder().id(request.getIdMaterial()).build())
                            .color(Color.builder().id(colorRequest.getId()).build())
                            .createdBy(username)
                            .createdTime(DateUtil.dateToString4(new Date()))
                            .quantity(colorRequest.getQuantity())
                            .status(Constant.ACTIVE)
                            .price(colorRequest.getPrice())
                            .build();
                    listDetailProduct.add(detailProduct);
                }
            }
            detailProductRepository.saveAll(listDetailProduct);

            folderPath = Constant.ROOT_FOLDER + "/" + Constant.PRODUCT_FOLDER + "/" + product.getCode();
            cloudinary.api().createFolder(folderPath, ObjectUtils.emptyMap());

            for (MultipartFile multipartFile : request.getListImage()) {
                HashMap folder = new HashMap();
                folder.put("folder", folderPath);
                cloudinary.uploader().upload(multipartFile.getBytes(), folder);
            }
            return product;
        }

        return new ValidationException(Constant.API001, "");
    }

    @Override
    public Object updateProduct(ProductRequest request, String username) {
        return null;
    }

    @Override
    public Object getAllTreeData() {
        List<Product> list = productRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setListDetailProduct(detailProductRepository.getActiveDetailProduct(list.get(i).getId()));
        }
        return list;
    }

}
