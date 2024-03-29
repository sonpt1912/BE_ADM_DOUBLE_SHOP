package com.example.be_adm_double_shop.controller;

import com.example.be_adm_double_shop.dto.request.ProductRequest;
import com.example.be_adm_double_shop.entity.DetailProduct;
import com.example.be_adm_double_shop.security.JwtProvider;
import com.example.be_adm_double_shop.service.CloudinaryService;
import com.example.be_adm_double_shop.service.DetailProductService;
import com.example.be_adm_double_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private DetailProductService detailProductService;

    @PostMapping("/get-all-product")
    public ResponseEntity getAllProduct(@RequestBody ProductRequest request) throws Exception {
        return new ResponseEntity(productService.getAllProduct(request), HttpStatus.OK);
    }

    @PostMapping("/remove-image")
    public ResponseEntity deleteImage(@RequestParam("id") String publicId) throws IOException {
        return new ResponseEntity(cloudinaryService.deleteImageBy(publicId), HttpStatus.OK);
    }

    @PostMapping("/upload-image")
    public ResponseEntity updateImageToFolder(@RequestParam MultipartFile image, @RequestParam("folder") String folder) throws IOException {
        return new ResponseEntity(cloudinaryService.uploadImage(image, folder), HttpStatus.OK);
    }

    @PostMapping("/get-detail-product")
    public ResponseEntity getDetailProduct(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity(detailProductService.getListDetailProductByProductId(productRequest.getIdProduct()), HttpStatus.OK);
    }

    @PostMapping("/create-product")
    public ResponseEntity createProduct(@RequestBody ProductRequest productRequest, @RequestHeader("Authorization") String token) throws Exception {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(productService.createProduct(productRequest, username), HttpStatus.OK);
    }

    @PostMapping("/update-detail-product")
    public ResponseEntity updateProduct(@RequestBody DetailProduct detailProduct, @RequestHeader("Authorization") String token) {
        String username = jwtProvider.getUsernameFromToken(token);
        return new ResponseEntity(detailProductService.updateDetailProduct(detailProduct, username), HttpStatus.OK);
    }

}
