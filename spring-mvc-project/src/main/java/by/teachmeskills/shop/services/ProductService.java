package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Product;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ProductService extends BaseService<Product> {
    ModelAndView findById(int id);

    List<Product> findByCategoryId(int id);

    ModelAndView findByNameOrDescription(String search);
}
