package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private final Connection connection;

    public Database(String fileName) throws SQLException {
        String baseUrl = "jdbc:sqlite:";
        String url = baseUrl + fileName;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try {
            Connection con = dataSource.getConnection();
            try (Statement statement = con.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card ("
                        + "id INTEGER,"
                        + "number TEXT,"
                        + "pin TEXT,"
                        + "balance INTEGER DEFAULT 0);");
            }
            this.connection = con;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void insert(CreditCard card) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO card (number, pin) VALUES " +
                    "('" + card.getCardNumber() + "', '" + card.getPinCode() + "');");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public CreditCard verifyLogin(String cardNumber, String pinCode) {
        CreditCard creditCard = null;
        try (Statement statement = connection.createStatement()) {
            try (ResultSet card = statement.executeQuery("SELECT * " +
                    "FROM card " +
                    "WHERE number = '" + cardNumber + "' AND pin = '" + pinCode + "';")) {
                if (card.next()) {
                    creditCard = new CreditCard(card.getString("number"),
                            card.getString("pin"),
                            card.getInt("balance"));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return creditCard;
    }

}
