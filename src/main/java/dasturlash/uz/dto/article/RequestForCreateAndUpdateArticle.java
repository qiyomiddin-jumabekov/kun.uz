package dasturlash.uz.dto.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestForCreateAndUpdateArticle {
    @NotBlank(message = "Article title is not empty")
    private String title;
    @NotBlank(message = "Article description is not empty")
    private String description;
    @NotBlank(message = "Article content is not empty")
    private String content;
    @Positive(message = "Article imageId is not negative num")
    private Integer imageId;
    @Positive(message = "Article region id is not negative num")
    private Integer regionId;

    private List<@Positive(message = "Category Id is not negative num") Integer> categoryIds;
    private List<@Positive(message = "section Id is not negative num") Integer> sectionIds;
}
