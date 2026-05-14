package dasturlash.uz.controller;

import dasturlash.uz.dto.region.RequestForRegion;
import dasturlash.uz.service.RegionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/region")
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
}
