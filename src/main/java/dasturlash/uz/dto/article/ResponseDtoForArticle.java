package dasturlash.uz.dto.article;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseDtoForArticle {
    private String articleId;
    private String title;
    private String description;
    private String content;
    private Integer imageId;
    private Integer regionId;
    private Integer moderatorId;
    private Double readTime;
    private LocalDateTime createdAt;
}
