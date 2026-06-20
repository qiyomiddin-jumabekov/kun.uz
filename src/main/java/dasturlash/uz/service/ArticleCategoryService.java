package dasturlash.uz.service;

import dasturlash.uz.entity.ArticleCategory;
import dasturlash.uz.repository.ArticleCategoryRepository;
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
}
