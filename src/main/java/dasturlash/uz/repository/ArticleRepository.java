package dasturlash.uz.repository;

import dasturlash.uz.entity.Article;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.Visible;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, String> {

    @Transactional
    @Modifying
    @Query("UPDATE Article a set a.visible = :visible" +
            " where a.id = :id")
    int deleteArticle(@Param("id") String id, @Param("visible") Visible visible);

    @Modifying
    @Transactional
    @Query("update Article a  set a.status = :status" +
            " where a.id = :articleId")
    int changeArticleStatusById(@Param("articleId") String id, @Param("status") ArticleStatus status);
}
