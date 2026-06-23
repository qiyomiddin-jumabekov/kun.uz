package dasturlash.uz.service;

import dasturlash.uz.dto.article.CreateCommentRequest;
import dasturlash.uz.dto.article.UpdateCommentRequest;
import dasturlash.uz.entity.Comment;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.article.CommentShortInfo;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.CommentRepository;
import dasturlash.uz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String updateComment(UpdateCommentRequest request) {

        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        Integer currentProfileId = SecurityUtil.getCurrentUserId();
        if (!comment.getProfileId().equals(currentProfileId)) {
            throw new IllegalArgumentException("You can only update your own comment");
        }

        if (!comment.getArticleId().equals(request.getArticleId())) {
            throw new IllegalArgumentException("Article id mismatch");
        }

        comment.setContent(request.getContent());
        commentRepository.save(comment);

        return "Comment updated successfully";
    }

    public boolean deleteComment(Integer commentId) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        Integer currentProfileId = SecurityUtil.getCurrentUserId();
        if (!comment.getProfileId().equals(currentProfileId)) {
            throw new IllegalArgumentException("You can only delete your own comment");
        }
        int result = commentRepository.changeVisibleOfComment(comment.getId(), Visible.INACTIVE);
        return result >= 0;
    }

    public List<CommentShortInfo> getRepliedCommentsByCommentId(Integer commentId) {
        Integer currentProfileId = SecurityUtil.getCurrentUserId();
        return commentRepository.getRepliedComments(commentId,currentProfileId);
    }
}
