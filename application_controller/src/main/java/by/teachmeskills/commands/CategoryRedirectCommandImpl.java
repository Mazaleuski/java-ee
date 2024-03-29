package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.model.Product;
import by.teachmeskills.utils.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static by.teachmeskills.enums.RequestParamsEnum.CATEGORY_ID;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCTS;

public class CategoryRedirectCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(CategoryRedirectCommandImpl.class);
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id=?";
    private static final String GET_CATEGORY_NAME_BY_ID = "SELECT name FROM categories WHERE id=?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String categoryId = request.getParameter(CATEGORY_ID.getValue());
        String categoryName = null;
        List<Product> productList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement productsStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_ID);
            PreparedStatement categoriesStatement = connection.prepareStatement(GET_CATEGORY_NAME_BY_ID);
            productsStatement.setString(1, categoryId);
            categoriesStatement.setString(1, categoryId);
            ResultSet productResultSet = productsStatement.executeQuery();
            ResultSet categoryResultSet = categoriesStatement.executeQuery();
            while (productResultSet.next()) {
                productList.add(Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build());
            }
            if (categoryResultSet.next()) {
                categoryName = categoryResultSet.getString(1);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            try {
                connectionPool.closeConnection(connection);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        if (productList.size() != 0) {
            request.setAttribute(PRODUCTS.getValue(), productList);
        } else {
            request.setAttribute("message", "В данной категории пока нет товаров...");
        }
        request.setAttribute(RequestParamsEnum.CATEGORY.getValue(), categoryName);
        return PagesPathEnum.CATEGORY_PAGE.getPath();
    }
}
