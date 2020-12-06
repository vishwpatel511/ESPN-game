package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfigration {

    private static final String Connectionaddress = "jdbc:mysql://localhost:3306/assignment?autoReconnect=true&useSSL=false";
    private static final String username = "root";
    private static final String pswd = "";

    public static Connection getconnection() throws SQLException{
        return DriverManager.getConnection(Connectionaddress, username, pswd);
    }

    public static void Exception(SQLException e){
        System.err.println("Error Message: " + e.getMessage());
        System.err.println("Error Code: "+ e.getErrorCode());
        System.err.println("SQL State: " + e.getSQLState());
    }

}
