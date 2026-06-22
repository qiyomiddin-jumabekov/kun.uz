package dasturlash.uz.repository;

import dasturlash.uz.entity.Tag;
import dasturlash.uz.projections.tag.ResponseDtoForTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {

    List<ResponseDtoForTag> findAllBy();
}
