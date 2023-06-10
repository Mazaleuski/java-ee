package by.teachmeskills.ps.utils;

import by.teachmeskills.ps.model.AccountStatus;

public class ConverterUtils {
    public static AccountStatus toAccountStatus(String status) {
        return status.equals("ACTIVE") ? AccountStatus.ACTIVE : AccountStatus.DELETED;
    }
}