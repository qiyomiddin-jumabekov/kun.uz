package dasturlash.uz.repository;

import dasturlash.uz.entity.Profile;
import dasturlash.uz.projections.StudentShortInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {


    boolean existsByUsername(String username);


    @Query("select p.id as profId," +
            " p.name as profName," +
            " p.surname as profSurname," +
            " p.status as profStatus" +
            " from Profile p" +
            " where p.visible = dasturlash.uz.enums.Visible.ACTIVE")
    public Page<StudentShortInfo> getAllProfilesByPagination(Pageable page);
}
