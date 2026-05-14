package dasturlash.uz.repository;

import dasturlash.uz.entity.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegionRepository extends CrudRepository<Region, Integer>,
        PagingAndSortingRepository<Region, Integer> {


    boolean existsByKey(String key);
}
