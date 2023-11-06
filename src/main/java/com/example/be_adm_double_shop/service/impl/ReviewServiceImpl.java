package com.example.be_adm_double_shop.service.impl;

import com.example.be_adm_double_shop.entity.Review;
import com.example.be_adm_double_shop.repository.ReviewRepository;
import com.example.be_adm_double_shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService  {
    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getOneById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public Page getAllByPage(int page, int pageSize) {
        Pageable p = PageRequest.of(page, pageSize);
        return reviewRepository.findAll(p);
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }
}
