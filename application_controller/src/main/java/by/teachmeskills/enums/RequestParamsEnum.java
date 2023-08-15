package by.teachmeskills.enums;

public enum RequestParamsEnum {
    NAME("name"),
    SURNAME("surname"),
    BIRTHDAY("birthday"),
    USER("user"),
    LOGIN("email"),
    PASSWORD("password"),
    COMMAND("command"),
    SHOPPING_CART("cart"),
    CATEGORY("category"),
    CATEGORY_ID("category_id"),
    PRODUCT("product"),
    PRODUCTS("products"),
    SHOPPING_CART_PRODUCTS("cartProductsList"),
    PRODUCT_ID("product_id");

    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
