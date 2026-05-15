package dasturlash.uz.repository;

import dasturlash.uz.entity.Section;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.section.ResponseProjectionSession;
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

    @Query("select s.id as sectionId, s.key as sectionKey," +
            " case :lang" +
            " when 'uz' then s.nameUz" +
            " when 'ru' then s.nameRu" +
            " when 'en' then s.nameEn" +
            " else s.nameUz " +
            " end as sectionName" +
            " from Section s" +
            " where s.visible = dasturlash.uz.enums.Visible.ACTIVE")
    Page<ResponseProjectionSession> getSectionsByLang(@Param("lang") String lang, Pageable pageable);
}
