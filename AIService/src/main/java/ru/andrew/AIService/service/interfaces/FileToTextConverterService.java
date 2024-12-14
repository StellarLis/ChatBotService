package ru.andrew.AIService.service.interfaces;

public interface FileToTextConverterService {
    String convertToText(byte[] fileBody) throws Exception;
}
