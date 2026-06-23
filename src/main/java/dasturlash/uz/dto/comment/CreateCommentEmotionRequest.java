package dasturlash.uz.dto.comment;

import dasturlash.uz.enums.CommentReaction;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentEmotionRequest {
    @Positive(message = "Comment Id is not be negative num")
    private Integer commentId;
    @NotNull(message = "Reaction isn't be null")
    private CommentReaction reaction;
}
