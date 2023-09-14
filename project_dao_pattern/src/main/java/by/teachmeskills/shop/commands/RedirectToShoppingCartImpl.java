package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.entities.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;

public class RedirectToShoppingCartImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(SHOPPING_CART.getValue());
        session.setAttribute(SHOPPING_CART.getValue(), cart);
        return CART_PAGE.getPath();
    }
}
