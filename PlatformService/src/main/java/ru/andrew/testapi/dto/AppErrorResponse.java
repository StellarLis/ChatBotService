package ru.andrew.testapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ошибка от сервера")
public class AppErrorResponse {
    @Schema(description = "Код ответа", example = "400")
    private int statusCode;
    @Schema(description = "Сообщение ошибки", example = "Неправильно введённые данные")
    private String message;
}
