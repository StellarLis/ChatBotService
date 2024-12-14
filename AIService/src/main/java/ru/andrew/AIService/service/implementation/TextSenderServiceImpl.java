package ru.andrew.AIService.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.andrew.AIService.service.interfaces.TextSenderService;

@Service
@Slf4j
public class TextSenderServiceImpl implements TextSenderService {
    @Override
    public void send(String text) throws Exception {
        log.info("TextSender text: " + text);
    }
}
