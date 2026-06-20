package dasturlash.uz.controller;

import dasturlash.uz.dto.article.RequestForCreateAndUpdateArticle;
import dasturlash.uz.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
