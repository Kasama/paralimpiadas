package br.usp.icmc.paralimpiadas;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        launch(args);
    }

}
