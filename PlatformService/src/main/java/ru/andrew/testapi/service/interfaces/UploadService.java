package ru.andrew.testapi.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String[] SUPPORTED_TYPES = {".txt", ".docx", ".pdf"};
    void upload(MultipartFile multipartFile) throws Exception;
    static String getFileType(MultipartFile file) throws Exception {
        for (String supportedType : SUPPORTED_TYPES) {
            if (file.getOriginalFilename().endsWith(supportedType)) {
                return supportedType;
            }
        }
        throw new Exception("Неподдерживаемый тип файла");
    }
}
