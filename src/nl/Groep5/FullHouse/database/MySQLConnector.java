package nl.Groep5.FullHouse.database;

import java.sql.*;
import java.util.TimeZone;

/**
 * Created by DeStilleGast on 11-9-2016.
 */
public class MySQLConnector {

    private final String host, database, user, password;
    private Connection con;

    /**
     * Create a MySQL Connector and attempt to connect
     * @param host Host of server
     * @param database Database/scheme to connect
     * @param user Username to login
     * @param password Password for user to connect with
     */
    public MySQLConnector(String host, String database, String user, String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;

        connect();
    }

    /**
     * Attempt to open a connection to the database
     */
    private void connect() {
        try {
            System.out.println("[MySQL] Attempting to login to database");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database + "?autoReconnect=true&maxReconnects=2&serverTimezone=" + TimeZone.getDefault().getID(),
                    user, password);


            System.out.println("[MySQL] The connection to MySQL has been made!");
        } catch (SQLException e) {
            System.out.println("[MySQL] The connection to MySQL couldn't be made!\nreason: " + e.getMessage() + ",\nCaused by " + e.getCause().getMessage());
        }
    }

    /**
     * Close connection with database
     */
    public void close() {
        try {
            if (con != null) {
                con.close();
                System.out.println("[MySQL] The connection to MySQL has ended successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Request a prepare statement with the given query
     * @param qry Query
     * @return PreparedStatedment with given query
     */
    public PreparedStatement prepareStatement(String qry) {
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }

    /**
     * Execute a update/insert statement
     * @param statement PrepareStatement with Update/Insert statement
     */
    public void update(PreparedStatement statement) {
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check if there is a connection to the MySQL server
     * @return true if there is a connection
     */
    public boolean hasConnection() {
        return con != null;
    }

    /**
     * Execute SELECT statement
     * @param statement PrepareStatement with SELECT query
     * @return ResultSet from given PrepareStatement/query
     */
    public ResultSet query(PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return null;
    }
}
