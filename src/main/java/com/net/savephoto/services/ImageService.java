package com.net.savephoto.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void uploadImage(MultipartFile file);

    ResponseEntity<byte[]> download(String imageName);
}
