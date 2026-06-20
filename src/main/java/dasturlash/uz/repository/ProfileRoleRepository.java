package dasturlash.uz.repository;

import dasturlash.uz.entity.ProfileRole;
import dasturlash.uz.enums.ProfileRoles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRoleRepository extends CrudRepository<ProfileRole, Integer> {

    @Query("select p.role from ProfileRole p" +
            " where p.profileId = :id")
    List<ProfileRoles> getRolesByProfileId(@Param("id") Integer id);
}
