package dasturlash.uz.dto.article;

import jakarta.validation.constraints.Positive;

public record RequestGetArticlesByRegionId(
        @Positive(message = "Region Id is not be negative num") Integer regionId) {
}
