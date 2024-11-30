package ru.andrew.testapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.andrew.testapi.dto.FileUploadRequest;
import ru.andrew.testapi.service.UploadRabbitService;
import ru.andrew.testapi.service.interfaces.UploadService;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AIController {
    private final UploadService uploadService;

    @PostMapping(path = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestPart FileUploadRequest request,
            @RequestPart MultipartFile multipartFile
    ) {
        try {
            uploadService.upload(request.getFileType(), multipartFile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
