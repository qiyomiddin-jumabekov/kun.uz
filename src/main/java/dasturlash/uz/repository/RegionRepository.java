package dasturlash.uz.repository;

import dasturlash.uz.entity.Region;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.ProjectionRegionLang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface RegionRepository extends CrudRepository<Region, Integer>,
        PagingAndSortingRepository<Region, Integer> {


    boolean existsByKey(String key);



    @Query("select r.id as regId, r.key as regKey," +
            " case :lang" +
            " when 'uz' then r.nameUz" +
            " when 'ru' then r.nameRu" +
            " when 'en' then r.nameEn" +
            " else r.nameUz" +
            " end as regName" +
            " from Region r" +
            " where r.visible = :visible")
    public Page<ProjectionRegionLang> getRegionsByLang(
            @Param("lang") String lang,
            @Param("visible") Visible visible,
            Pageable pageable);
}
