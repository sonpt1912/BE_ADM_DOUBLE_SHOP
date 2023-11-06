package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.entity.Review;
import com.example.be_adm_double_shop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = { "http://localhost:3000"}, allowCredentials = "true")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

//    @GetMapping("/load")
//    public List<Review> getAllReviews() {
//        return reviewService.getAllReviews();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
//        Optional<Review> review = reviewService.getReviewById(id);
//        return review.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @PostMapping("/add")
//    public void addReview(@RequestBody Review review) {
//        reviewService.saveReview(review);
//    }
//@PostMapping("/save")
//public void add (@RequestBody Review review) {
//         reviewService.saveReview(review);
//}
//
//    @PutMapping("update/{id}")
//    public void updateReview(@PathVariable Long id, @RequestBody Review review) {
//        review.setId(id);
//        reviewService.saveReview(review);
//    }
//
//    @DeleteMapping("delete/{id}")
//    public void deleteReview(@PathVariable Long id) {
//        reviewService.deleteReview(id);
//    }
@GetMapping("/get-all")
public ResponseEntity getAll() {
    return ResponseEntity.ok(reviewService.getAll());
}

    @GetMapping("/get-one-by-id/{id}")
    public ResponseEntity getOneById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reviewService.getOneById(id));
    }

    @GetMapping("/get-review-by-page")
    public ResponseEntity getAllPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return ResponseEntity.ok(reviewService.getAllByPage(page, pageSize));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.save(review));
    }
}

