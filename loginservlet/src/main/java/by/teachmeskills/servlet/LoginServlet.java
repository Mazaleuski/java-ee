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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=? and password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/home.jsp");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
