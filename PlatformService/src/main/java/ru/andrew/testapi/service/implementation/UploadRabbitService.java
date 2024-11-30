package ru.andrew.testapi.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.andrew.testapi.model.repo_model.Document;
import ru.andrew.testapi.repository.DocumentRepositorySQL;
import ru.andrew.testapi.service.interfaces.UploadService;

@Service
@RequiredArgsConstructor
public class UploadRabbitService implements UploadService {
    private final DocumentRepositorySQL documentRepository;
    private final RabbitTemplate rabbitTemplate;

    public void upload(String fileType, MultipartFile file) throws Exception {
        checkFileType(fileType, file);
        Document document = new Document(fileType, file.getBytes());
        document = documentRepository.save(document);
        rabbitTemplate.convertAndSend("aiQueue", document.getId().toString());
    }

    public void checkFileType(String fileType, MultipartFile file) throws Exception {
        switch (fileType) {
            case "txt":
                if (!file.getName().endsWith(".txt")) {
                    throw new Exception("Невалидный тип файла");
                }
                break;
            case "docx":
                if (!file.getName().endsWith(".docx") && !file.getName().endsWith(".doc")) {
                    throw new Exception("Невалидный тип файла");
                }
                break;
            case "pdf":
                if (!file.getName().endsWith(".pdf")) {
                    throw new Exception("Невалидный тип файла");
                }
                break;
        }
    }
}
