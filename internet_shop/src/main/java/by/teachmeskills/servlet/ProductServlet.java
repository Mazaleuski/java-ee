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

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private static final String GET_PRODUCTS_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product_id");
        Product product = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement productsStatement = connection.prepareStatement(GET_PRODUCTS_BY_ID);
            productsStatement.setString(1, productId);
            ResultSet productResultSet = productsStatement.executeQuery();
            connectionPool.closeConnection(connection);
            if (productResultSet.next()) {
                product = Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        req.setAttribute("product", product);
        req.getRequestDispatcher("/product.jsp").forward(req, resp);
    }
}
