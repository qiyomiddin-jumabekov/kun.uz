package dasturlash.uz.service;

import dasturlash.uz.dto.article.RequestForCreateAndUpdateArticle;
import dasturlash.uz.dto.article.ResponseDtoForArticle;
import dasturlash.uz.entity.Article;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleSectionService articleSectionService;

    public ResponseDtoForArticle createArticle(@Valid RequestForCreateAndUpdateArticle request) {
        // get moderator id with ContextHolder
        Integer moderatorId = SecurityUtil.getCurrentUserId();

        // Create Article
        Article article = new Article();
        article.setContent(request.getContent());
        article.setTitle(request.getTitle());
        article.setDescription(request.getDescription());
        article.setImageId(request.getImageId());
        article.setRegionId(request.getRegionId());
        article.setModeratorId(moderatorId);
        article.setReadTime(3.5);
        article.setSharedCount(0);
        article.setViewCount(0);
        article.setStatus(ArticleStatus.NOT_PUBLISHED);
        article.setVisible(Visible.INACTIVE);
        articleRepository.save(article);

        // create Article-Category
        articleCategoryService.createArticleCategory(article.getId(), request.getCategoryIds());

        // create Article-Section
        articleSectionService.createArticleCategory(article.getId(), request.getSectionIds());

        return toDto(article);
    }


    public ResponseDtoForArticle toDto(Article article) {
        ResponseDtoForArticle dto = new ResponseDtoForArticle();
        dto.setTitle(article.getTitle());
        dto.setDescription(article.getDescription());
        dto.setContent(article.getContent());
        dto.setImageId(article.getImageId());
        dto.setRegionId(article.getRegionId());
        dto.setModeratorId(article.getModeratorId());
        dto.setReadTime(article.getReadTime());
        dto.setCreatedAt(article.getCreatedAt());
        dto.setArticleId(article.getId());
        return dto;
    }

    public ResponseDtoForArticle updateArticle(String articleId, RequestForCreateAndUpdateArticle request) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article Not Found"));

        Integer moderatorId = SecurityUtil.getCurrentUserId();

        article.setContent(request.getContent());
        article.setTitle(request.getTitle());
        article.setDescription(request.getDescription());
        article.setImageId(request.getImageId());
        article.setRegionId(request.getRegionId());
        article.setModeratorId(moderatorId);
        article.setReadTime(3.5);
        article.setSharedCount(0);
        article.setViewCount(0);
        article.setStatus(ArticleStatus.NOT_PUBLISHED);
        article.setVisible(Visible.INACTIVE);
        articleRepository.save(article);

        // Update Article-Category
        articleCategoryService.updateArticleCategory(article.getId(), request.getCategoryIds());

        // Update Article-Section
        articleSectionService.updateArticleSection(article.getId(), request.getSectionIds());

        return toDto(article);
    }
}
