package dasturlash.uz.dto.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentRequest {
    @Positive(message = "Comment Id is not be negative nums")
    private Integer commentId;
    @NotBlank(message = "Content is not be empty")
    private String content;
    @NotBlank(message = "Article id is not be empty")
    private String articleId;
}
