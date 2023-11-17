package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Size;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SizeService {

    List<Size> getAll();

    Size getOneById(Long id);

    Page getAllByPage(int page);

    Size save(Size size);

    Size update(Size size, Long id);

}
