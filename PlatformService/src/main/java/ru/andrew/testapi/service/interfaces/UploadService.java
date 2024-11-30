package ru.andrew.testapi.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    void upload(String fileType, MultipartFile multipartFile) throws Exception;
}
