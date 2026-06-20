package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleCategoryRepository extends CrudRepository<ArticleCategory, Integer> {

    List<ArticleCategory> findAllByArticleId(String articleId);
}
