package ru.andrew.testapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.andrew.testapi.dto.AppErrorResponse;
import ru.andrew.testapi.service.interfaces.UploadService;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@Slf4j
public class AIController {
    private UploadService uploadService;

    @Autowired
    public AIController(
            @Qualifier("uploadRabbitService") UploadService uploadService
    ) {
        this.uploadService = uploadService;
    }

    @PostMapping(path = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestPart MultipartFile multipartFile
    ) {
        try {
            uploadService.upload(multipartFile);
        } catch (Exception e) {
            AppErrorResponse response = new AppErrorResponse(400, e.getMessage());
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
        }
        return ResponseEntity.ok().build();
    }
}
