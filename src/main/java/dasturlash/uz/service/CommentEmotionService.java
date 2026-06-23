package dasturlash.uz.service;

import dasturlash.uz.dto.comment.CreateCommentEmotionRequest;
import dasturlash.uz.entity.CommentEmotion;
import dasturlash.uz.repository.CommentEmotionRepository;
import dasturlash.uz.repository.CommentRepository;
import dasturlash.uz.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentEmotionService {
    @Autowired
    private CommentEmotionRepository commentEmotionRepository;

    @Autowired
    private CommentRepository commentRepository;

    public String createCommentEmotion(CreateCommentEmotionRequest request) {
        boolean existComment = commentRepository.existsById(request.getCommentId());
        if (!existComment) {
            throw new IllegalArgumentException("Comment Not Found");
        }

        Integer currentProfileId = SecurityUtil.getCurrentUserId();
        Optional<CommentEmotion> optional = commentEmotionRepository
                .findByProfileIdAndCommentId(currentProfileId, request.getCommentId());
        if (optional.isEmpty()) {
            CommentEmotion commentEmotion = new CommentEmotion();
            commentEmotion.setCommentId(request.getCommentId());
            commentEmotion.setProfileId(currentProfileId);
            commentEmotion.setReaction(request.getReaction());
            commentEmotionRepository.save(commentEmotion);
            return "Current Emotion : " + commentEmotion.getReaction().toString();
        }

        CommentEmotion existing = optional.get();

        if (existing.getReaction().equals(request.getReaction())) {
            commentEmotionRepository.delete(existing);
            return "Current Emotion removed";
        }

        existing.setReaction(request.getReaction());
        commentEmotionRepository.save(existing);
        return "Current Emotion :" + existing.getReaction().toString();
    }
}
