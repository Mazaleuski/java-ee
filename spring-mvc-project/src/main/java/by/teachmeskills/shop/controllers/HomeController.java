package by.teachmeskills.shop.controllers;

import by.teachmeskills.shop.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.shop.ShopConstants.CATEGORIES;
import static by.teachmeskills.shop.enums.PagesPathEnum.HOME_PAGE;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ModelAndView openHomePage() {
        ModelAndView modelAndView = new ModelAndView(HOME_PAGE.getPath());
         return modelAndView.addObject(CATEGORIES,categoryService.read());
    }
}
