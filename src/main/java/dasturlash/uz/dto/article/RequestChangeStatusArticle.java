package dasturlash.uz.dto.article;

import dasturlash.uz.enums.ArticleStatus;
import jakarta.validation.constraints.NotBlank;

public record RequestChangeStatusArticle(
        @NotBlank(message = "article Id is not be empty") String articleId,
        ArticleStatus status) {
}
