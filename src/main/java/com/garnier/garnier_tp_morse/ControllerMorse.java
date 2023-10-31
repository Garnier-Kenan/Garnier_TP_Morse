package com.garnier.garnier_tp_morse;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMorse implements Initializable {
@FXML
private TextField saisie;
@FXML
private Label sortie;
@FXML
private Circle led;
@FXML
private Button stop;
private TraduireMorse traduireMorse = new TraduireMorse(this);
private Thread onoff;
int point = 500;
String rexex = "[A-Za-z0-9]+( [A-Za-z0-9]+)*";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.sortie.setStyle("-fx-background-color: black");
        this.stop.setStyle("-fx-background-color: red");
        this.stop.setOnAction(event -> stop());
        this.saisie.setOnMouseClicked(event -> this.saisie.selectAll());
        this.saisie.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    controller();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void stop(){
        traduireMorse.stop();
        sortie.setTextFill(Color.YELLOW);
        if (onoff != null){
            if  (onoff.isAlive()){
                onoff.stop();
            }
        }
        led.setFill(Color.RED);
        onoff = new Thread(() ->
        {
            while (true)
            {
                Platform.runLater(() -> sortie.setText("Traduction stopée"));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> sortie.setText(""));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        onoff.start();
    }
    private void controller() throws InterruptedException {
        if (onoff != null){
            if  (onoff.isAlive()){
                onoff.stop();
                led.setFill(Color.BLACK);
            }
        }
        if (saisie.getText().length()>0) {
            if (saisie.getText().matches(rexex)) {
                traduireMorse.traduire(saisie.getText());
                System.out.println("Traduction en cours");
                sortie.setTextFill(Color.GREEN);
                sortie.setText("Traduction en cours");
            }else {
                System.err.println("Format invalide");
                sortie.setTextFill(Color.RED);
                sortie.setText("Format invalide");
            }
        }else{
            System.err.println("Aucun texte saisie");
            sortie.setTextFill(Color.RED);
            sortie.setText("Aucun texte saisie");
        }
    }
    public void morse(String[] message) throws InterruptedException {
        for (int i = 0; i < message.length; i++) {
            System.out.println(message[i].toString());
            if(message[i].equals(".")) {
                Platform.runLater(() ->led.setFill(Color.YELLOW));
                Thread.sleep(point);
                Platform.runLater(() ->led.setFill(Color.BLACK));
            }else if (message[i].equals("-")) {
                Platform.runLater(() ->led.setFill(Color.YELLOW));
                Thread.sleep(point * 3);
                Platform.runLater(() ->led.setFill(Color.BLACK));
            }else if (message[i].equals("\\")) {
                Thread.sleep(point);
            }else if (message[i].equals("/")) {
                Thread.sleep(point*3);
            }else {
                    System.err.println("Erreur traduction");
            }
        }
    }
}
