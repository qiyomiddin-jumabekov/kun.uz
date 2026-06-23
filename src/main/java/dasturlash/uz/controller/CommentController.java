package dasturlash.uz.controller;

import dasturlash.uz.dto.article.CreateCommentRequest;
import dasturlash.uz.dto.article.UpdateCommentRequest;
import dasturlash.uz.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article-comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody @Valid CreateCommentRequest request) {
        return ResponseEntity.ok(commentService.createComment(request));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateComment(@RequestBody @Valid UpdateCommentRequest request) {
        return ResponseEntity.ok(commentService.updateComment(request));
    }
}
