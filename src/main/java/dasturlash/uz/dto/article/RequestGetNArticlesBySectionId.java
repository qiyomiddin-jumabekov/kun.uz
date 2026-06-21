package dasturlash.uz.dto.article;

import jakarta.validation.constraints.Positive;

public record RequestGetNArticlesBySectionId(
        @Positive(message = "SectionId is not be negative num") Integer sectionId) {
}
