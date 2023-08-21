package by.teachmeskills.commands;

import by.teachmeskills.enums.PagesPathEnum;
import by.teachmeskills.enums.RequestParamsEnum;
import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.util.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static by.teachmeskills.utils.HttpRequestParamValidator.validateParamNotNull;
import static by.teachmeskills.utils.ValidatorUtil.validationBirthday;
import static by.teachmeskills.utils.ValidatorUtil.validationEmail;
import static by.teachmeskills.utils.ValidatorUtil.validationNameAndSurname;

public class RegistrationPageCommandImpl implements BaseCommand {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_USER = "SELECT * FROM users WHERE email = ? and password=?";
    private static final String ADD_USER = "INSERT INTO users (name,surname,birthday,email,password) values (?,?,?,?,?)";
    private final static Logger log = LogManager.getLogger(RegistrationPageCommandImpl.class);

    @Override
    public String execute(HttpServletRequest request) {

        String name = request.getParameter(RequestParamsEnum.NAME.getValue());
        String surname = request.getParameter(RequestParamsEnum.SURNAME.getValue());
        String birthday = request.getParameter(RequestParamsEnum.BIRTHDAY.getValue());
        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());
        try {
            validateParamNotNull(name);
            validateParamNotNull(surname);
            validateParamNotNull(birthday);
            validateParamNotNull(email);
            validateParamNotNull(password);
        } catch (CommandException e) {
            log.warn(e.getMessage());
            return PagesPathEnum.REGISTRATION_PAGE.getPath();
        }
        if (validationNameAndSurname(name) && validationNameAndSurname(surname)
                && validationEmail(email) && validationBirthday(birthday)) {
            try {
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER);
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    request.setAttribute("info", "Данный пользователь уже зарегистрирован. Войдите в систему.");
                } else {
                    preparedStatement = connection.prepareStatement(ADD_USER);
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, surname);
                    preparedStatement.setString(3, birthday);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, password);
                    preparedStatement.executeUpdate();
                    request.setAttribute("info", "Пользователь успешно зарегистрирован. Войдите в систему.");
                }
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        } else {
            request.setAttribute("message", "Некорректные данные.");
        }
        return PagesPathEnum.REGISTRATION_PAGE.getPath();
    }
}