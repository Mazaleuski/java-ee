package by.teachmeskills.utils;

public final class ValidatorUtil {

    private ValidatorUtil() {
    }

    public static boolean validationNameAndSurname(String string) {
        return string.matches("[A-Za-z А-Яа-я]+") && Character.isUpperCase(string.charAt(0));
    }

    public static boolean validationEmail(String email) {
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean validationBirthday(String date) {
        return date.matches("\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*");
    }
}
