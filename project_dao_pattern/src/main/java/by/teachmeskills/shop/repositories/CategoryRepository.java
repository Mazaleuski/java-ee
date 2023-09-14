package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Category;

public interface CategoryRepository extends BaseRepository<Category> {
    String findNameById(int id);
}
