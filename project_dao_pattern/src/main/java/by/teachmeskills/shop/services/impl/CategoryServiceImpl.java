package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.repositories.CategoryRepository;
import by.teachmeskills.shop.repositories.impl.CategoryRepositoryImpl;
import by.teachmeskills.shop.services.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository = new CategoryRepositoryImpl();

    @Override
    public Category create(Category entity) {
        return categoryRepository.create(entity);
    }

    @Override
    public List<Category> read() {
        return categoryRepository.read();
    }

    @Override
    public Category update(Category entity) {
        return categoryRepository.update(entity);
    }

    @Override
    public void delete(int id) {
        categoryRepository.delete(id);
    }

    @Override
    public String findNameById(int id) {
        return categoryRepository.findNameById(id);
    }
}
