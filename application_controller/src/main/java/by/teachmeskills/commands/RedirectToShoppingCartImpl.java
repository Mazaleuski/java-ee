package by.teachmeskills.commands;

import by.teachmeskills.model.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.enums.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.SHOPPING_CART;

public class RedirectToShoppingCartImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(SHOPPING_CART.getValue());

        session.setAttribute(SHOPPING_CART.getValue(), cart);
        return CART_PAGE.getPath();
    }
}
