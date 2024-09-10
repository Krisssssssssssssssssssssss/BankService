package org.example;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {

    private static Map<String, Account> accounts = new HashMap<>();  // Initialized statically here

    public static Map<String, Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(Map<String, Account> accounts) {
        BankService.accounts = accounts;
    }

    public static void openAccount(List<Client> clients) {
        String accountNumber = Instant.now().toString();
        Account newAccount = new Account(accountNumber, BigDecimal.ZERO, clients);
        accounts.put(accountNumber, newAccount);
    }

    public static void openAccount(List<Client> clients, BigDecimal preBalance) {
        String accountNumber = Instant.now().toString();
        Account newAccount = new Account(accountNumber, preBalance, clients);
        accounts.put(accountNumber, newAccount);
    }
    public static void transferAmount(String to, String from, BigDecimal amount) {
        Account fromAccount = accounts.get(from);
        Account toAccount = accounts.get(to);

        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getAccountBalance().compareTo(amount) >= 0) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transferred " + amount + " from " + from + " to " + to);
            } else {
                System.out.println("Insufficient balance in the source account.");
            }
        } else {
            System.out.println("One or both of the accounts were not found.");
        }
    }
    public List<String> split(String accountNumber) {
        Account accountToBeSplit = accounts.get(accountNumber);
        List<String> newAccountNumbers = new ArrayList<>();

        if (accountToBeSplit != null) {
            List<Client> associatedClients = accountToBeSplit.getAssociatedClient();
            int numClients = associatedClients.size();

            if (numClients <= 1) {
                System.out.println("No need to split the account, as there is only one account holder.");
                return newAccountNumbers;
            }

            BigDecimal totalBalance = accountToBeSplit.getAccountBalance();
            BigDecimal share = totalBalance.divide(BigDecimal.valueOf(numClients), 2, RoundingMode.DOWN);

            for (int i = 0; i < numClients; i++) {
                List<Client> singleClientList = new ArrayList<>();
                singleClientList.add(associatedClients.get(i));
                    openAccount(singleClientList, share);
            }
            accounts.remove(accountNumber);
        } else {
            System.out.println("Account with number " + accountNumber + " does not exist.");
        }
        return newAccountNumbers;
    }
}
