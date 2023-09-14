package by.teachmeskills.shop.commands;

import by.teachmeskills.shop.exceptions.CommandException;
import by.teachmeskills.shop.entities.Cart;
import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.services.ProductService;
import by.teachmeskills.shop.services.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.teachmeskills.shop.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.shop.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.shop.enums.RequestParamsEnum.SHOPPING_CART;

public class AddProductToCartCommandImpl implements BaseCommand {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());
        Product product = productService.findById(Integer.parseInt(productId));

        Cart cart;
        Object objCart = session.getAttribute(SHOPPING_CART.getValue());

        if (objCart != null) {
            cart = (Cart) objCart;
        } else {
            cart = new Cart();
            session.setAttribute(SHOPPING_CART.getValue(), cart);
        }
        cart.addProduct(product);
        request.setAttribute(PRODUCT.getValue(), product);

        return PRODUCT_PAGE.getPath();
    }
}
