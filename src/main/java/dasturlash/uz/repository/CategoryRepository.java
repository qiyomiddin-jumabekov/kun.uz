package dasturlash.uz.repository;

import dasturlash.uz.entity.Category;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.category.ProjectionCategoryLang;
import dasturlash.uz.projections.region.ProjectionRegionLang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends CrudRepository<Category, Integer>,
        PagingAndSortingRepository<Category, Integer> {

    boolean existsByKey(String key);

    @Query("select c.id as catId, c.key as catKey," +
            " case :lang" +
            " when 'uz' then c.nameUz" +
            " when 'ru' then c.nameRu" +
            " when 'en' then c.nameEn" +
            " else c.nameUz" +
            " end as catName" +
            " from Category c" +
            " where c.visible = :visible")
    public Page<ProjectionCategoryLang> getCategoriesByLang(
            @Param("lang") String lang,
            @Param("visible") Visible visible,
            Pageable pageable);
}
