package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<CreditCard> cards = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        menu(scanner);
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

    public static void userMenu(Scanner scanner, CreditCard card) {
        while (true) {
            System.out.println("""
                    1. Balance
                    2. Log out
                    0. Exit""");
            String input = scanner.next();
            System.out.println();
            switch (input) {
                case "1" -> System.out.println("Balance: " + card.getBalance());
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
        CreditCard creditCard = null;
        for (CreditCard card : cards) {
            if (card.getCardNumber().equals(cardNumber)) {
                if (card.getPinCode().equals(pinCode)) {
                    creditCard = card;
                }
            }
        }
        return creditCard;
    }

    public static void createCreditCard() {
        CreditCard card = new CreditCard();
        cards.add(card);
        System.out.println("Your card has been created");
        System.out.println("Your card number:");
        System.out.println(card.getCardNumber());
        System.out.println("Your card PIN:");
        System.out.println(card.getPinCode());
        System.out.println();
    }
}