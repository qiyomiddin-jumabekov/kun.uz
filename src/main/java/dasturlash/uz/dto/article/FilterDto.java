package dasturlash.uz.dto.article;

import java.time.LocalDateTime;

public record FilterDto(
        String title,
        Integer regionId,
        Integer categoryId,
        Integer sectionId,
        LocalDateTime dateFrom,
        LocalDateTime dateTo) {
}
