package by.teachmeskills.ps.service;

import by.teachmeskills.ps.model.BankAccount;
import by.teachmeskills.ps.model.Merchant;
import by.teachmeskills.ps.model.AccountStatus;
import by.teachmeskills.ps.exceptions.BankAccountNotFoundException;
import by.teachmeskills.ps.exceptions.MerchantNotFoundException;
import by.teachmeskills.ps.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.ps.utils.CRUDUtils;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class MerchantService {

    public BankAccount addBankAccount(String id, String bankAccountNum) throws IOException, IllegalArgumentException, MerchantNotFoundException {
        Merchant m = getMerchantById(id);
        AtomicReference<BankAccount> ba = new AtomicReference<>();
        if (bankAccountNum.matches("[0-9a-zA-Z]{10}")) {
            Optional<BankAccount> o = CRUDUtils.getMerchantBankAccounts(id).stream()
                    .filter(b -> b.getAccountNumber().equals(bankAccountNum)).findFirst();
            o.ifPresentOrElse(
                    (n) -> {
                        if (n.getStatus().equals(AccountStatus.DELETED)) {
                            n.setStatus(AccountStatus.ACTIVE);
                            CRUDUtils.updateMerchantBankAccount(n);
                            ba.set(n);
                        }
                    },
                    () -> {
                        BankAccount bankAccount = new BankAccount(m);
                        CRUDUtils.createBankAccount(m);
                        ba.set(bankAccount);
                    });

        } else {
            throw new IllegalArgumentException("Incorrect accountNumber.");
        }
        return ba.get();
    }

    public List<BankAccount> getMerchantBankAccounts(String id) throws NoBankAccountsFoundException, MerchantNotFoundException {
        List<BankAccount> bankAccounts = CRUDUtils.getMerchantBankAccounts(id);
        Merchant m = CRUDUtils.getMerchantById(id);
        if (m == null) {
            throw new MerchantNotFoundException("Merchant with " + id + " not found.");
        } else if (bankAccounts.size() == 0) {
            throw new NoBankAccountsFoundException("Merchant don't have bank accounts.");
        }
        return bankAccounts;
    }

    public BankAccount updateBankAccount(String id, String newAccountNum) throws IllegalArgumentException, BankAccountNotFoundException {
        if (!id.matches("[0-9a-zA-Z]{10}}") && !newAccountNum.matches("[0-9a-zA-Z]{10}")) {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        BankAccount bankAccount = CRUDUtils.getBankAccountById(id);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException("Bank account not found");
        } else {
            bankAccount.setAccountNumber(newAccountNum);
            CRUDUtils.updateMerchantBankAccount(bankAccount);
        }
        return bankAccount;
    }

    public void deleteBankAccount(String id) throws IllegalArgumentException {
        if (!id.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
            throw new IllegalArgumentException("Incorrect account number.");
        }
        CRUDUtils.deleteBankAccountById(id);
    }

    public Merchant createMerchant(Merchant merchant) {
        return CRUDUtils.createMerchant(merchant);
    }

    public List<Merchant> getMerchants() {
        return CRUDUtils.getAllMerchants();
    }

    public Merchant getMerchantById(String id) throws MerchantNotFoundException {
        Merchant m = CRUDUtils.getMerchantById(id);
        if (m == null) {
            throw new MerchantNotFoundException("Merchant with " + id + " not found.");
        }
        return m;
    }

    public void deleteMerchant(String id) {
        CRUDUtils.deleteMerchantById(id);
    }
}