package dasturlash.uz.projections;

import dasturlash.uz.enums.Status;

public interface ProfileShortInfo {
    Integer getProfId();

    String getProfName();

    String getProfSurname();

    Status getProfStatus();
}
