package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class HomePageCommandImpl implements BaseCommand {
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        List<Category> categoryList = categoryService.read();
        request.setAttribute("categories", categoryList);
        return PagesPathEnum.HOME_PAGE.getPath();
    }
}