package util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            System.out.println("Connection established successfully!");
            // Proceed with your database operations here
        } catch (SQLException e) {
            System.out.println("Failed to establish connection!");
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
}
