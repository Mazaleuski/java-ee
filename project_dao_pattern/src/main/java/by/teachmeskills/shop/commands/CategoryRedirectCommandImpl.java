package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.enums.RequestParamsEnum;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static by.teachmeskills.shop.enums.RequestParamsEnum.CATEGORY_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCTS;

public class CategoryRedirectCommandImpl implements BaseCommand {
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int categoryId = Integer.parseInt(request.getParameter(CATEGORY_ID.getValue()));
        String categoryName = categoryService.findNameById(categoryId);
        List<Product> productList = productService.findByCategoryId(categoryId);
        if (productList.size() != 0) {
            request.setAttribute(PRODUCTS.getValue(), productList);
        } else {
            request.setAttribute("message", "В данной категории пока нет товаров...");
        }
        request.setAttribute(RequestParamsEnum.CATEGORY.getValue(), categoryName);
        return PagesPathEnum.CATEGORY_PAGE.getPath();
    }
}
