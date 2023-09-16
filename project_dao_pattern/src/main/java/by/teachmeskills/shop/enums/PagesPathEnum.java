package by.teachmeskills.shop.enums;

public enum PagesPathEnum {
    HOME_PAGE("home.jsp"),
    LOGIN_PAGE("login.jsp"),
    CATEGORY_PAGE("category.jsp"),
    CART_PAGE("cart.jsp"),
    PRODUCT_PAGE("product.jsp"),
    REGISTRATION_PAGE("registration.jsp"),
    ACCOUNT_PAGE("account.jsp"),
    SEARCH_PAGE("search.jsp");

    private final String path;

    PagesPathEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
