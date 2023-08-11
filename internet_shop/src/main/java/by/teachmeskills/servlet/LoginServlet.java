package by.teachmeskills.servlet;

import by.teachmeskills.model.User;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String GET_USER = "SELECT * FROM users WHERE email=? and password=?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = null;
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            connectionPool.closeConnection(connection);
            if (rs.next()) {
                user = User.builder().name(rs.getString(1)).surname(rs.getString(2))
                        .email(rs.getString(4))
                        .password(rs.getString(5)).build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/home").forward(req, resp);
        } else {
            req.setAttribute("error","Пользователь не зарегистрирован!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}