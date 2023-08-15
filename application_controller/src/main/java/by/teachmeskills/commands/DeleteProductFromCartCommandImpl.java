package by.teachmeskills.commands;

import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.model.Cart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.enums.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.enums.RequestParamsEnum.SHOPPING_CART;
import static by.teachmeskills.enums.RequestParamsEnum.SHOPPING_CART_PRODUCTS;

public class DeleteProductFromCartCommandImpl implements BaseCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());
        Cart cart = (Cart) session.getAttribute(SHOPPING_CART.getValue());

        cart.removeProduct(Integer.parseInt(productId));
        request.setAttribute(SHOPPING_CART_PRODUCTS.getValue(), cart.getProducts());

        return CART_PAGE.getPath();
    }
}
