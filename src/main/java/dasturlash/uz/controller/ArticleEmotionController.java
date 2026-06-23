package dasturlash.uz.controller;

import dasturlash.uz.dto.article.ArticleEmotionRequest;
import dasturlash.uz.service.ArticleEmotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article-emotion")
public class ArticleEmotionController {

    @Autowired
    private ArticleEmotionService articleEmotionService;

    @PostMapping("")
    public ResponseEntity<?> setEmotionForArticle(@RequestBody @Valid ArticleEmotionRequest request) {
        return ResponseEntity.ok(articleEmotionService.setEmotion(request));
    }
}
