package by.teachmeskills.shop.services.impl;

import by.teachmeskills.shop.entities.Category;
import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.repositories.UserRepository;
import by.teachmeskills.shop.services.CategoryService;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.teachmeskills.shop.ShopConstants.ADDRESS;
import static by.teachmeskills.shop.ShopConstants.BIRTHDAY;
import static by.teachmeskills.shop.ShopConstants.CATEGORIES;
import static by.teachmeskills.shop.ShopConstants.EMAIL;
import static by.teachmeskills.shop.ShopConstants.NAME;
import static by.teachmeskills.shop.ShopConstants.ORDERS;
import static by.teachmeskills.shop.ShopConstants.PHONE_NUMBER;
import static by.teachmeskills.shop.ShopConstants.SURNAME;
import static by.teachmeskills.shop.ShopConstants.USER;
import static by.teachmeskills.shop.enums.PagesPathEnum.ACCOUNT_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.HOME_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.LOGIN_PAGE;
import static by.teachmeskills.shop.utils.ValidatorUtil.validateUserData;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ProductService productService;

    public UserServiceImpl(UserRepository userRepository, CategoryService categoryService, ProductService productService) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public ModelAndView create(User entity) {
        ModelAndView modelAndView = new ModelAndView(PagesPathEnum.REGISTRATION_PAGE.getPath());
        Map<String, String> data = Map.of(NAME, entity.getName(), SURNAME, entity.getSurname(), BIRTHDAY,
                String.valueOf(entity.getBirthday()), EMAIL, entity.getEmail(),
                ADDRESS, entity.getAddress(), PHONE_NUMBER, entity.getPhoneNumber());
        if (validateUserData(data)) {
            if ((userRepository.findByEmailAndPassword(entity.getEmail(), entity.getPassword())) != null) {
                modelAndView.addObject("info", "Данный пользователь уже зарегистрирован. Войдите в систему.");
            } else {
                userRepository.create(entity);
                modelAndView.addObject("info", "Пользователь успешно зарегистрирован. Войдите в систему.");
            }
        } else {
            modelAndView.addObject("info", "Некорректные данные.");
        }
        return modelAndView;
    }

    @Override
    public List<User> read() {
        return userRepository.read();
    }

    @Override
    public User update(User entity) {
        return userRepository.update(entity);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public ModelAndView authenticate(User user) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        if (Optional.ofNullable(user).isPresent()
                && Optional.ofNullable(user.getEmail()).isPresent()
                && Optional.ofNullable(user.getPassword()).isPresent()) {
            User loggedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (Optional.ofNullable(loggedUser).isPresent()) {
                List<Category> categoriesList = categoryService.read();
                modelMap.addAttribute(CATEGORIES, categoriesList);
                modelAndView.setViewName(HOME_PAGE.getPath());
                modelAndView.addAllObjects(modelMap);
            } else {
                modelMap.addAttribute("error", "Пользователь не зарегистрирован!");
                modelAndView.setViewName(LOGIN_PAGE.getPath());
                modelAndView.addAllObjects(modelMap);
            }
        }
        return modelAndView;
    }

    @Override
    public ModelAndView findUserOrders(User user) {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = new ModelMap();
        if (Optional.ofNullable(user).isPresent()
                && Optional.ofNullable(user.getEmail()).isPresent()
                && Optional.ofNullable(user.getPassword()).isPresent()) {
            User loggedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (Optional.ofNullable(loggedUser).isPresent()) {
                List<Product> productList = productService.findByCategoryId(1);
                modelMap.addAttribute(USER, loggedUser);
                modelMap.addAttribute(ORDERS, productList);
                modelMap.addAttribute("date", "17-08-2023");
                modelMap.addAttribute("number", "#123-34-999");
                modelAndView.addAllObjects(modelMap);
            }
        } else {
            modelMap.addAttribute("info", "Для входа в кабинет введите почту и пароль!");
            modelAndView.addAllObjects(modelMap);
        }
        modelAndView.setViewName(ACCOUNT_PAGE.getPath());
        return modelAndView;
    }
}
