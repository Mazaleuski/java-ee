package by.teachmeskills.servlet;

import by.teachmeskills.model.Product;
import by.teachmeskills.util.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id=?";
    private static final String GET_CATEGORY_NAME_BY_ID = "SELECT name FROM categories WHERE id=?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("category_id");
        String categoryName = null;
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement productsStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_ID);
            PreparedStatement categoriesStatement = connection.prepareStatement(GET_CATEGORY_NAME_BY_ID);
            productsStatement.setString(1, categoryId);
            categoriesStatement.setString(1, categoryId);
            ResultSet productResultSet = productsStatement.executeQuery();
            ResultSet categoryResultSet = categoriesStatement.executeQuery();
            connectionPool.closeConnection(connection);
            while (productResultSet.next()) {
                productList.add(Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build());
            }
            if (categoryResultSet.next()) {
                categoryName = categoryResultSet.getString(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (productList.size() != 0) {
            req.setAttribute("products", productList);
        } else {
            req.setAttribute("message", "В данной категории пока нет товаров...");
        }
        req.setAttribute("category", categoryName);
        req.getRequestDispatcher("/category.jsp").forward(req, resp);
    }
}
