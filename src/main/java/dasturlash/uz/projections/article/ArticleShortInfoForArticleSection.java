package dasturlash.uz.projections.article;

import java.time.LocalDateTime;

public interface ArticleShortInfoForArticleSection {
    String getArticleId();

    String getTitle();

    String getContent();

    LocalDateTime getCreatedAt();

    Integer getSectionId();

}
