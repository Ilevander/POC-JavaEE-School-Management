package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String JDBC_URL = "jdbc:mysql://localhost/schooldb?serverTimezone=UTC";
    private static final String JDBC_USER = "ilyass";
    private static final String JDBC_PASSWORD = "ilyass@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                System.out.println("Failed to close the connection");
                e.printStackTrace();
            }  
        }
    }
}  
