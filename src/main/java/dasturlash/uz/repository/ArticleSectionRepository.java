package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleCategory;
import dasturlash.uz.entity.ArticleSection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleSectionRepository extends CrudRepository<ArticleSection, Integer> {
    List<ArticleSection> findAllBysectionId(String id);

    List<ArticleSection> findAllByArticleId(String articleId);
}
