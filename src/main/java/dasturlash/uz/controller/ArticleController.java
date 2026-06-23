package dasturlash.uz.controller;

import dasturlash.uz.dto.article.*;
import dasturlash.uz.projections.article.ArticleShortInfo;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleCategory;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleSection;
import dasturlash.uz.projections.article.ArticleShortInfoForArticleTag;
import dasturlash.uz.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody @Valid RequestForCreateAndUpdateArticle request) {
        return ResponseEntity.ok(articleService.createArticle(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArticle(
            @PathVariable String id,
            @RequestBody @Valid RequestForCreateAndUpdateArticle request) {
        return ResponseEntity.ok(articleService.updateArticle(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id) {
        return ResponseEntity.ok(articleService.deleteArticleById(id));
    }

    @PutMapping("/change-status")
    public ResponseEntity<?> changeArticleStatus(@RequestBody @Valid RequestChangeStatusArticle request) {
        return ResponseEntity.ok(articleService.changeArticleStatus(request));
    }

    @GetMapping("/get-by/sectionId")
    public ResponseEntity<Page<ArticleShortInfoForArticleSection>> getArticlesBySectionId(
            @RequestBody @Valid RequestGetNArticlesBySectionId request,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestParam(value = "page", defaultValue = "5") int page) {
        return ResponseEntity.ok(articleService.getArticlesBySectionId(page, size, request));
    }

    @PostMapping("/last12-except")
    public ResponseEntity<?> last12ArticlesExceptIds(
            @RequestBody @Valid RequestLast12Except request,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(articleService.getLast12ArticlesExceptIds(page, size, request));
    }

    @GetMapping("/get-by/category")
    public ResponseEntity<Page<ArticleShortInfoForArticleCategory>> getArticlesByCategoryId(
            @RequestBody @Valid RequestGetNArticlesByCategoryId request,
            @RequestParam(value = "size", defaultValue = "1") int size,
            @RequestParam(value = "page", defaultValue = "5") int page) {
        return ResponseEntity.ok(articleService.getArticlesByCategoryId(page, size, request));
    }

    @PostMapping("/get-by/region-id")
    public ResponseEntity<Page<ResponseDtoForArticle>> getArticlesByRegionId(
            @RequestBody @Valid RequestGetArticlesByRegionId request,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(articleService.getArticlesByRegionId(page, size, request));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDtoForArticle> getArticleById(
            @PathVariable String id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping("/get-by/tag-name")
    public ResponseEntity<Page<ArticleShortInfoForArticleTag>> getArticleByTagName(
            @RequestBody @Valid RequestGetArticlesByTagName request,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(articleService.getArticlesByTagName(page, size, request));
    }

    @GetMapping("/last4-by-section/{sectionId}/{articleId}")
    public ResponseEntity<List<ArticleShortInfo>> getLast4ArticlesBySectionId(
            @PathVariable Integer sectionId,
            @PathVariable String articleId) {
        return ResponseEntity.ok(articleService.getLast4ArticlesBySectionId(sectionId, articleId));
    }

    @GetMapping("/most-read/{articleId}")
    public ResponseEntity<List<ArticleShortInfo>> getTop4MostReadArticlesExceptId(
            @PathVariable String articleId) {
        return ResponseEntity.ok(articleService.getTop4MostReadArticlesExceptId(articleId));
    }

    @GetMapping("/increase-view-count/{articleId}")
    public ResponseEntity<Integer> increaseArticleViewCount(@PathVariable String articleId) {
        return ResponseEntity.ok(articleService.increaseArticleViewCount(articleId));
    }

    @GetMapping("/increase-share-count/{articleId}")
    public ResponseEntity<Integer> increaseArticleShareCount(@PathVariable String articleId) {
        return ResponseEntity.ok(articleService.increaseArticleShareCount(articleId));
    }
}
