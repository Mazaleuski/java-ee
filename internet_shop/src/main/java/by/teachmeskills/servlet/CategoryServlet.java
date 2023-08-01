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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE category_id=?");
            PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM categories WHERE id=?");
            preparedStatement.setString(1, categoryId);
            preparedStatement2.setString(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            ResultSet rs2 = preparedStatement2.executeQuery();
            while (rs.next()) {
                productList.add(Product.builder().id(rs.getInt(1)).name(rs.getString(2))
                        .description(rs.getString(3)).price(rs.getInt(4)).imageName(rs.getString(6)).build());
            }
            if (rs2.next()) {
                categoryName = rs2.getString(2);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        req.getSession().setAttribute("category", categoryName);
        if (productList.size() != 0) {
            req.getSession().setAttribute("products", productList);
            resp.sendRedirect("/category.jsp");
        } else {
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }
    }
}
