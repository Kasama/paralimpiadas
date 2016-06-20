package br.usp.icmc.paralimpiadas;

import br.usp.icmc.paralimpiadas.database.Local;
import javafx.application.Application;
import javafx.application.Platform;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Jogos Paralímpicos - Main");
        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(270);
        primaryStage.setWidth(500);
        primaryStage.show();
        // Updates a text field to have your external IP address, so its easier
        // for you to tell someone to connect to you
        loader.<MainController>getController().setup();
        // Set the platform to exit when the window is closed
        primaryStage.setOnCloseRequest(event -> Platform.exit());

    }

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.load(new FileReader("database.properties"));
            Class.forName(props.getProperty("driver"));
            String driver = buildDriver(props);
            Connection conn = DriverManager.getConnection(driver, props);
            Statement stm = conn.createStatement();
            String sql = "select * from Local";
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Local l = Local.populate(rs);
                System.out.println(l.getNome() + " - " + l.getCapacidade() + " - " + l.getEndereco());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        launch(args);
    }

}
