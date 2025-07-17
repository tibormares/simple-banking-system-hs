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

    public CreditCard(String cardNumber, String pinCode, int balance) {
        this.cardNumber = cardNumber;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    private String generateCardNumberWithoutChecksum() {
        String MII = "4";
        String BIN = MII + "00000";

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            int num = (int) (Math.random() * 10);
            sb.append(num);
        }
        return BIN + sb;
    }

    private String generateChecksumNumberUsingLuhnAlgorithm(String cardNumberWithoutChecksum) {
        int sum = 0;

        for (int i = 1; i <= cardNumberWithoutChecksum.length(); i++) {
            int number = Integer.parseInt(String.valueOf(cardNumberWithoutChecksum.charAt(i - 1)));
            int result = number;
            if (i % 2 != 0) {
                result = number * 2;
                if (result > 9) {
                    result -= 9;
                }
            }
            sum += result;
        }
        int remainder = sum % 10;
        int checksum = 10 - remainder;
        if (checksum != 10) {
            return String.valueOf(checksum);
        }
        return "0";
    }

    private String generateCardNumber() {
        String cardNumberWithoutChecksum = generateCardNumberWithoutChecksum();
        String checksumNumber = generateChecksumNumberUsingLuhnAlgorithm(cardNumberWithoutChecksum);
        return cardNumberWithoutChecksum + checksumNumber;
    }

    private String generatePinCode() {
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
