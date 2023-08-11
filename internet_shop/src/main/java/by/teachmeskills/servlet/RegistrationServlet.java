package by.teachmeskills.servlet;

import by.teachmeskills.util.ConnectionPool;
import by.teachmeskills.util.ValidatorUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private static final ValidatorUtil validatorUtil = ValidatorUtil.getInstance();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_USER = "SELECT * FROM users WHERE email = ? and password=?";
    private static final String ADD_USER = "INSERT INTO users (name,surname,birthday,email,password) values (?,?,?,?,?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthday = req.getParameter("birthday");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (validatorUtil.validationNameAndSurname(name) && validatorUtil.validationNameAndSurname(surname)
                && validatorUtil.validationEmail(email) && validatorUtil.validationBirthday(birthday)) {
            try {
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    req.setAttribute("message", "Данный пользователь уже зарегистрирован. Войдите в систему.");
                } else {
                    preparedStatement = connection.prepareStatement(ADD_USER);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setString(3, birthday);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, password);
                    preparedStatement.executeUpdate();
                    connectionPool.closeConnection(connection);
                    req.setAttribute("message", "Пользователь успешно зарегистрирован. Войдите в систему.");
                }
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            req.setAttribute("message", "Некорректные данные.");
            req.getRequestDispatcher("/registration.jsp").forward(req, resp);
        }
    }
}
