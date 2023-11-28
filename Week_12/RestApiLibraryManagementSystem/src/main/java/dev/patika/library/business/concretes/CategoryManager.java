package dev.patika.library.business.concretes;

import dev.patika.library.business.abstracts.ICategoryService;
import dev.patika.library.dao.CategoryRepo;
import dev.patika.library.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryManager implements ICategoryService {

    @Autowired
    private final CategoryRepo categoryRepo;

    public CategoryManager(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category getById(int id) {
        return this.categoryRepo.findById(id).orElseThrow();
    }


    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category update(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public String delete(int id) {
        Category category = this.getById(id);

        // Kategorinin silinip silinemeyeceğininin kontrolu
        if (category.getBooks() != null && !category.getBooks().isEmpty()) {
            return "Bu kategoriye ait kitap var. Bu kategori silinemedi.";
        } else {
            this.categoryRepo.delete(category);
            return "Kategori başarıyla silindi.";
        }
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepo.findAll();
    }
}
