package com.example.be_adm_double_shop.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.be_adm_double_shop.dto.ValidationException;
import com.example.be_adm_double_shop.dto.request.ColorRequest;
import com.example.be_adm_double_shop.dto.request.ProductRequest;
import com.example.be_adm_double_shop.dto.request.SizeRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.*;
import com.example.be_adm_double_shop.repository.*;
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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private CollarRepository collarRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DetailProductRepository detailProductRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Object getAllProductByCondition(ProductRequest request) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        ListResponse<Product> listProductResponse = new ListResponse<>();

        sql.append(" SELECT p.*");
        sql.append(" FROM product p JOIN detail_product dp ON p.id = dp.id_product ");
        sql.append(" JOIN brand b on dp.id_brand = b.id");
        sql.append(" JOIN collar cl ON dp.id_collar = cl.id ");
        sql.append(" JOIN category ct on dp.id_category = ct.id ");
        sql.append(" JOIN material m on dp.id_material = m.id ");
        sql.append(" JOIN color c on dp.id_color = c.id ");
        sql.append(" JOIN size s on dp.id_size = s.id WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            sql.append(" AND b.id = :idBrand ");
            params.put("idBrand", request.getIdBrand());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            sql.append(" AND ct.id = :idCategory ");
            params.put("idCategory", request.getIdCategory());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            sql.append(" AND cl.id = :idcollar ");
            params.put("idcollar", request.getIdCollar());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdColor())) {
            sql.append(" AND c.id = :idColor ");
            params.put("idColor", request.getIdColor());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdMaterial())) {
            sql.append(" AND m.id = :idMaterial ");
            params.put("idMaterial", request.getIdMaterial());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            sql.append(" AND s.id = :idSize");
            params.put("idSize", request.getIdSize());
        }

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

        sql.append(" SELECT COUNT(*)");
        sql.append(" FROM product p JOIN detail_product dp ON p.id = dp.id_product ");
        sql.append(" JOIN brand b on dp.id_brand = b.id");
        sql.append(" JOIN collar cl ON dp.id_collar = cl.id ");
        sql.append(" JOIN category ct on dp.id_category = ct.id ");
        sql.append(" JOIN material m on dp.id_material = m.id ");
        sql.append(" JOIN color c on dp.id_color = c.id ");
        sql.append(" JOIN size s on dp.id_size = s.id WHERE 1 = 1 ");

        if (!StringUtil.stringIsNullOrEmty(request.getIdBrand())) {
            sql.append(" AND b.id = :idBrand ");
            params.put("idBrand", request.getIdBrand());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCategory())) {
            sql.append(" AND ct.id = :idCategory ");
            params.put("idCategory", request.getIdCategory());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdCollar())) {
            sql.append(" AND cl.id = :idcollar ");
            params.put("idcollar", request.getIdCollar());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdColor())) {
            sql.append(" AND c.id = :idColor ");
            params.put("idColor", request.getIdColor());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdMaterial())) {
            sql.append(" AND m.id = :idMaterial ");
            params.put("idMaterial", request.getIdMaterial());
        }

        if (!StringUtil.stringIsNullOrEmty(request.getIdSize())) {
            sql.append(" AND s.id = :idSize");
            params.put("idSize", request.getIdSize());
        }

        Query totalQuery = entityManager.createNativeQuery(sql.toString());
        params.forEach(totalQuery::setParameter);

        int totalRecord = ((Long) totalQuery.getSingleResult()).intValue();

        listProductResponse.setTotalRecord(totalRecord);
        return listProductResponse;
    }

    @Override
    public Object getAllProduct(ProductRequest request) throws Exception {
        ListResponse<Product> listResponse = (ListResponse<Product>) getAllProductByCondition(request);
        for (int i = 0; i < listResponse.getTotalRecord(); i++) {
            listResponse.getListData().get(i).setListImages(cloudinary.search().expression("folder:samples/animals/*").maxResults(500).execute());
        }
        return listResponse;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Object createProduct(ProductRequest request, String username) throws Exception {
        // tạo product
        String folderPath = "";
        Product product = Product.builder()
                .code(request.getCode())
                .name(request.getName())
                .createdBy(username)
                .createdTime(DateUtil.dateToString4(new Date()))
                .status(Constant.ACTIVE)
                .build();
        product = productRepository.save(product);

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
                            .build();
                    detailProductRepository.save(detailProduct);
                }
            }
            folderPath = Constant.ROOT_FOLDER + "/" + Constant.PRODUCT_FOLDER + "/" + product.getCode();
            cloudinary.api().createFolder(folderPath, ObjectUtils.emptyMap());

            for (MultipartFile multipartFile : request.getListImage()) {
                HashMap folder = new HashMap();
                folder.put("folder", folderPath);
                cloudinary.uploader().upload(multipartFile.getBytes(), folder);
            }
            return Constant.SUCCESS;
        }

        return new ValidationException(Constant.API001, "");
    }

}
