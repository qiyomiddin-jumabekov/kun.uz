package dasturlash.uz.projections.article;

import dasturlash.uz.enums.Visible;

import java.time.LocalDateTime;

public interface ArticleShortInfo {
    String getId();

    String getTitle();

    String getContent();

    LocalDateTime getCreatedAt();

    Visible getVisible();

}
