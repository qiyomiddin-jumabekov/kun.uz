package dasturlash.uz.projections.section;

import dasturlash.uz.enums.Visible;

import java.time.LocalDateTime;

public interface SectionProjectionPagination {
    Integer getSeId();

    String getSeKey();

    String getSeNameUz();

    String getSeNameRu();

    String getSeNameEn();

    Visible getSeVisible();

    LocalDateTime getSeCreatedDate();

}
