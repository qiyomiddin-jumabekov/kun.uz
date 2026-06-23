package dasturlash.uz.controller;

import dasturlash.uz.dto.comment.CreateCommentEmotionRequest;
import dasturlash.uz.service.CommentEmotionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment-emotion")
public class CommentEmotionController {

    @Autowired
    private CommentEmotionService commentEmotionService;

    @PostMapping("/create")
    public ResponseEntity<?> createCommentEmotion(@RequestBody @Valid CreateCommentEmotionRequest request) {
        return ResponseEntity.ok(commentEmotionService.createCommentEmotion(request));
    }
}
