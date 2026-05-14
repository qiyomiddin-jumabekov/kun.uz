package dasturlash.uz.controller;

import dasturlash.uz.dto.region.RequestForRegion;
import dasturlash.uz.dto.region.ResponseDtoForRegionLang;
import dasturlash.uz.entity.Region;
import dasturlash.uz.projections.ProjectionRegionLang;
import dasturlash.uz.service.RegionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/region")
@Validated
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody @Valid RequestForRegion request) {
        return ResponseEntity.ok().body(regionService.createRegion(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,
                                         @RequestBody @Valid RequestForRegion request) {
        return ResponseEntity.ok().body(regionService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Min(value = 1, message = "Delete Id Min = 1 ") Integer id) {
        return ResponseEntity.ok().body(regionService.deleteRegionById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Region>> getAllRegionsByPagination(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok().body(regionService.getAllRegionsPagination(page, size));
    }

    @GetMapping("/lang")
    public ResponseEntity<Page<ResponseDtoForRegionLang>> getAllRegionsByPagination(
            @RequestHeader("Accept-Language") String lang,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ResponseEntity.ok().body(regionService.getRegionsByLang(lang, page, size));
    }
}
