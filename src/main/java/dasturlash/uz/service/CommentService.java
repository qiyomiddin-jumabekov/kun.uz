package dasturlash.uz.service;

import dasturlash.uz.dto.article.CreateCommentRequest;
import dasturlash.uz.entity.Comment;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.CommentRepository;
import dasturlash.uz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public String createComment(CreateCommentRequest request) {
        boolean exist = articleRepository.existsById(request.getArticleId());
        if (!exist) {
            throw new IllegalArgumentException("Article does not exist");
        }
        if (request.getReplyId() != null) {
            boolean existReply = commentRepository.existsById(request.getReplyId());
            if (!existReply) {
                throw new IllegalArgumentException("Reply Comment does not exist");
            }
        }
        Integer currentProfileId = SecurityUtil.getCurrentUserId();

        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setArticleId(request.getArticleId());
        comment.setProfileId(currentProfileId);
        comment.setReplyId(request.getReplyId());
        comment.setVisible(Visible.ACTIVE);
        commentRepository.save(comment);
        return "Comment Successfully created";
    }
}
