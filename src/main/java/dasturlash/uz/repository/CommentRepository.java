package dasturlash.uz.repository;

import dasturlash.uz.entity.Comment;
import dasturlash.uz.enums.Visible;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    @Transactional
    @Modifying
    @Query("update Comment  c set c.visible = :visible" +
            " where c.id = :id")
    int changeVisibleOfComment(@Param("id") Integer id, @Param("visible") Visible visible);
}
