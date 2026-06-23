package dasturlash.uz.dto.article;

import dasturlash.uz.enums.EmotionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleEmotionRequest {
    @NotBlank(message = "article id is not be empty")
    private String articleId;
    @NotNull(message = "emotion type is not be null")
    private EmotionType emotionType;
}
