package by.teachmeskills.servlet;

import by.teachmeskills.listener.DBConnectionManager;
import by.teachmeskills.model.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product_id");
        Product product = null;
        ServletContext ctx = getServletContext();
        try {
            DBConnectionManager dbConnectionManager = (DBConnectionManager) ctx.getAttribute("DBManager");
            Connection connection = dbConnectionManager.getConnection();
            PreparedStatement productsStatement = connection.prepareStatement("SELECT * FROM products WHERE id=?");
            productsStatement.setString(1, productId);
            ResultSet productResultSet = productsStatement.executeQuery();
            if (productResultSet.next()) {
                product=Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        req.setAttribute("product", product);
        req.getRequestDispatcher("/product.jsp").forward(req,resp);
    }
}
