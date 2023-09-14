package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.enums.RequestParamsEnum;
import by.teachmeskills.shop.exceptions.RequestParamNullException;
import by.teachmeskills.shop.services.UserService;
import by.teachmeskills.shop.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.Map;

import static by.teachmeskills.shop.utils.HttpRequestParamValidator.validateParamNotNull;
import static by.teachmeskills.shop.utils.ValidatorUtil.validateUserData;

@Log4j2
public class RegistrationPageCommandImpl implements BaseCommand {
    private final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(RequestParamsEnum.NAME.getValue());
        String surname = request.getParameter(RequestParamsEnum.SURNAME.getValue());
        String birthday = request.getParameter(RequestParamsEnum.BIRTHDAY.getValue());
        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());
        String address = request.getParameter(RequestParamsEnum.ADDRESS.getValue());
        String phoneNumber = request.getParameter(RequestParamsEnum.PHONE_NUMBER.getValue());
        try {
            validateParamNotNull(name);
            validateParamNotNull(surname);
            validateParamNotNull(birthday);
            validateParamNotNull(email);
            validateParamNotNull(password);
            validateParamNotNull(address);
            validateParamNotNull(phoneNumber);
        } catch (RequestParamNullException e) {
            log.warn(e.getMessage());
            return PagesPathEnum.REGISTRATION_PAGE.getPath();
        }
        Map<String, String> data = Map.of("name", name, "surname", surname, "birthday", birthday, "email", email,
                "address", address, "phoneNumber", phoneNumber);

        if (validateUserData(data)) {
            if ((userService.findByEmailAndPassword(email, password)) != null) {
                request.setAttribute("info", "Данный пользователь уже зарегистрирован. Войдите в систему.");
            } else {
                userService.create(User.builder()
                        .name(name)
                        .surname(surname)
                        .birthday(LocalDate.parse(birthday))
                        .email(email).password(password)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .build());
                request.setAttribute("info", "Пользователь успешно зарегистрирован. Войдите в систему.");
            }
        } else {
            request.setAttribute("info", "Некорректные данные.");
        }
        return PagesPathEnum.REGISTRATION_PAGE.getPath();
    }
}