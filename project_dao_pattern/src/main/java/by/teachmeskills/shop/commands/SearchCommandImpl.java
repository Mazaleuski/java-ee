package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.enums.RequestParamsEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.exceptions.RequestParamNullException;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import static by.teachmeskills.shop.utils.HttpRequestParamValidator.validateParamNotNull;

@Log4j2
public class SearchCommandImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String search = request.getParameter("searchString");

        try {
            validateParamNotNull(search);
            if (search.length() < 3) {
                request.setAttribute("info", "Не менее трех символов для поиска!");
            } else {
                List<Product> productList = productService.findByNameOrDescription(search);
                if (productList.size() != 0) {
                    request.setAttribute(RequestParamsEnum.PRODUCTS.getValue(), productList);
                } else {
                    request.setAttribute("message", "Ничего не найдено...");
                }
            }
        } catch (RequestParamNullException e) {
            log.warn(e.getMessage());
        }
        request.setAttribute(RequestParamsEnum.CATEGORIES.getValue(), categoryService.read());
        return PagesPathEnum.SEARCH_PAGE.getPath();
    }
}
