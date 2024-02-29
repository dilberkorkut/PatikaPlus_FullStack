package dev.patika.library.api;

import dev.patika.library.business.abstracts.ICategoryService;
import dev.patika.library.dto.request.AuthorUpdateRequest;
import dev.patika.library.dto.request.CategorySaveRequest;
import dev.patika.library.dto.request.CategoryUpdateRequest;
import dev.patika.library.entities.Author;
import dev.patika.library.entities.Category;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(ICategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category getById(@PathVariable("id") int id) {
        return this.categoryService.getById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Category save(@Valid @RequestBody CategorySaveRequest categorySaveRequest) {
        Category newCategory = this.modelMapper.map(categorySaveRequest, Category.class);
        return this.categoryService.save(newCategory);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category update(@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category newCategory = this.categoryService.getById(categoryUpdateRequest.getId());
        newCategory.setCategoryName(categoryUpdateRequest.getCategoryName());
        newCategory.setCategoryDescription(categoryUpdateRequest.getCategoryDescription());
        return this.categoryService.update(newCategory);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        this.categoryService.delete(id);

    }

}
