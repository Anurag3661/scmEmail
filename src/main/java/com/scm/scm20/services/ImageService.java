package com.scm.scm20.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile contactImage, String filename);

    String getUrlFromPublicId(String publicId);

}
