package br.usp.icmc.paralimpiadas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Jogos Paralímpicos - Consultas");
        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(270);
        primaryStage.setWidth(500);
        primaryStage.show();
        // Updates a text field to have your external IP address, so its easier
        // for you to tell someone to connect to you
        Properties queries = new Properties();
        queries.load(new FileReader("queries.properties"));
        loader.<MainController>getController().setup(queries);
        // Set the platform to exit when the window is closed
        primaryStage.setOnCloseRequest(event -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
