package dasturlash.uz.specification;

import dasturlash.uz.dto.article.FilterDto;
import dasturlash.uz.entity.Article;
import dasturlash.uz.enums.ArticleStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ArticleSpecification {

    public static Specification<Article> filter(FilterDto filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("status"), ArticleStatus.PUBLISHED));

            if (filter.title() != null && !filter.title().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + filter.title().toLowerCase() + "%"));
            }

            if (filter.regionId() != null) {
                predicates.add(cb.equal(root.get("regionId"), filter.regionId()));
            }

            if (filter.categoryId() != null) {
                predicates.add(cb.equal(root.get("categoryId"), filter.categoryId()));
            }

            if (filter.sectionId() != null) {
                predicates.add(cb.equal(root.get("sectionId"), filter.sectionId()));
            }

            if (filter.dateFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("publishedAt"), filter.dateFrom()));
            }

            if (filter.dateTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("publishedAt"), filter.dateTo()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
