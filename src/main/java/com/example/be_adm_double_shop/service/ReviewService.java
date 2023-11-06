package com.example.be_adm_double_shop.service;

import com.example.be_adm_double_shop.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
        List<Review> getAll();

        Review getOneById(Long id);

        Page getAllByPage(int page, int pageSize);

        Review save(Review review);
}
