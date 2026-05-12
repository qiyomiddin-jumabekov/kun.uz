package dasturlash.uz.repository;

import dasturlash.uz.entity.Profile;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {


    boolean existsByUsername(String username);
}
