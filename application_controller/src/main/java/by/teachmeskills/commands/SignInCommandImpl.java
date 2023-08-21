package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.model.User;
import by.teachmeskills.utils.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static by.teachmeskills.utils.DataUtil.getCategoriesFromDB;
import static by.teachmeskills.utils.HttpRequestParamValidator.validateParamNotNull;

public class SignInCommandImpl implements BaseCommand {
    private final static Logger log = LogManager.getLogger(SignInCommandImpl.class);
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_USER = "SELECT * FROM users WHERE email=? and password=?";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());

        validateParamNotNull(email);
        validateParamNotNull(password);

        User user = null;
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = User.builder().name(rs.getString(2)).surname(rs.getString(3))
                        .birthDay(rs.getString(4))
                        .email(rs.getString(5)).build();
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            try {
                connectionPool.closeConnection(connection);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
        return checkReceivedUser(user, request);
    }

    private String checkReceivedUser(User user, HttpServletRequest request) {
        if (user != null) {
            request.getSession().setAttribute(RequestParamsEnum.USER.getValue(), user);
            request.setAttribute("categories", getCategoriesFromDB());
            return PagesPathEnum.HOME_PAGE.getPath();
        } else {
            request.setAttribute("error", "Пользователь не зарегистрирован!");
            return PagesPathEnum.LOGIN_PAGE.getPath();
        }
    }
}