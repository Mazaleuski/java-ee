package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.entities.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;

public class DeleteProductFromCartCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());
        Cart cart = (Cart) session.getAttribute(SHOPPING_CART.getValue());
        cart.removeProduct(Integer.parseInt(productId));
        session.setAttribute(SHOPPING_CART.getValue(), cart);
        return CART_PAGE.getPath();
    }
}
