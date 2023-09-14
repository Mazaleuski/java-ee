package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.enums.PagesPathEnum;
import by.teachmeskills.shop.exceptions.CommandException;

import by.teachmeskills.shop.entities.User;
import by.teachmeskills.shop.entities.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class AccountPageCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("orders", getUserOrdersFromDB());
            request.setAttribute("date","17-08-2023");
            request.setAttribute("number","#123-34-999");
        } else {
            request.setAttribute("info", "Для входа в кабинет введите почту и пароль!");
        }
        return PagesPathEnum.ACCOUNT_PAGE.getPath();
    }

    private List<Product> getUserOrdersFromDB() {
        return List.of(Product.builder().name("iphone 13").description("Хороший").imagePath("13.jpg").price(800).build(),
                Product.builder().name("iphone 14").description("Отличный").imagePath("14.jpg").price(900).build(),
                Product.builder().name("iphone 4").description("Классика").imagePath("4.jpg").price(1200).build());
    }
}
