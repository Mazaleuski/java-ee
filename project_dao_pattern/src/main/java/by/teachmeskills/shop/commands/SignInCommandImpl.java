package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.enums.RequestParamsEnum;
import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.UserService;
import by.teachmeskills.shop.services.impl.CategoryServiceImpl;
import by.teachmeskills.shop.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static by.teachmeskills.shop.utils.HttpRequestParamValidator.validateParamNotNull;

public class SignInCommandImpl implements BaseCommand {
    private final UserService userService = new UserServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(RequestParamsEnum.LOGIN.getValue());
        String password = request.getParameter(RequestParamsEnum.PASSWORD.getValue());

        validateParamNotNull(email);
        validateParamNotNull(password);

        User user = userService.findByEmailAndPassword(email, password);
        return checkReceivedUser(user, request);
    }

    private String checkReceivedUser(User user, HttpServletRequest request) {
        if (user != null) {
            request.getSession().setAttribute(RequestParamsEnum.USER.getValue(), user);
            request.setAttribute("categories", categoryService.read());
            return PagesPathEnum.HOME_PAGE.getPath();
        } else {
            request.setAttribute("error", "Пользователь не зарегистрирован!");
            return PagesPathEnum.LOGIN_PAGE.getPath();
        }
    }
}