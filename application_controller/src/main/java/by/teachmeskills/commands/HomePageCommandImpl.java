package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.model.Category;
import by.teachmeskills.model.Product;
import by.teachmeskills.utils.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomePageCommandImpl implements BaseCommand {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id=?";
    private final static Logger log = LogManager.getLogger(HomePageCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("categories", getCategoriesFromDB());
        return PagesPathEnum.HOME_PAGE.getPath();
    }

    private List<Category> getCategoriesFromDB() {
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_CATEGORIES);
            connectionPool.closeConnection(connection);
            while (rs.next()) {
                categories.add(Category.builder().id(rs.getString(1)).name(rs.getString(2))
                        .imageName(rs.getString(3)).productList(getProductByIdCategory(rs.getString(1))).build());
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return categories;
    }

    private List<Product> getProductByIdCategory(String id) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            connectionPool.closeConnection(connection);
            while (rs.next()) {
                products.add(Product.builder().id(rs.getInt(1)).name(rs.getString(2))
                        .description(rs.getString(3)).price(rs.getInt(4)).imageName(rs.getString(6)).build());
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return products;
    }
}
