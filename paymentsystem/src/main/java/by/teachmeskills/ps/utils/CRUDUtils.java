package by.teachmeskills.ps.utils;

import by.teachmeskills.ps.model.BankAccount;
import by.teachmeskills.ps.model.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    private final static String ADD_BANK_ACCOUNT_QUERY = "INSERT INTO bank_accounts (id, merchant_id, status, account_number, created_at) Values (?, ?, ?, ?, ?)";
    private final static String ADD_MERCHANT_QUERY = "INSERT INTO merchants (id, name, created_at) Values (?, ?, ?)";
    private final static String GET_ALL_MERCHANTS = "SELECT * FROM merchants";
    private final static String GET_MERCHANT_BANK_ACCOUNTS = "SELECT * FROM bank_accounts WHERE merchant_id = ? order by status ASC, created_at ASC";
    private final static String UPDATE_MERCHANT_BANK_ACCOUNT = "UPDATE bank_accounts SET account_number = ? WHERE id = ?";
    private final static String GET_BANK_ACCOUNT_BY_ID = "SELECT * FROM bank_accounts WHERE id = ?";
    private final static String GET_MERCHANT_BY_ID = "SELECT * FROM merchants WHERE id = ?";
    private final static String DELETE_BANK_ACCOUNT_BY_ID = "DELETE FROM bank_accounts WHERE merchant_id = ?";
    private final static String DELETE_MERCHANT_BY_ID = "DELETE FROM merchants WHERE id = ?";

    private CRUDUtils() {
    }

    public static List<BankAccount> getMerchantBankAccounts(String merchantId) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_MERCHANT_BANK_ACCOUNTS)) {
            statement.setString(1, merchantId);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                bankAccounts.add(new BankAccount(set.getString(1), set.getString(2),
                        ConverterUtils.toAccountStatus(set.getString(3))
                        , set.getString(4), set.getTimestamp(5).toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bankAccounts;
    }

    public static BankAccount updateMerchantBankAccount(BankAccount bankAccount) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_MERCHANT_BANK_ACCOUNT)) {
            statement.setString(1, bankAccount.getAccountNumber());
            statement.setString(2, bankAccount.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bankAccount;
    }

    public static BankAccount createBankAccount(Merchant merchant) {
        BankAccount b = null;
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_BANK_ACCOUNT_QUERY)) {
            b = new BankAccount(merchant);
            statement.setString(1, b.getId());
            statement.setString(2, b.getMerchantId());
            statement.setString(3, b.getStatus().toString());
            statement.setString(4, b.getAccountNumber());
            statement.setTimestamp(5, Timestamp.valueOf(b.getCreatedAt()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return b;
    }

    public static BankAccount getBankAccountById(String id) {
        BankAccount ba = null;
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BANK_ACCOUNT_BY_ID)) {
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                ba = new BankAccount(set.getString(1), set.getString(2),
                        ConverterUtils.toAccountStatus(set.getString(3))
                        , set.getString(4), set.getTimestamp(5).toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ba;
    }

    public static void deleteBankAccountById(String id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BANK_ACCOUNT_BY_ID)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Merchant createMerchant(Merchant merchant) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_MERCHANT_QUERY)) {
            statement.setString(1, merchant.getId());
            statement.setString(2, merchant.getName());
            statement.setTimestamp(3, Timestamp.valueOf(merchant.getCreatedAt()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchant;
    }

    public static List<Merchant> getAllMerchants() {
        List<Merchant> merchants = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(GET_ALL_MERCHANTS);
            while (set.next()) {
                merchants.add(new Merchant(set.getString(1), set.getString(2),
                        set.getTimestamp(3).toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchants;
    }

    public static Merchant getMerchantById(String id) {
        Merchant merchant = null;
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_MERCHANT_BY_ID)) {
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                merchant = new Merchant(set.getString(1), set.getString(2),
                        set.getTimestamp(3).toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return merchant;
    }

    public static void deleteMerchantById(String id) {
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MERCHANT_BY_ID)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        deleteBankAccountById(id);
    }
}