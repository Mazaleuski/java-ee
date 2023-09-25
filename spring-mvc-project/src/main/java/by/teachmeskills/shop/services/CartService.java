package by.teachmeskills.shop.services;

import by.teachmeskills.shop.entities.Cart;
import by.teachmeskills.shop.entities.Product;
import by.teachmeskills.shop.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static by.teachmeskills.shop.ShopConstants.CART;
import static by.teachmeskills.shop.ShopConstants.PRODUCT;
import static by.teachmeskills.shop.enums.PagesPathEnum.CART_PAGE;
import static by.teachmeskills.shop.enums.PagesPathEnum.PRODUCT_PAGE;

@Service
public class CartService {

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ModelAndView addProductToCart(int productId, Cart cart) {
        ModelMap modelParams = new ModelMap();
        Product product = productRepository.findById(productId);
        cart.addProduct(product);
        modelParams.addAttribute(CART, cart);
        modelParams.addAttribute(PRODUCT, product);

        return new ModelAndView(PRODUCT_PAGE.getPath(), modelParams);
    }

    public ModelAndView removeProductFromCart(int productId, Cart cart) {
        ModelMap modelParams = new ModelMap();

        cart.removeProduct(productId);
        modelParams.addAttribute(CART, cart);

        return new ModelAndView(CART_PAGE.getPath(), modelParams);
    }
}
