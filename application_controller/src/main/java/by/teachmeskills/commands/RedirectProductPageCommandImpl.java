package by.teachmeskills.commands;

import by.teachmeskills.model.Product;
import by.teachmeskills.util.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static by.teachmeskills.enums.PagesPathEnum.PRODUCT_PAGE;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT;
import static by.teachmeskills.enums.RequestParamsEnum.PRODUCT_ID;

public class RedirectProductPageCommandImpl implements BaseCommand{
    private static final String GET_PRODUCTS_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final static Logger log = LogManager.getLogger(RedirectProductPageCommandImpl.class);
    @Override
    public String execute(HttpServletRequest request) {
        String productId = request.getParameter(PRODUCT_ID.getValue());
        Product product = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement productsStatement = connection.prepareStatement(GET_PRODUCTS_BY_ID);
            productsStatement.setString(1, productId);
            ResultSet productResultSet = productsStatement.executeQuery();
            connectionPool.closeConnection(connection);
            if (productResultSet.next()) {
                product = Product.builder().id(productResultSet.getInt(1)).name(productResultSet.getString(2))
                        .description(productResultSet.getString(3)).price(productResultSet.getInt(4)).imageName(productResultSet.getString(6)).build();
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        request.setAttribute(PRODUCT.getValue(), product);
        return PRODUCT_PAGE.getPath();
    }
}
