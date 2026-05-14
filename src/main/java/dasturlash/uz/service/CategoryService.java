package dasturlash.uz.service;

import dasturlash.uz.dto.category.RequestForCategory;
import dasturlash.uz.dto.category.ResponseDtoForCategoryLang;
import dasturlash.uz.entity.Category;
import dasturlash.uz.enums.Visible;
import dasturlash.uz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public String createCategory(RequestForCategory request) {
        if (categoryRepository.existsByKey(request.key())) {
            return "Category already exists by Category Key";
        }
        Category category = new Category();
        category.setKey(request.key());
        category.setNameUz(request.nameUz());
        category.setNameRu(request.nameRu());
        category.setNameEn(request.nameEn());
        category.setOrderNumber(request.orderNumber());
        categoryRepository.save(category);
        return "Category successfully created";
    }

    public String updateById(Integer categoryId, RequestForCategory request) {
        Category category = findById(categoryId);
        category.setOrderNumber(request.orderNumber());
        category.setKey(request.key());
        category.setNameUz(request.nameUz());
        category.setNameRu(request.nameRu());
        category.setNameEn(request.nameEn());
        categoryRepository.save(category);
        return "Category successfully updated";
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public String deleteCategoryById(Integer id) {
        Category category = findById(id);
        category.setVisible(Visible.INACTIVE);
        categoryRepository.save(category);
        return "Category successfully deleted";
    }

    public Page<Category> getAllCategoryByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("orderNumber").ascending());
        return categoryRepository.findAll(pageable);
    }

    public Page<ResponseDtoForCategoryLang> getCategoriesByLang(String lang, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return categoryRepository.getCategoriesByLang(lang, Visible.ACTIVE, pageable)
                .map(p -> {
                    ResponseDtoForCategoryLang dto = new ResponseDtoForCategoryLang();
                    dto.setId(p.getCatId());
                    dto.setKey(p.getCatKey());
                    dto.setName(p.getCatName());
                    return dto;
                });
    }
}
