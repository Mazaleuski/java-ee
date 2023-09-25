package by.teachmeskills.shop.repositories;

import by.teachmeskills.shop.entities.User;
import org.springframework.web.servlet.ModelAndView;

public interface UserRepository extends BaseRepository<User> {

    User findById(int id);

    User findByEmailAndPassword(String email, String password);

}
