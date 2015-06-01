/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottopicker;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMSSQLServer {

    private Connection con = null;
    private final String url = "jdbc:sqlserver://";
    private final String serverName = "127.0.0.1";
    private final String portNumber = "1433";
    private final String databaseName = "dev";
    private final String userName = "sa";
    private final String password = "MSDBConnect";
    private final String selectMethod = "cursor";

    private String getConnectionUrl() {
        return url + serverName + ":" + portNumber + ";databaseName=" + databaseName + " ;selectMethod=" + selectMethod + ";";
    }

    private Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.con = DriverManager.getConnection(getConnectionUrl(), "sa", "SarudnoH1967");
            if (this.con != null) {
                System.out.println("Connection Successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Trace in getConnection() : " + e.getMessage());
        }
        return this.con;
    }

    public void displayDbProperties() {
        DatabaseMetaData dm = null;
        ResultSet rs = null;
        try {
            this.con = getConnection();
            if (this.con != null) {
                dm = this.con.getMetaData();
                System.out.println("Driver Information");
                System.out.println("\tDriver Name: " + dm.getDriverName());
                System.out.println("\tDriver Version: " + dm.getDriverVersion());
                System.out.println("\nDatabase Information ");
                System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
                System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
                System.out.println("Avalilable Catalogs ");
                rs = dm.getCatalogs();
                while (rs.next()) {
                    System.out.println("\tcatalog: " + rs.getString(1));
                }
                rs.close();
                rs = null;
                closeConnection();
            } else {
                System.out.println("Error: No active Connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dm = null;
    }

    public ResultSet viewTable(String sqlStr)
            throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            this.con = getConnection();
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(sqlStr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void closeConnection() {
        try {
            if (this.con != null) {
                this.con.close();
            }
            this.con = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
