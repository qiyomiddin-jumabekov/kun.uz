package dasturlash.uz.dto.tag;

import jakarta.validation.constraints.NotBlank;

public record RequestForCreateTag(
        @NotBlank(message = "Tag name is not be empty") String name,
        @NotBlank(message = "Article id is not be empty")String articleId) {
}
