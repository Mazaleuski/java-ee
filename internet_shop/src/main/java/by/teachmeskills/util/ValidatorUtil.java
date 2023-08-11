package by.teachmeskills.util;

public class ValidatorUtil {

    private ValidatorUtil() {
    }

    private static volatile ValidatorUtil instance;

    public static ValidatorUtil getInstance() {
        if (instance == null) {
            synchronized (ValidatorUtil.class) {
                if (instance == null) {
                    instance = new ValidatorUtil();
                }
            }
        }
        return instance;
    }

    public boolean validationNameAndSurname(String string) {
        return string.matches("[A-Za-z А-Яа-я]+") && Character.isUpperCase(string.charAt(0));
    }

    public boolean validationEmail(String email) {
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public boolean validationBirthday(String date) {
        return date.matches("\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*");
    }
}
