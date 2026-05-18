package dasturlash.uz.dto.verification;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ConfirmDto(
        @Email(message = "Email is required") String email,
        @NotBlank(message = "Code is required") String code) {
}
