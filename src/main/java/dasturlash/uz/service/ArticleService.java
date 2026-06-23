package dasturlash.uz.service;

import dasturlash.uz.dto.article.*;
import dasturlash.uz.entity.Article;
import dasturlash.uz.enums.ArticleStatus;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.article.ArticleShortInfo;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleCategory;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleSection;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleTag;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.specification.ArticleSpecification;
import dasturlash.uz.util.SecurityUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String deleteArticleById(String id) {
        int result = articleRepository.deleteArticle(id, Visible.INACTIVE);
        if (result < 0) {
            throw new IllegalArgumentException("Article Not Found");
        }
        return "Article Deleted Successfully";
    }

    public String changeArticleStatus(RequestChangeStatusArticle request) {
        int result = articleRepository.changeArticleStatusById(request.articleId(), request.status());
        if (result < 0) {
            throw new IllegalArgumentException("Article Not Found");
        }
        return "Article Status Changed Successfully";
    }

    public Page<ArticleShortInfoForArticleSection> getArticlesBySectionId(int page, int size, RequestGetNArticlesBySectionId request) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleRepository.getArticlesBySectionId(request.sectionId(), pageable);
    }

    public Page<ResponseDtoForArticle> getLast12ArticlesExceptIds(int page, int size, RequestLast12Except request) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> list = articleRepository.getLast12ArticlesExceptIds(request.idList(), pageable);
        return list.map(this::toDto);
    }

    public Page<ArticleShortInfoForArticleCategory> getArticlesByCategoryId(int page, int size, RequestGetNArticlesByCategoryId request) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleRepository.getArticlesByCategoryId(request.categoryId(), pageable);
    }

    public Page<ResponseDtoForArticle> getArticlesByRegionId(int page, int size, RequestGetArticlesByRegionId request) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Article> list = articleRepository.getArticlesByRegionId(request.regionId(), pageable);
        return list.map(this::toDto);
    }

    public ResponseDtoForArticle getArticleById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Article id is null or empty");
        }
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article Not Found"));
        return toDto(article);
    }

    public Page<ArticleShortInfoForArticleTag> getArticlesByTagName(int page, int size, RequestGetArticlesByTagName request) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleRepository.getArticlesByTagName(request.name(), pageable);
    }

    public List<ArticleShortInfo> getLast4ArticlesBySectionId(Integer sectionId, String articleId) {
        return articleRepository.getLast4ArticlesBySectionId(sectionId, articleId);
    }

    public List<ArticleShortInfo> getTop4MostReadArticlesExceptId(String articleId) {
        return articleRepository.getTop4MostReadArticlesExceptId(articleId);
    }

    @Transactional
    public Integer increaseArticleViewCount(String articleId) {
        boolean exists = articleRepository.existsById(articleId);
        if (!exists) {
            throw new IllegalArgumentException("Article Not Found");
        }
        articleRepository.increaseViewCount(articleId);
        return articleRepository.getArticleViewCount(articleId);
    }

    @Transactional
    public Integer increaseArticleShareCount(String articleId) {
        boolean exists = articleRepository.existsById(articleId);
        if (!exists) {
            throw new IllegalArgumentException("Article Not Found");
        }
        articleRepository.increaseShareCount(articleId);
        return articleRepository.getArticleShareCount(articleId);
    }

    public Page<ResponseDtoForArticle> filterDto(FilterDto filterDto, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Specification<Article> spec = ArticleSpecification.filter(filterDto);
        Page<Article> list = articleRepository.findAll(spec, pageable);
        return list.map(this::toDto);
    }
}
