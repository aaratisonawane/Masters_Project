package com.se.pranita.termproject.model.common;

import com.se.pranita.termproject.utils.Constants;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Pranita on 16/4/16.
 */
public class ConnectionHandler {

    public static Connection connection;
    private static String address;
    private static String username;
    private static String password;
    private static String dbName;

    private void DbConnection() {
        address = Constants.DBHOSTADDRESS;
        username = Constants.USERNAME;
        password = Constants.PASSWORD;
        dbName = Constants.DATABASENAME;
    }

    //local
//    public static Connection getConnection() {
//        ConnectionHandler cHandler = new ConnectionHandler();
//        cHandler.DbConnection();
//        String connectionString = "jdbc:mysql://" + address + "/" + dbName;
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            connection = DriverManager.getConnection(connectionString, username, password);
//            connection.setAutoCommit(false);
//        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        return connection;
//    }

    //deploy
    public static Connection getConnection() {
        try {
            URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

            String username = jdbUri.getUserInfo().split(":")[0];
            String password = jdbUri.getUserInfo().split(":")[1];
            String port = String.valueOf(jdbUri.getPort());
            String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();
            connection = DriverManager.getConnection(jdbUrl, username, password);
            connection.setAutoCommit(false);
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
