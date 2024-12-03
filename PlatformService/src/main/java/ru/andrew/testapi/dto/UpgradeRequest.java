package ru.andrew.testapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на апгрейд аккаунта")
public class UpgradeRequest {
    @Schema(description = "Имя компании")
    @Size(min = 2, max = 50, message = "Недопустимое имя компании!")
    @NotBlank(message = "Имя компании пустое")
    private String companyName;
}
