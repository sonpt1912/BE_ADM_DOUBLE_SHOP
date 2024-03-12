package com.example.be_adm_double_shop.service.impl;

import com.cloudinary.Cloudinary;
import com.example.be_adm_double_shop.service.CloudinaryService;
import com.example.be_adm_double_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;


    @Override
    public Object deleteImageBy(String publicId) throws IOException {
        return cloudinary.uploader().destroy(publicId, new HashMap());
    }

    @Override
    public Object uploadImage(MultipartFile image, String folder) throws IOException {
        HashMap<String, String> folderPath = new HashMap<>();
        folderPath.put("folder", Constant.ROOT_FOLDER + "/" + Constant.PRODUCT_FOLDER + "/" + folder);
        return cloudinary.uploader().upload(image.getBytes(), folderPath);
    }
}
