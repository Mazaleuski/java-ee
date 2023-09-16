package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Product;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    Product findById(int id);

    List<Product> findByCategoryId(int id);

    List<Product> findByNameOrDescription(String search);
}
