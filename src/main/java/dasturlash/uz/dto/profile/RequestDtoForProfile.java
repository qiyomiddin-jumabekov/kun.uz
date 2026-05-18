package dasturlash.uz.dto.profile;

import jakarta.validation.constraints.NotBlank;

public record RequestDtoForProfile(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Surname is required") String surname,
        @NotBlank(message = "Email is required") String email,
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password) {
}
