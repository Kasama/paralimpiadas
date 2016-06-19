package br.usp.icmc.paralimpiadas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            props.load(new FileReader("database.properties"));
            String driver = buildDriver(props);
            Connection conn = DriverManager.getConnection(driver, props);
            Statement stm = conn.createStatement();
            String sql = "select * from Local";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                String nome = rs.getString("nome");
                int cap = rs.getInt("capacidade");
                String endereco = rs.getString("endereco");
                System.out.println(nome + " - " + cap + " - " + endereco);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        launch(args);
    }

    private static String buildDriver(Properties props) {
        StringBuilder str = new StringBuilder();
        str.append("jdbc:");
        str.append(props.getProperty("adapter"));
        str.append("://");
        str.append(props.getProperty("host"));
        str.append(":");
        str.append(props.getProperty("port"));
        str.append("/");
        str.append(props.getProperty("database"));
        return str.toString();
    }
}
