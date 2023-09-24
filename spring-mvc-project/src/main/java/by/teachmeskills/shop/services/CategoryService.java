package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Category;
import org.springframework.web.servlet.ModelAndView;

public interface CategoryService extends BaseService<Category> {
    ModelAndView findById(int id);
}
