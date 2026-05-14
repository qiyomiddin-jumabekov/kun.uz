package dasturlash.uz.repository;

import dasturlash.uz.entity.Region;
import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region, Integer> {


    boolean existsByKey(String key);
}
