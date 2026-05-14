package dasturlash.uz.repository;

import dasturlash.uz.entity.Section;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Integer> {


    boolean existsByKey(String key);
}
