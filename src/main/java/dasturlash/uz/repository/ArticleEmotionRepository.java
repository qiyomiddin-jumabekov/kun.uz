package dasturlash.uz.repository;

import dasturlash.uz.entity.ArticleEmotion;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleEmotionRepository extends CrudRepository<ArticleEmotion, Integer> {


    Optional<ArticleEmotion> findByProfileIdAndArticleId(Integer profileId, String articleId);
}
