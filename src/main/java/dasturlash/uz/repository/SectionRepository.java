package dasturlash.uz.repository;

import dasturlash.uz.entity.Section;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.section.SectionProjectionPagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends JpaRepository<Section, Integer> {


    boolean existsByKey(String key);


    @Query("select s.id as seId, s.nameRu as seNameRu, s.nameUz as seNameUz," +
            " s.nameEn as seNameEn, s.key as seKey, s.visible as seVisible," +
            " s.createdAt as seCreatedDate" +
            " from Section s" +
            " where s.visible = :visible")
    Page<SectionProjectionPagination> findAllByVisible(@Param("visible") Visible visible, Pageable pageable);
}
