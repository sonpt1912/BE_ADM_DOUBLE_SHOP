package com.example.be_adm_double_shop.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    Object deleteImageBy(String id) throws IOException;

    Object uploadImage(MultipartFile image, String folder) throws IOException;

}
