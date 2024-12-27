package ru.andrew.AIService.service.implementation;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import ru.andrew.AIService.service.interfaces.FileToTextConverterService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class DocxToTextConverterService implements FileToTextConverterService {
    @Override
    public String convertToText(byte[] fileBody) throws IOException {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(fileBody)) {
            XWPFDocument document = new XWPFDocument(bais);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            return extractor.getText();
        }
    }
}
