package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.ShopConstants;
import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.repositories.CategoryRepository;
import by.teachmeskills.shop.repositories.ProductRepository;
import by.teachmeskills.shop.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static by.teachmeskills.shop.ShopConstants.CATEGORY;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public ModelAndView create(Category entity) {
        return new ModelAndView();
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
    public ModelAndView findById(int id) {
        ModelAndView modelAndView = new ModelAndView(PagesPathEnum.CATEGORY_PAGE.getPath());
        Category category = categoryRepository.findById(id);
        if (Optional.ofNullable(category).isPresent()) {
            List<Product> products = productService.findByCategoryId(id);
            if (products.size() != 0) {
                modelAndView.addObject(ShopConstants.PRODUCTS, products);
            } else {
                modelAndView.addObject("message", "В данной категории пока нет товаров...");
            }
            modelAndView.addObject(CATEGORY, category.getName());
        }
        return modelAndView;
    }
}
