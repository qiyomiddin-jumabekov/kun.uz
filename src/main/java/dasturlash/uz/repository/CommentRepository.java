package dasturlash.uz.repository;

import dasturlash.uz.entity.Comment;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.projections.comment.CommentShortInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
    @Transactional
    @Modifying
    @Query("update Comment  c set c.visible = :visible" +
            " where c.id = :id")
    int changeVisibleOfComment(@Param("id") Integer id, @Param("visible") Visible visible);

    @Query("select c.id as id, c.createdAt as createdAt, c.updatedAt as updatedAt,c.content as content," +
            " p.id as profileId, p.name as profileName, p.surname as profileSurname" +
            " from Comment c" +
            " inner join Profile p on p.id = c.profileId" +
            " and c.profileId = :profileId" +
            " and c.replyId = :commentId" +
            " order by c.createdAt desc ")
    List<CommentShortInfo> getRepliedComments(@Param("commentId") Integer cId, @Param("profileId") Integer id);


    @Query("select c.id as id,c.createdAt as createdAt,c.updatedAt as updateAt," +
            " c.content as content, p.id as profileId, p.name as profileName, p.surname as profileSurname" +
            " from Comment c" +
            " inner join Profile p on p.id = c.profileId" +
            " where c.articleId = ?1")
    List<CommentShortInfo> getCommentsByArticleId(String id);
}
