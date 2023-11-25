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
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
    @FXML
    private Label mapStatusLabel;
    @FXML
    private TextField initialPoint;
    @FXML
    private TextField finalPoint;
    @FXML
    private Button viajarButton;
    private MapScreen mapScreen;
    private PlayerGenerator<K> playerGenerator;
    private MapGenerator mapGenerator;
    private GraphController<String> graphController;
    private GamePanel gamePanel;
    private GraphGenerator<String> graphGenerator;
    public int dadoResult = 0;

    int life = 3;

    Graphics graphis;

    private ArrayList<Vertex<K>> graphicVertexArrayList;
    private ArrayList<Edge<K>> graphicEdgeArrayList;

    public MapController() {
//        graphicVertexArrayList = graphController.getVertexArrayList();
//        graphicEdgeArrayList = graphController.getEdgeArrayList();
    }

    public void movementAction(){
        int reach = dadoResult;
        String initialPointText = initialPoint.getText();
        String finalPointText = finalPoint.getText();
        graphGenerator.movementDynamic(dadoResult,initialPointText,finalPointText);
    }

    public void caminoLongitudMinima(){
        String initialPointText = initialPoint.getText();
        String finalPointText = finalPoint.getText();
        graphGenerator.mostrarCaminoLongitudMinima(initialPointText,finalPointText);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.graphicVertexArrayList = new ArrayList<>();
        this.graphicEdgeArrayList = new ArrayList<>();
        gamePanel = new GamePanel();
        graphGenerator = new GraphGenerator<>(this.canvas);

        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        //Scene scene = new Scene(new StackPane(canvas), 600, 400);
        this.mapScreen = new MapScreen(this.canvas, graphGenerator);
        String life = String.valueOf(Player.getInstance().getLifeStatus());
        lifeStatusLabel.setText(life);
        paint();
        plotElementsOnMap();
        //gamePanel.paintComponent(graphis);
        new Rectangle(0, 0, 16, 16);
    }

    public int onLanzarDadoButton(){
        int resultadoDado = new Random().nextInt(6) + 1;
        dadoResult = resultadoDado;
        // Mostrar una alerta con el resultado
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado del Dado");
        alert.setHeaderText(null);
        alert.setContentText("El resultado del dado es: " + resultadoDado);
        alert.showAndWait();

        return resultadoDado;
    }

    public void onCavarButton(){

        int item = graphGenerator.checkItemVertex();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        int life = Player.getInstance().getLifeStatus();
        int coin = Player.getInstance().getCoinAmount();
        int map = Player.getInstance().getMapStatus();
        switch(item) {
            case 0:
                System.out.println("Returning to the tittle!");
                break;
            case 1:
                Player.getInstance().setCoinAmount(coin+15);
                alert.setTitle("Monedas encontradas");
                alert.setHeaderText(null);
                alert.setContentText("Encontraste 15 monedas enterradas");
                alert.showAndWait();
                break;
            case 2:
                Player.getInstance().setLifeStatus(life+1);
                alert.setTitle("Encontraste un corazon");
                alert.setHeaderText(null);
                alert.setContentText("Tienes una vida extra");
                alert.showAndWait();
                break;
            case 3:
                Player.getInstance().setLifeStatus(life-1);
                alert.setTitle("Pisaste una trampa");
                alert.setHeaderText(null);
                alert.setContentText("Perdiste una vida");
                alert.showAndWait();
                if(Player.getInstance().getLifeStatus()<1){
                    HelloApplication.hideWindow((Stage)cavarButton.getScene().getWindow());
                    //HelloApplication.showWindow("hello.view", 600.0,400);
                }
                break;
            case 4:
                Player.getInstance().setMapStatus(map + 1);
                alert.setTitle("Encontraste el mapa");
                alert.setHeaderText(null);
                alert.setContentText("Tienes una pista del tesoro");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("No encontraste nada");
                alert.setHeaderText(null);
                alert.setContentText(":(");
                alert.showAndWait();
                break;
            case 6:
                alert.setTitle("Encontraste tu casa");
                alert.setHeaderText(null);
                alert.setContentText("Solo es tu casa");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("TESORO, TESOROOO!!!");
                alert.setHeaderText(null);
                alert.setContentText("HAS ENCONTRADO EL TESORO");
                alert.showAndWait();
                HelloApplication.hideWindow((Stage)cavarButton.getScene().getWindow());
                //HelloApplication.showWindow("hello.view", 600.0,400);
                break;

            default:
                System.out.println("Error, wrong option");

        }

        lifeStatusLabel.setText(String.valueOf(Player.getInstance().getLifeStatus()));
        coinStatusLabel.setText(String.valueOf(Player.getInstance().getCoinAmount()));
        mapStatusLabel.setText(String.valueOf(Player.getInstance().getMapStatus()));
    }

    public void revelarCaminoTesoro(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(Player.getInstance().getMapStatus()>0) {
            alert.setTitle("Informacion de tesoro");
            alert.setHeaderText(null);
            alert.setContentText("La X marca el lugar");
            alert.showAndWait();
            graphGenerator.primAlgorithm();
        }else {
            alert.setTitle("Sin mapa");
            alert.setHeaderText(null);
            alert.setContentText("No tienes pistas del tesoro");
            alert.showAndWait();
        }
        //graphGenerator.primAlgorithm();
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
