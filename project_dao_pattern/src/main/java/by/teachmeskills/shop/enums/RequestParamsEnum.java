package by.teachmeskills.shop.enums;

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
    PRODUCT_ID("product_id"),
    ADDRESS("address"),
    PHONE_NUMBER("phoneNumber");

    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
