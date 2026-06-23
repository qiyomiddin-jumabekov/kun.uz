package dasturlash.uz.projections.article;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface CommentShortInfo {
    Integer getId();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    String getContent();

    Integer getProfileId();

    String getProfileName();

    String getProfileSurname();

}
