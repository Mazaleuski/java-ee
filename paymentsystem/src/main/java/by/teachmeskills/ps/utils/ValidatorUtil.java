package by.teachmeskills.ps.utils;

public class ValidatorUtil {

    private ValidatorUtil() {
    }

    public static boolean validationId(String id) {
        return id.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
    }
}
