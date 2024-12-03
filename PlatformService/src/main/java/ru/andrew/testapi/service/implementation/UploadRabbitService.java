package ru.andrew.testapi.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.andrew.testapi.model.interfaces.DatabaseDocument;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
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

    @Autowired
    private DocumentService documentService;

    public void upload(MultipartFile file) throws Exception {
        String fileType = UploadService.getFileType(file);
        DatabaseUser user = userService.getCurrentUser();
        ServiceDocument document = ServiceDocument.builder()
                .filetype(fileType)
                .file(file.getBytes())
                .user(user)
                .build();
        DatabaseDocument dbDocument = documentService.save(document);
        rabbitTemplate.convertAndSend("aiQueue", dbDocument.getId().toString());
    }
}
