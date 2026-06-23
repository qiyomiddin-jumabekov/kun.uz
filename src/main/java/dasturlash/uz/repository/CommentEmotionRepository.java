package dasturlash.uz.repository;

import dasturlash.uz.entity.CommentEmotion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CommentEmotionRepository extends CrudRepository<CommentEmotion, Integer> {

    Optional<CommentEmotion> findByProfileIdAndCommentId(Integer currentProfileId, Integer commentId);
}
