package com.garnier.garnier_tp_morse;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMorse implements Initializable {
@FXML
private TextField saisie;
@FXML
private Button traduire;
@FXML
private Circle led;
private TraduireMorse traduireMorse = new TraduireMorse(this);
int point = 500;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.traduire.setOnAction(event -> controller());
    }
    private void controller(){
        if (saisie.getText().length()>0) {
            traduireMorse.traduire(saisie.getText());
        }else{
            System.err.println("Aucun texte saisie");
        }
    }
    public void morse(String message){
    }
}
