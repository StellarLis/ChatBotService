package ru.andrew.testapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {
    @Schema(description = "Имя пользователя", example = "John")
    @Size(min = 5, max = 50, message = "Имя пользователя должно быть от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String username;

    @Schema(description = "Пароль", example = "qwerty1234567")
    @Size(max = 255, message = "Длина пароля должна быть не больше 255 символов")
    private String password;
}
