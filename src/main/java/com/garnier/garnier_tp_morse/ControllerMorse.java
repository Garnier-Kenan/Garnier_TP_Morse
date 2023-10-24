package com.garnier.garnier_tp_morse;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerMorse {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}