package dasturlash.uz.dto.article;

import jakarta.validation.constraints.NotBlank;

public record RequestGetArticlesByTagName(
        @NotBlank(message = "Tag name is not be empty") String name) {
}
