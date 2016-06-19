package br.usp.icmc.paralimpiadas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private String driver;
    private String url;

    public Database(){
        this.url = "";
    }

    public void test(){
        try {
            Connection conn = DriverManager.getConnection(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
