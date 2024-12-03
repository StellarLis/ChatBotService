package ru.andrew.testapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на загрузку файла для AI сервиса")
public class FileUploadRequest {
    @Schema(description = "Тип загружаемого файла")
    @Size(min = 1, max = 10, message = "Тип загружаемого файла недопустим")
    @NotBlank(message = "Тип загружаемого файла не должен быть пустым")
    private String fileType;


}
