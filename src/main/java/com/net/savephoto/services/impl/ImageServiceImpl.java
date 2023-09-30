package com.net.savephoto.services.impl;

import com.net.savephoto.entities.Image;
import com.net.savephoto.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImpl extends ImageUtilImpl{
    @Autowired
    private ImageRepository repository;

    @Override
    public void uploadImage(MultipartFile file) {
        Image image = new Image();

        try {
            image.setName(file.getOriginalFilename());
            image.setType(file.getContentType());
            image.setImageData(compress(file.getBytes()));

            repository.save(image);
        }catch (Exception e){
            log.info("an error occured: "+ e.getMessage());
        }
    }

    @Transactional
    @Override
    public ResponseEntity<byte[]> download(String imageName) {
        try {
            Optional<Image> image = repository.findByName(imageName);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(decompress(image.get().getImageData()));
        }catch (Exception err){
            log.info("An error occured: "+err.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.valueOf("text")).body("error occurred".getBytes());
        }
    }
}
