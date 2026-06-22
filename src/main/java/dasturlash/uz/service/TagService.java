package dasturlash.uz.service;

import dasturlash.uz.dto.tag.RequestForCreateTag;
import dasturlash.uz.entity.Tag;
import dasturlash.uz.projections.tag.ResponseDtoForTag;
import dasturlash.uz.repository.ArticleRepository;
import dasturlash.uz.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public String createTag(RequestForCreateTag request) {
        boolean exist = articleRepository.existsById(request.articleId());
        if (!exist) {
            throw new IllegalArgumentException("Article not found");
        }
        boolean found = tagRepository.existsByNameAndArticleId(request.name(), request.articleId());
        if (found) {
            throw new IllegalArgumentException("Tag name already exists");
        }
        Tag tag = new Tag();
        tag.setArticleId(request.articleId());
        tag.setName(request.name());
        tagRepository.save(tag);
        return "Tag successfully created";
    }

    public List<ResponseDtoForTag> getAllTags() {
        return tagRepository.findAllBy();
    }
}
