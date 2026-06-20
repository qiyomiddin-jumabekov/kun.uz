package dasturlash.uz.service;

import dasturlash.uz.entity.ArticleCategory;
import dasturlash.uz.entity.ArticleSection;
import dasturlash.uz.repository.ArticleSectionRepository;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleSectionService {
    @Autowired
    private ArticleSectionRepository articleSectionRepository;

    public void createArticleCategory(String articleId, List<Integer> sectionIds) {
        sectionIds.forEach(id -> {
            ArticleSection articleSection = new ArticleSection();
            articleSection.setSectionId(id);
            articleSection.setArticleId(articleId);
            articleSectionRepository.save(articleSection);
        });
    }

    public void updateArticleSection(String articleId, List<Integer> sectionIds) {

        // 1. Eski bog'lanishlarni o'chirish
        List<ArticleSection> existing = articleSectionRepository.findAllByArticleId((articleId));
        articleSectionRepository.deleteAll(existing);

        // 2. Yangi bog'lanishlarni yaratish
        List<ArticleSection> newEntities = sectionIds.stream()
                .map(sectionId -> {
                    ArticleSection ac = new ArticleSection();
                    ac.setArticleId(articleId);
                    ac.setSectionId(sectionId);
                    return ac;
                })
                .toList();

        articleSectionRepository.saveAll(newEntities);
    }
}
