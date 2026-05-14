package dasturlash.uz.repository;

import dasturlash.uz.entity.Profile;
import dasturlash.uz.projections.ProfileShortInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {


    boolean existsByUsername(String username);


    @Query("select p.id as profId," +
            " p.name as profName," +
            " p.surname as profSurname," +
            " p.status as profStatus" +
            " from Profile p" +
            " where p.visible = dasturlash.uz.enums.Visible.ACTIVE")
    public Page<ProfileShortInfo> getAllProfilesByPagination(Pageable page);


    @Query("select p.id as profId, p.name as profName," +
            " p.surname as profSurname, p.status as profStatus" +
            " from Profile p" +
            " where (:query is null or" +
            " lower(p.name) like lower(concat('%',:query,'%')) or " +
            " lower(p.surname) like lower(concat('%',:query,'%')) or " +
            " lower(p.username) like lower(concat('%',:query,'%')))")
    public Page<ProfileShortInfo> getProfilesByFilter(Pageable page, @Param("query") String query);
}
