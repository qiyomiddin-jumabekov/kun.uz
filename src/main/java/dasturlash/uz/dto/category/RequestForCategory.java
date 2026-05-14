package dasturlash.uz.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RequestForCategory(
        @Positive(message = "Order number is required") Integer orderNumber,
        @NotBlank(message = "nameUz is required") String nameUz,
        @NotBlank(message = "nameRu is required") String nameRu,
        @NotBlank(message = "nameEn is required") String nameEn,
        @NotBlank(message = "Key is required") String key) {
}
