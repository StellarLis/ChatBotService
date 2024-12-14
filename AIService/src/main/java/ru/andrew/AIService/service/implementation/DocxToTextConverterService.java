package ru.andrew.AIService.service.implementation;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.extractor.POITextExtractor;
import org.springframework.stereotype.Service;
import ru.andrew.AIService.service.interfaces.FileToTextConverterService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class DocxToTextConverterService implements FileToTextConverterService {
    @Override
    public String convertToText(byte[] fileBody) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBody);
        POITextExtractor extractor = ExtractorFactory.createExtractor(inputStream);
        return extractor.getText();
    }
}
