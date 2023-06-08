package by.teachmeskills.ps.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class BankAccount {
    private String id;
    private String merchantId;
    private AccountStatus accountStatus;
    private String accountNumber;
    private LocalDateTime createdAt;

    public BankAccount(String id, String merchantId, AccountStatus accountStatus, String accountNumber, LocalDateTime createdAt) {
        this.id = id;
        this.merchantId = merchantId;
        this.accountStatus = accountStatus;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public BankAccount(Merchant m) {
        this.id = String.valueOf(UUID.randomUUID());
        this.merchantId = m.getId();
        this.accountStatus = AccountStatus.ACTIVE;
        this.accountNumber = UUID.randomUUID().toString().substring(0, 11).replaceAll("-", "");
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public AccountStatus getStatus() {
        return accountStatus;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return merchantId + " " + accountStatus + " " + accountNumber + " "
                + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm")) + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BankAccount that = (BankAccount) o;

        if (accountStatus != that.accountStatus) return false;
        if (!Objects.equals(accountNumber, that.accountNumber))
            return false;
        return Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        int result = accountStatus != null ? accountStatus.hashCode() : 0;
        result = 31 * result + (accountNumber != null ? accountNumber.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}