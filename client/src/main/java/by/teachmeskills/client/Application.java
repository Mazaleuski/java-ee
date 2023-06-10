package by.teachmeskills.client;

import by.teachmeskills.ps.exceptions.BankAccountNotFoundException;
import by.teachmeskills.ps.exceptions.MerchantNotFoundException;
import by.teachmeskills.ps.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.ps.model.Merchant;
import by.teachmeskills.ps.service.MerchantService;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
            new Application().start();
        } catch (MerchantNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    Scanner scanner = new Scanner(System.in);
    MerchantService ms = new MerchantService();

    public void start() throws MerchantNotFoundException {
        while (true) {
            printMenu();
            int i = scanner.nextInt();
            if (i == 9) {
                break;
            } else if (i < 0 || i > 9) {
                System.out.printf("%d not found in menu\n", i);
            } else {
                switch (i) {
                    case 1 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        if (id.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                            try {
                                ms.getMerchantBankAccounts(id).forEach(System.out::print);
                            } catch (NoBankAccountsFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            throw new IllegalArgumentException("Incorrect ID.");
                        }
                    }
                    case 2 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        System.out.println("Enter account number for add.");
                        String num = scanner.next();
                        try {
                            ms.addBankAccount(id, num);
                        } catch (IOException | MerchantNotFoundException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Bank account with " + num + " not added.");
                        }
                    }
                    case 3 -> {
                        System.out.println("Enter account id for update.");
                        String id = scanner.next();
                        System.out.println("Enter new account number.");
                        String newNum = scanner.next();
                        try {
                            ms.updateBankAccount(id, newNum);
                        } catch (IllegalArgumentException | BankAccountNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter merchant id for delete bank account.");
                        String id = scanner.next();
                        try {
                            ms.deleteBankAccount(id);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        try {
                            System.out.print(ms.getMerchantById(id));
                        } catch (MerchantNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 6 -> {
                        if (ms.getMerchants().size() == 0) {
                            throw new MerchantNotFoundException("No merchants in system.");
                        } else {
                            ms.getMerchants().forEach(System.out::print);
                        }
                    }
                    case 7 -> {
                        System.out.println("Enter name new merchant.");
                        String name = scanner.next();
                        ms.createMerchant(new Merchant(name));
                        System.out.println("Merchant with name " + name + " created.");
                    }
                    case 8 -> {
                        System.out.println("Enter merchant id:");
                        String id = scanner.next();
                        if (id.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                            ms.deleteMerchant(id);
                            System.out.println("Merchant with id " + id + " deleted.");
                        } else {
                            throw new IllegalArgumentException("Incorrect ID");
                        }
                    }
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                Select a menu item:
                1 Get info about merchant bank accounts.
                2 Add new bank account.
                3 Update bank account.
                4 Delete bank account.
                5 Get info about merchant.
                6 Get all merchants in system.
                7 Create new merchant.
                8 Delete merchant.
                9 Exit.""");
    }
}