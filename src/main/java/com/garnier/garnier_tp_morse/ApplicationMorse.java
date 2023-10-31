package com.garnier.garnier_tp_morse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ApplicationMorse extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationMorse.class.getResource("viewMorse.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 197, 280);
        stage.setTitle("MorseDecode");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> System.exit(0));
    }
    public static void main(String[] args) {
        launch();
    }
}