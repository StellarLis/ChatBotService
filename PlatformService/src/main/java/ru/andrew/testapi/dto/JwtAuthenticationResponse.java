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
@Schema(description = "Ответ с токеном доступа")
public class JwtAuthenticationResponse {
    @Schema(description = "Токен доступа", example = "ejfj2ijdsc.234eu329jrdiej.jrefoij429o")
    private String token;
}
