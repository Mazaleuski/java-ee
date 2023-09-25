package by.teachmeskills.shop.controllers;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.shop.ShopConstants.USER;
import static by.teachmeskills.shop.enums.PagesPathEnum.REGISTRATION_PAGE;

@RestController
@RequestMapping("/registration")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView openRegisterPage() {
        return new ModelAndView(REGISTRATION_PAGE.getPath());
    }

    @PostMapping
    public ModelAndView register(@ModelAttribute(USER) User user){
        return userService.create(user);
    }
}
