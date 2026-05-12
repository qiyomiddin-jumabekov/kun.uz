package dasturlash.uz.projections;

import dasturlash.uz.enums.Status;

public interface StudentShortInfo {
    Integer getProfId();

    String getProfName();

    String getProfSurname();

    Status getProfStatus();
}
