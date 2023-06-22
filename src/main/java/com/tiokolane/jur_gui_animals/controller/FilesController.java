package com.tiokolane.jur_gui_animals.controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.tiokolane.jur_gui_animals.exception.ResponseMessage;
import com.tiokolane.jur_gui_animals.model.Category;
import com.tiokolane.jur_gui_animals.model.FileInfo;
import com.tiokolane.jur_gui_animals.model.Picture;
import com.tiokolane.jur_gui_animals.payload.FileUploadDto;
import com.tiokolane.jur_gui_animals.repository.CategoryRepository;
import com.tiokolane.jur_gui_animals.repository.PictureRepository;
import com.tiokolane.jur_gui_animals.service.FilesStorageService;

import lombok.val;

@RestController
@RequestMapping("/api")
public class FilesController {
    @Autowired
    FilesStorageService storageService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PictureRepository pictureRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(required = false) Integer category_id) {
        String message = "";
        try {
            Resource file_saved =  storageService.save(file);
            message = "Uploaded the file successfully: " + file_saved.getFilename();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", file_saved.getFilename().toString()).build()
                    .toString();
            Picture picture = new Picture();
            Category category = categoryRepository.getById((long) category_id);
            picture.setCategory(category);
            picture.setUrl(url);
            pictureRepository.save(picture);
            List<Picture> pictures = category.getPictures();
            pictures.add(picture);
            category.setPictures(pictures);
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
