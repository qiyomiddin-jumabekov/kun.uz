package dasturlash.uz.dto.article;

import jakarta.validation.constraints.Positive;

public record RequestGetNArticlesByCategoryId(
        @Positive(message = "Category Id  is not be negative num") Integer categoryId) {
}
