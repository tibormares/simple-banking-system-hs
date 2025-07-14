package banking;

public class CreditCard {
    private final String cardNumber;
    private String pinCode;
    private int balance;

    public CreditCard() {
        cardNumber = generateCardNumber();
        pinCode = generatePinCode();
        balance = 0;
    }

    public String generateCardNumber() {
        String MII = "4";
        String BIN = MII + "00000";

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            int num = (int) (Math.random() * 10);
            sb.append(num);
        }

        String checksum = String.valueOf((int) (Math.random() * 10));
        return BIN + sb + checksum;
    }

    public String generatePinCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
            int num = (int) (Math.random() * 10);
            sb.append(num);
        }
        return sb.toString();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void newGeneratedPinCode() {
        pinCode = generatePinCode();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
        balance = amount;
    }
}
