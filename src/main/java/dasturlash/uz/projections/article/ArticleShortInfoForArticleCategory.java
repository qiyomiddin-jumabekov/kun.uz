package dasturlash.uz.projections.article;

import java.time.LocalDateTime;

public interface ArticleShortInfoForArticleCategory {
    String getArticleId();

    String getTitle();

    String getContent();

    LocalDateTime getCreatedAt();

    Integer getCategoryId();

}
