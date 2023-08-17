package by.teachmeskills.utils;

import by.teachmeskills.model.Category;
import by.teachmeskills.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class DataUtil {
    private DataUtil() {
    }

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id=?";

    public static List<Category> getCategoriesFromDB() {
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_CATEGORIES);

            while (rs.next()) {
                categories.add(Category.builder().id(rs.getString(1)).name(rs.getString(2))
                        .imageName(rs.getString(3)).productList(getProductByIdCategory(rs.getString(1))).build());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                connectionPool.closeConnection(connection);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return categories;
    }

    /*данный метод использую в DataUtil.getCategoriesFromDB() для получения продуктов определенной категории
    и создания категории с листом этих продуктов*/

    public static List<Product> getProductByIdCategory(String id) {
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
            System.out.println(e.getMessage());
        }
        return products;
    }
}
