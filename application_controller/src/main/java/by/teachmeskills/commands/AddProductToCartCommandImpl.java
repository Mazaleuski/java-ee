package by.teachmeskills.commands;

import by.teachmeskills.exceptions.CommandException;
import by.teachmeskills.model.Cart;
import by.teachmeskills.model.Product;
import by.teachmeskills.util.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static by.teachmeskills.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;
import static by.teachmeskills.enums.RequestParamsEnum.SHOPPING_CART;

public class AddProductToCartCommandImpl implements BaseCommand{

    private static final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String productId = request.getParameter(PRODUCT_ID.getValue());

        Product product = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement productsStatement = connection.prepareStatement(GET_PRODUCT_BY_ID);
            productsStatement.setString(1, productId);
            ResultSet productResultSet = productsStatement.executeQuery();
            connectionPool.closeConnection(connection);
            if (productResultSet.next()) {
                product = Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
