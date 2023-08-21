package by.teachmeskills.enums;

public enum CommandsEnum {
    HOME_PAGE_COMMAND("home"),
    SIGN_IN_COMMAND("login"),
    CATEGORY_PAGE_COMMAND("category-redirect"),
    REDIRECT_SHOPPING_CART_COMMAND("redirect-to-shopping-cart"),
    REDIRECT_PRODUCT_COMMAND("product-redirect"),
    ADD_PRODUCT_TO_CART("add-product-to-cart"),
    DELETE_PRODUCT_FROM_CART("delete-product-from-cart"),
    REGISTRATION_PAGE_COMMAND("registration");


    private final String command;

    CommandsEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
