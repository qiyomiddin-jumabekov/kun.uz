package dasturlash.uz.service;

import dasturlash.uz.entity.ArticleCategory;
import dasturlash.uz.repository.ArticleCategoryRepository;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCategoryService {
    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    public void createArticleCategory(String articleId, List<Integer> categoryId) {
        categoryId.forEach(id -> {
            ArticleCategory articleCategory = new ArticleCategory();
            articleCategory.setCategoryId(id);
            articleCategory.setArticleId(articleId);
            articleCategoryRepository.save(articleCategory);
        });
    }

    public void updateArticleCategory(String articleId, List<Integer> categoryIds) {
        // 1. Eski bog'lanishlarni o'chirish
        List<ArticleCategory> existing = articleCategoryRepository.findAllByArticleId(articleId);
        articleCategoryRepository.deleteAll(existing);

        // 2. Yangi bog'lanishlarni yaratish
        List<ArticleCategory> newEntities = categoryIds.stream()
                .map(categoryId -> {
                    ArticleCategory ac = new ArticleCategory();
                    ac.setArticleId(articleId);
                    ac.setCategoryId(categoryId);
                    return ac;
                })
                .toList();

        articleCategoryRepository.saveAll(newEntities);
    }
}
