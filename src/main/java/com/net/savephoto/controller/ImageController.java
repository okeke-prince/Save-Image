package com.net.savephoto.controller;

import com.net.savephoto.services.ImageService;
import com.net.savephoto.services.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/v1/image")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @PostMapping("/upload")
    public String upload(@RequestParam("image") MultipartFile file) {
        imageService.uploadImage(file);
        return "success";
    }
    @GetMapping("/download/{imageName}")
    public ResponseEntity<byte[]> download(@PathVariable String imageName) {
        return imageService.download(imageName);

    }
}
