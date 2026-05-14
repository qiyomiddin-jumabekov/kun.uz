package dasturlash.uz.controller;

import dasturlash.uz.dto.section.RequestForSection;
import dasturlash.uz.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/section")
public class SectionController {
    @Autowired
    private SectionService sectionService;

    @PostMapping("")
    public ResponseEntity<String> createSection(@RequestBody @Valid RequestForSection request) {
        return ResponseEntity.ok().body(sectionService.addSection(request));
    }
}
