package dasturlash.uz.controller;

import dasturlash.uz.dto.category.RequestForCategory;
import dasturlash.uz.dto.category.ResponseDtoForCategoryLang;
import dasturlash.uz.entity.Category;
import dasturlash.uz.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody @Valid RequestForCategory request) {
        return ResponseEntity.ok().body(categoryService.createCategory(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id,
                                         @RequestBody @Valid RequestForCategory request) {
        return ResponseEntity.ok().body(categoryService.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable @Min(value = 1, message = "Delete Id Min = 1 ") Integer id) {
        return ResponseEntity.ok().body(categoryService.deleteCategoryById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Category>> getAllCategoriesByPagination(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok().body(categoryService.getAllCategoryByPagination(page, size));
    }

    @GetMapping("/lang")
    public ResponseEntity<Page<ResponseDtoForCategoryLang>> getAllCategoriesByPaginationWithLang(
            @RequestHeader("Accept-Language") String lang,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return ResponseEntity.ok().body(categoryService.getCategoriesByLang(lang, page, size));
    }
}
