package dasturlash.uz.repository;

import dasturlash.uz.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface AuthorizationRepository extends CrudRepository<Profile, Integer> {
}
