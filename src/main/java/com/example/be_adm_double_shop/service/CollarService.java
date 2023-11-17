package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Collar;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CollarService {

    List<Collar> getAll();

    Collar getOneById(Long id);

    Page getAllByPage(int page);

    Collar save(Collar collar);

    Collar update(Collar collar, Long id);

}
