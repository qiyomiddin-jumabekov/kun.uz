package dasturlash.uz.controller;

import dasturlash.uz.dto.tag.RequestForCreateTag;
import dasturlash.uz.projections.tag.ResponseDtoForTag;
import dasturlash.uz.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<String> createTag(@RequestBody @Valid RequestForCreateTag request) {
        return ResponseEntity.ok(tagService.createTag(request));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ResponseDtoForTag>> listTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }
}
