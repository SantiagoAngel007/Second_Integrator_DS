package com.example.busquedadeltesoro;

import Player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ImageView tresureImage;

    @FXML
    private Button startGame;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onStartGameButtonClick() {
        Player.getInstance().setLifeStatus(3);
        Player.getInstance().setCoinAmount(0);
        HelloApplication.hideWindow((Stage)this.tresureImage.getScene().getWindow());
        HelloApplication.showWindow("tresureMap-view", 1550, 850);
        //HelloApplication.showWindow("tresureMap-view", null);
    }
}