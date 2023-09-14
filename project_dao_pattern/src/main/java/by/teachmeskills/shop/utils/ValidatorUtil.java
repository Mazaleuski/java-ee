package by.teachmeskills.shop.utils;

import java.util.Map;

public final class ValidatorUtil {

    private ValidatorUtil() {
    }

    public static boolean validateUserData(Map<String, String> data) {
        boolean validName = false, validSurname = false, validEmail = false,
                validBirthday = false, validAddress = false, validPhone = false;
        if (data.get("name") != null) {
            validName = data.get("name").matches("[A-Za-z А-Яа-я]+")
                    && Character.isUpperCase((data.get("name")).charAt(0));
        }
        if (data.get("surname") != null) {
            validSurname = data.get("surname").matches("[A-Za-z А-Яа-я]+")
                    && Character.isUpperCase((data.get("surname")).charAt(0));
        }
        if (data.get("email") != null) {
            validEmail = (data.get("email")).matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        }
        if (data.get("birthday") != null) {
            validBirthday = (data.get("birthday")).matches("\\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])*");
        }
        if (data.get("address") != null) {
            validAddress = (data.get("address")).matches("[A-Za-z А-Яа-я]+[0-9]{1,3}-[0-9]{1,3}");
        }
        if (data.get("phoneNumber") != null) {
            validPhone = (data.get("phoneNumber")).matches("^\\+?[1-9][0-9]{11}$");
        }
        return validName && validSurname && validEmail && validBirthday && validAddress && validPhone;
    }
}
