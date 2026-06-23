package dasturlash.uz.repository;

import dasturlash.uz.entity.Article;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.article.ArticleShortInfo;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleCategory;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleSection;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleTag;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

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


    @Query("select a.id as articleId, a.title as title, a.content as content," +
            " a.createdAt as createdAt, asec.sectionId as sectionId" +
            " from Article a" +
            " inner join ArticleSection as asec on asec.articleId = a.id" +
            " where asec.sectionId = :sectionId" +
            " and a.status = dasturlash.uz.enums.ArticleStatus.PUBLISHED" +
            " order by a.createdAt desc ")
    Page<ArticleShortInfoForArticleSection> getArticlesBySectionId(@Param("sectionId") Integer integer, Pageable pageable);


    @Query("select a from Article a " +
            " where a.status = dasturlash.uz.enums.ArticleStatus.PUBLISHED" +
            " and a.id not in :idList" +
            " order by a.createdAt desc")
    Page<Article> getLast12ArticlesExceptIds(
            @Param("idList") List<String> idList,
            Pageable pageable
    );

    @Query("select a.id as articleId, a.title as title, a.content as content," +
            " a.createdAt as createdAt, acat.categoryId as categoryId" +
            " from Article a" +
            " inner join ArticleCategory as acat on acat.articleId = a.id" +
            " where acat.categoryId = :categoryId" +
            " and a.status = dasturlash.uz.enums.ArticleStatus.PUBLISHED" +
            " order by a.createdAt desc ")
    Page<ArticleShortInfoForArticleCategory> getArticlesByCategoryId(@Param("categoryId") Integer integer, Pageable pageable);

    Page<Article> getArticlesByRegionId(Integer integer, Pageable pageable);


    @Query("select a.id as id,a.title as title,t.name as name  " +
            " from Article a" +
            " inner join Tag t on t.articleId = a.id" +
            " where t.name = :tagName")
    Page<ArticleShortInfoForArticleTag> getArticlesByTagName(@Param("tagName") String name, Pageable pageable);


    @Query("select a.id as id,a.title as title," +
            " a.content as content,a.createdAt as createdAt, a.visible as visible " +
            " from Article a" +
            " inner join ArticleSection asec on asec.articleId = a.id" +
            " where asec.sectionId = :sectionId" +
            " and a.id <> :articleId" +
            " and a.status = dasturlash.uz.enums.ArticleStatus.PUBLISHED" +
            " order by a.createdAt desc" +
            " limit 4")
    List<ArticleShortInfo> getLast4ArticlesBySectionId(
            @Param("sectionId") Integer sectionId,
            @Param("articleId") String articleId);

    @Query("select a.id as id,a.title as title,a.content as content," +
            " a.createdAt as createdAt,a.visible as visible" +
            " from Article a " +
            " where a.id <> ?1" +
            " and a.status = dasturlash.uz.enums.ArticleStatus.PUBLISHED" +
            " order by a.viewCount desc " +
            " limit 4")
    List<ArticleShortInfo> getTop4MostReadArticlesExceptId(String id);

    @Modifying
    @Query("update Article a set a.viewCount = a.viewCount + 1 " +
            " where a.id = ?1")
    void increaseViewCount(String articleId);

    @Query("select a.viewCount from Article a" +
            " where a.id = ?1")
    Integer getArticleViewCount(String articleId);

    @Modifying
    @Query("update Article a set a.sharedCount = a.sharedCount + 1" +
            " where a.id = ?1")
    void increaseShareCount(String articleId);

    @Query("select a.sharedCount from Article a" +
            " where a.id = ?1")
    Integer getArticleShareCount(String articleId);
}
