package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.dto.request.BrandRequest;
import com.example.be_adm_double_shop.dto.request.CategoryRequest;
import com.example.be_adm_double_shop.dto.response.ListResponse;
import com.example.be_adm_double_shop.entity.Brand;
import com.example.be_adm_double_shop.entity.Category;

public interface CategoryService {

    ListResponse<Category> getAllByConditon(CategoryRequest request);

    String save(Category category, String username);

    Object update(Category category, String username);

}
