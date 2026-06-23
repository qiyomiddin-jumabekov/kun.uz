package dasturlash.uz.service;

import dasturlash.uz.dto.article.ArticleEmotionRequest;
import dasturlash.uz.entity.ArticleEmotion;
import dasturlash.uz.repository.ArticleEmotionRepository;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.util.SecurityUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleEmotionService {
    @Autowired
    private ArticleEmotionRepository articleEmotionRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public String setEmotion(ArticleEmotionRequest request) {
        boolean exist = articleRepository.existsById(request.getArticleId());
        if (!exist) {
            throw new IllegalArgumentException("article not found");
        }
        Integer currentProfileId = SecurityUtil.getCurrentUserId();

        Optional<ArticleEmotion> optional = articleEmotionRepository
                .findByProfileIdAndArticleId(currentProfileId, request.getArticleId());

        if (optional.isEmpty()) {
            ArticleEmotion articleEmotion = new ArticleEmotion();
            articleEmotion.setProfileId(currentProfileId);
            articleEmotion.setArticleId(request.getArticleId());
            articleEmotion.setEmotionType(request.getEmotionType());
            articleEmotionRepository.save(articleEmotion);
            return "Current Emotion : " + request.getEmotionType().toString();
        }

        ArticleEmotion existing = optional.get();

        if (existing.getEmotionType().equals(request.getEmotionType())) {
            articleEmotionRepository.delete(existing);
            return "Current Emotion Removed";
        }

        existing.setEmotionType(request.getEmotionType());
        articleEmotionRepository.save(existing);
        return "Current Emotion " + existing.getEmotionType().toString();
    }
}
