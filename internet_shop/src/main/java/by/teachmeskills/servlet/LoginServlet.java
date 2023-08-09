package by.teachmeskills.servlet;

import by.teachmeskills.listener.DBConnectionManager;
import by.teachmeskills.model.User;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String GET_USER = "SELECT * FROM users WHERE name=? and password=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        ServletContext ctx = getServletContext();
        User user = null;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            DBConnectionManager dbConnectionManager = (DBConnectionManager) ctx.getAttribute("DBManager");
            Connection connection = dbConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = User.builder().email(rs.getString(1)).password(rs.getString(2)).build();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/home").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}