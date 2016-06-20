package br.usp.icmc.paralimpiadas;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private Properties props;
    private Connection conn;

    public Database(String properiesFileName)
            throws IOException, ClassNotFoundException, SQLException {
        props = new Properties();
        props.load(new FileReader(properiesFileName));
        Class.forName(props.getProperty("driver"));
        String driver = buildDriver(props);
        conn = DriverManager.getConnection(driver, props);
    }

    private static String buildDriver(Properties props) {
        String str = "";
        str += ("jdbc:");
        str += (props.getProperty("adapter"));
        str += ("://");
        str += (props.getProperty("host"));
        str += (":");
        str += (props.getProperty("port"));
        str += ("/");
        str += (props.getProperty("database"));
        return str;
    }

    public ResultSet executeQuery(String sql){
        try {
            return conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
    }
    public boolean execute(String sql){
        try {
            return conn.createStatement().execute(sql);
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public Properties getProperties() {
        return props;
    }
}
