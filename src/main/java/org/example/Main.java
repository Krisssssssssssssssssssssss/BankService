package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Client client1 = new Client("John", "Doe", 1);
        Client client2 = new Client("Jack", "Smith", 2);
        List<Client> clientList = new ArrayList<>(Arrays.asList(client1, client2));
        BankService.openAccount(clientList);
        System.out.println(BankService.getAccounts().size());
        System.out.println("Those are the accounts: " + BankService.getAccounts());

        Client client3 = new Client("Kris", "Kris", 3);
        List<Client> clientList2 = new ArrayList<>(Arrays.asList(client3));
        BankService.openAccount(clientList2);
        System.out.println(BankService.getAccounts().size());
        System.out.println("Those are the accounts: " + BankService.getAccounts());

    }
}