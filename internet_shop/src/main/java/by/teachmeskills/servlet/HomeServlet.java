package by.teachmeskills.servlet;

import by.teachmeskills.listener.DBConnectionManager;
import by.teachmeskills.model.Category;
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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE category_id=?";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", getCategoriesFromDB());
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }


    private List<Category> getCategoriesFromDB() {
        List<Category> categories = new ArrayList<>();
        ServletContext ctx = getServletContext();
        try {
            DBConnectionManager dbConnectionManager = (DBConnectionManager) ctx.getAttribute("DBManager");
            Connection connection = dbConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_CATEGORIES);
            while (rs.next()) {
                categories.add(Category.builder().id(rs.getString(1)).name(rs.getString(2))
                        .imageName(rs.getString(3)).productList(getProductByIdCategory(rs.getString(1))).build());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    private List<Product> getProductByIdCategory(String id) {
        List<Product> products = new ArrayList<>();
        ServletContext ctx = getServletContext();
        try {
            DBConnectionManager dbConnectionManager = (DBConnectionManager) ctx.getAttribute("DBManager");
            Connection connection = dbConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_CATEGORY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                products.add(Product.builder().id(rs.getInt(1)).name(rs.getString(2))
                        .description(rs.getString(3)).price(rs.getInt(4)).imageName(rs.getString(6)).build());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }
}
