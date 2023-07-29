package com.abdi.cardiscover.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abdi.cardiscover.entity.ImageEntity;

import com.abdi.cardiscover.repository.ImageRepository;


@Component 
@RestController
public class Image {
    private final ImageRepository imageRepository;


    @Autowired
    Image(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @GetMapping("/get-all-images")
  

    @ResponseBody
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public List<ImageEntity> getAllImages() throws SQLException {
        Iterable<ImageEntity> result = imageRepository.findAll();
        List<ImageEntity> images = new ArrayList<>();
        result.forEach(images::add);
        return images;
    }

  
    

    @ResponseBody
    // Serlizes the object as JSON due to the @ResponseBody annotation
    public String getImage(@RequestParam("id") Long id) throws SQLException {
        // Find by returns a ref to the db object, hence the get() here.
        ImageEntity image = imageRepository.findById(id).get();
        if(image == null){ return "No image found";}
        return image.toString();
    }

    @PostMapping("/upload-image")
    

    @ResponseBody
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        return String.valueOf(file.getSize());
    };

}
