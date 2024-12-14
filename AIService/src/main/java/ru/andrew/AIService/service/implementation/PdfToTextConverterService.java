package ru.andrew.AIService.service.implementation;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import ru.andrew.AIService.service.interfaces.FileToTextConverterService;

@Service
public class PdfToTextConverterService implements FileToTextConverterService {

    @Override
    public String convertToText(byte[] fileBody) throws Exception {
        String result;
        try (PDDocument document = PDDocument.load(fileBody)) {
            result = new PDFTextStripper().getText(document);
        }
        return result;
    }
}
