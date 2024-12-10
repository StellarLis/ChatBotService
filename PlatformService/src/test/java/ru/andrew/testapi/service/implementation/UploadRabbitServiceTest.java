package ru.andrew.testapi.service.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import ru.andrew.testapi.model.interfaces.DatabaseDocument;
import ru.andrew.testapi.model.interfaces.DatabaseUser;
import ru.andrew.testapi.model.repo_model.DocumentSQL;
import ru.andrew.testapi.model.repo_model.UserSQL;
import ru.andrew.testapi.repository.repo_service.DocumentService;
import ru.andrew.testapi.repository.repo_service.UserService;

@ExtendWith(MockitoExtension.class)
public class UploadRabbitServiceTest {
    @InjectMocks
    private UploadRabbitService uploadRabbitService;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private UserService userService;
    @Mock
    private DocumentService documentService;
    @Mock
    private final MockMultipartFile multipartFile = new MockMultipartFile(
            "test.txt", (byte[]) null);

    @Test
    void upload_correctFile_noException() {
        Mockito.when(multipartFile.getOriginalFilename()).thenReturn("test.txt");
        DatabaseUser dbUser = UserSQL.builder().build();
        Mockito.when(userService.getCurrentUser()).thenReturn(dbUser);
        DatabaseDocument dbDocument = DocumentSQL.builder().id(1L).build();
        Mockito.when(documentService.save(ArgumentMatchers.any())).thenReturn(dbDocument);
        Assertions.assertDoesNotThrow(() -> uploadRabbitService.upload(multipartFile));
    }

    @Test
    void upload_incorrectFiletype_noException() {
        Mockito.when(multipartFile.getOriginalFilename()).thenReturn("test.asd");
        Assertions.assertThrows(Exception.class, () -> uploadRabbitService.upload(multipartFile));
    }
}
