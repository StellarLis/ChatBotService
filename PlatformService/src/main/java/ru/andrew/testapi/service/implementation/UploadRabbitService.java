package ru.andrew.testapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.andrew.testapi.model.interfaces.DatabaseDocument;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
import ru.andrew.testapi.model.repo_model.DocumentStatus;
import ru.andrew.testapi.model.service_model.ServiceDocument;
import ru.andrew.testapi.repository.repo_service.DocumentService;
import ru.andrew.testapi.repository.repo_service.UserService;
import ru.andrew.testapi.service.interfaces.UploadService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadRabbitService implements UploadService {
    private final RabbitTemplate rabbitTemplate;
    private final UserService userService;
    private final DocumentService documentService;

    public void upload(MultipartFile file) throws Exception {
        String fileType = UploadService.getFileType(file);
        DatabaseUser user = userService.getCurrentUser();
        ServiceDocument document = ServiceDocument.builder()
                .filename(file.getOriginalFilename())
                .filetype(fileType)
                .file(file.getBytes())
                .user(user)
                .status(DocumentStatus.ON_PROCESSING)
                .build();
        DatabaseDocument dbDocument = documentService.save(document);
        rabbitTemplate.convertAndSend("aiQueue", dbDocument.getId().toString());
    }
}
