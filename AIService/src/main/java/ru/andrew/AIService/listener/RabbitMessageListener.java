package ru.andrew.AIService.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.andrew.AIService.model.interfaces.DatabaseDocument;
import ru.andrew.AIService.model.repo_model.DocumentStatus;
import ru.andrew.AIService.repository.repo_service.DocumentService;
import ru.andrew.AIService.service.interfaces.FileToTextConverterService;
import ru.andrew.AIService.service.interfaces.TextSenderService;

@Component
@Slf4j
public class RabbitMessageListener {
    private final DocumentService documentService;
    private final TextSenderService textSenderService;
    private final FileToTextConverterService pdfConverterService;
    private final FileToTextConverterService docxConverterService;

    public RabbitMessageListener(
            DocumentService documentService,
            TextSenderService textSenderService,
            @Qualifier("pdfToTextConverterService") FileToTextConverterService pdfConverterService,
            @Qualifier("docxToTextConverterService") FileToTextConverterService docxConverterService
    ) {
        this.documentService = documentService;
        this.textSenderService = textSenderService;
        this.pdfConverterService = pdfConverterService;
        this.docxConverterService = docxConverterService;
    }

    @RabbitListener(queues = "aiQueue")
    public void processQueue(String message) {
        Long documentId = Long.valueOf(message);
        DatabaseDocument document = documentService.getById(documentId);
        try {
            switch (document.getFiletype()) {
                case ".txt":
                    String txtText = new String(document.getFile());
                    textSenderService.send(txtText);
                    break;
                case ".docx":
                    String docxText = docxConverterService.convertToText(document.getFile());
                    textSenderService.send(docxText);
                    break;
                case ".pdf":
                    String pdfText = pdfConverterService.convertToText(document.getFile());
                    textSenderService.send(pdfText);
                    break;
            }
            document.setStatus(DocumentStatus.PROCESSED_SUCCESSFULLY);
            documentService.save(document);
        } catch (Exception e) {
            log.info("Exception!: " + e.getMessage());
            document.setStatus(DocumentStatus.FAILED);
            documentService.save(document);
        }
    }
}
