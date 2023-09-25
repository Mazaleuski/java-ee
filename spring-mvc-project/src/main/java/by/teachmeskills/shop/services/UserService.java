package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.User;
import org.springframework.web.servlet.ModelAndView;

public interface UserService extends BaseService<User> {
    User findByEmailAndPassword(String email, String password);

    User findById(int id);

    ModelAndView authenticate(User user);

    ModelAndView findUserOrders(User user);
}
