package dasturlash.uz.service;

import dasturlash.uz.entity.ArticleSection;
import dasturlash.uz.repository.ArticleSectionRepository;
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
}
