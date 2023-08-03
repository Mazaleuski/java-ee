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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("category_id");
        String categoryName = null;
        ServletContext ctx = getServletContext();
        List<Product> productList = new ArrayList<>();
        try {
            DBConnectionManager dbConnectionManager = (DBConnectionManager) ctx.getAttribute("DBManager");
            Connection connection = dbConnectionManager.getConnection();
            PreparedStatement productsStatement = connection.prepareStatement("SELECT * FROM products WHERE category_id=?");
            PreparedStatement categoriesStatement = connection.prepareStatement("SELECT * FROM categories WHERE id=?");
            productsStatement.setString(1, categoryId);
            categoriesStatement.setString(1, categoryId);
            ResultSet productResultSet = productsStatement.executeQuery();
            ResultSet categoryResultSet = categoriesStatement.executeQuery();
            while (productResultSet.next()) {
                productList.add(Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build());
            }
            if (categoryResultSet.next()) {
                categoryName = categoryResultSet.getString(2);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        req.setAttribute("category", categoryName);
        if (productList.size() != 0) {
            req.setAttribute("products", productList);
            req.getRequestDispatcher("/category.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
    }
}
