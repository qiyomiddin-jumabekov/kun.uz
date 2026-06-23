package dasturlash.uz.dto.article;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    @NotBlank(message = "Comment content is not be empty")
    private String content;
    @NotBlank(message = "Article id is not be empty")
    private String articleId;
    private Integer replyId;
}
