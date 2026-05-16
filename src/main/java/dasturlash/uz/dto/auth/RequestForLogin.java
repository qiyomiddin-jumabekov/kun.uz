package dasturlash.uz.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record RequestForLogin(
        @NotBlank(message = "Username is not empty") String username,
        @NotBlank(message = "Password is not empty") String password) {
}
