package banking;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Database database;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            if (args.length < 2 || !args[0].equals("-fileName") || args[1].isEmpty()) {
                System.out.println("""
                    Invalid arguments.
                    -fileName filename""");
                return;
            }
            database = new Database(args[1]);
            menu(scanner);
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void menu(Scanner scanner) {
        while (true) {
            System.out.println("""
                    1. Create an account
                    2. Log into account
                    0. Exit""");
            String input = scanner.next();
            System.out.println();
            switch (input) {
                case "1" -> createCreditCard();
                case "2" -> loginMenu(scanner);
                case "0" -> {
                    System.out.print("Bye!");
                    return;
                }
            }
        }
    }

    public static void loginMenu(Scanner scanner) {
        System.out.println("Enter your card number:");
        String inputCardNumber = scanner.next();
        System.out.println("Enter your PIN:");
        String inputPinCode = scanner.next();
        System.out.println();
        CreditCard creditCard = verifyLogin(inputCardNumber, inputPinCode);
        if (creditCard != null) {
            System.out.println("You have successfully logged in!");
            System.out.println();
            userMenu(scanner, creditCard);
        } else {
            System.out.println("Wrong card number or PIN!");
            System.out.println();
        }
    }

    public static void userMenu(Scanner scanner, CreditCard creditCard) {
        while (true) {
            System.out.println("""
                    1. Balance
                    2. Log out
                    0. Exit""");
            String input = scanner.next();
            System.out.println();
            switch (input) {
                case "1" -> System.out.println("Balance: " + creditCard.getBalance());
                case "2" -> {
                    System.out.println("You have successfully logged out!");
                    System.out.println();
                    return;
                }
                case "0" -> {
                    System.out.print("Bye!");
                    System.exit(0);
                }
            }
            System.out.println();
        }
    }

    public static CreditCard verifyLogin(String cardNumber, String pinCode) {
        return database.verifyLogin(cardNumber, pinCode);
    }

    public static void createCreditCard() {
        CreditCard card = new CreditCard();
        database.insert(card);
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(card.getPinCode());
        System.out.println();
    }
}