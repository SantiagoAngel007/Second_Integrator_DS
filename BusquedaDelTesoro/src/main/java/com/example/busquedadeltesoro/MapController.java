package com.example.busquedadeltesoro;

import GraphicElements.GraphicEdge;
import GraphicElements.GraphicVertex;
import Player.Player;
import Screens.MapScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MapController <K extends Comparable<K>> implements Initializable  {

    @FXML
    private Canvas canvas;
    @FXML
    private Button lanzarDado;
    @FXML
    private Button cavarButton;
    @FXML
    private Label lifeStatusLabel;
    @FXML
    private Label coinStatusLabel;
    private MapScreen mapScreen;
    private PlayerGenerator<K> playerGenerator;
    private MapGenerator mapGenerator;
    private GraphController<K> graphController;
    private GamePanel gamePanel;

    int life = 3;

    Graphics graphis;

    private ArrayList<Vertex<K>> graphicVertexArrayList;
    private ArrayList<Edge<K>> graphicEdgeArrayList;

    public MapController() {
        this.graphicVertexArrayList = new ArrayList<>();
        this.graphicEdgeArrayList = new ArrayList<>();
        gamePanel = new GamePanel();
//        graphicVertexArrayList = graphController.getVertexArrayList();
//        graphicEdgeArrayList = graphController.getEdgeArrayList();
    }

    public void movementAction(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        //Scene scene = new Scene(new StackPane(canvas), 600, 400);
        this.mapScreen = new MapScreen(this.canvas);
        String life = String.valueOf(Player.getInstance().getLifeStatus());
        lifeStatusLabel.setText(life);
        paint();
        plotElementsOnMap();
        //gamePanel.paintComponent(graphis);
        new Rectangle(0, 0, 16, 16);
    }

    public void onLanzarDadoButton(){
        int resultadoDado = new Random().nextInt(6) + 1;

        // Mostrar una alerta con el resultado
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado del Dado");
        alert.setHeaderText(null);
        alert.setContentText("El resultado del dado es: " + resultadoDado);
        alert.showAndWait();
    }

    public void onCavarButton(){
        int life = Player.getInstance().getLifeStatus();
        int coin = Player.getInstance().getCoinAmount();
        Player.getInstance().setLifeStatus(life-1);
        Player.getInstance().setCoinAmount(coin+15);
        lifeStatusLabel.setText(String.valueOf(Player.getInstance().getLifeStatus()));
        coinStatusLabel.setText(String.valueOf(Player.getInstance().getCoinAmount()));
    }

    public void plotElementsOnMap() {
        Random random = new Random();

//        for (GraphicVertex graphicVertex : graphicVertexArrayList) {
//            double x = random.nextDouble() * 400; // Rango de coordenadas x entre 0 y 400
//            double y = random.nextDouble() * 400; // Rango de coordenadas y entre 0 y 400
//            graphicVertex.getCirculo().setCenterX(x);
//            graphicVertex.getCirculo().setCenterY(y);
//        }
//
//        for (GraphicEdge graphicEdge : graphicEdgeArrayList) {
//            // Asigna coordenadas aleatorias a las aristas si es necesario
//            // Puedes ajustar esto según tus necesidades
//        }
    }


    public void paint() {
        this.mapScreen.paint();
    }
}
