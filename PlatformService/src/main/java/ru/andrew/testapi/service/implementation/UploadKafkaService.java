package ru.andrew.testapi.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.andrew.testapi.repository.DocumentRepositorySQL;
import ru.andrew.testapi.service.interfaces.UploadService;

@Service
@RequiredArgsConstructor
public class UploadKafkaService implements UploadService {
    private final DocumentRepositorySQL documentRepository;

    @Override
    public void upload(String fileType, MultipartFile multipartFile) throws Exception {
        // TODO: do this service
    }
}
