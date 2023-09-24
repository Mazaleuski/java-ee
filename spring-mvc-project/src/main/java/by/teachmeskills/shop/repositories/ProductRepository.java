package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {
    Product findById(int id);

    List<Product> findByCategoryId(int id);

    List<Product> findByNameOrDescription(String search);
}
