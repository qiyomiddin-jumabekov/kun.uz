package dasturlash.uz.controller;

import dasturlash.uz.dto.section.RequestForSection;
import dasturlash.uz.projections.section.ResponseProjectionSession;
import dasturlash.uz.projections.section.SectionProjectionPagination;
import dasturlash.uz.service.SectionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/section")
@Validated
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @PostMapping("")
    public ResponseEntity<String> createSection(@RequestBody @Valid RequestForSection request) {
        return ResponseEntity.ok().body(sectionService.addSection(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSection(@PathVariable Integer id, @RequestBody @Valid RequestForSection request) {
        return ResponseEntity.ok().body(sectionService.updateSectionById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSection(@PathVariable @Min(value = 1, message = "Delete Id has been positive") Integer id) {
        return ResponseEntity.ok().body(sectionService.deleteSectionById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<SectionProjectionPagination>> getAllSections(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(sectionService.getSectionList(page, size));
    }

    @GetMapping("/lang")
    public ResponseEntity<Page<ResponseProjectionSession>> getAllSectionsByLang(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestHeader("Accept-Language") String lang) {
        return ResponseEntity.ok(sectionService.getSectionsByLang(page, size, lang));
    }
}
