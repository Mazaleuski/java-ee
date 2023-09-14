package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Category;

public interface CategoryService extends BaseService<Category> {
    String findNameById(int id);
}
