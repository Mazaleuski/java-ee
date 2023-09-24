package by.teachmeskills.shop.controllers;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.shop.ShopConstants.USER;

@RestController
@RequestMapping("/account")
@SessionAttributes(USER)
public class AccountController {

    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView openAccountPage(@SessionAttribute(name = USER, required = false) User user) {
        return userService.findUserOrders(user);
    }
}
