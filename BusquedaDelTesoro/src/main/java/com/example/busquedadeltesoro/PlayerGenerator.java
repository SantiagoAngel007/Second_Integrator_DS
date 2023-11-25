package com.example.busquedadeltesoro;

import GraphicElements.ItemType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PlayerGenerator <K extends Comparable<K>> {
    private double x;
    private double y;
    private Image playerImage;
    private ImageView imageView;
    private Vertex<String> actualVertex;

    Circle circle;

    private static final double RADIUS = 5;

    public PlayerGenerator(double x, double y, Vertex<String> actualVertex) {
        this.x = x;
        this.y = y;
        this.actualVertex = actualVertex;
        //this.playerImage = new Image(imagePath);
        this.imageView = new ImageView(playerImage);
        this.imageView.setX(x);
        this.imageView.setY(y);
        circle = new Circle(x, y, RADIUS, Color.ORANGE);
    }

    Circle getPlayerCircle() {
        return circle;
    }

    public void placePlayerOnVertex() {
        if (actualVertex != null) {
            double vertexX = actualVertex.getX();
            double vertexY = actualVertex.getY();

            // Ajustar las coordenadas del jugador al centro del círculo del vértice
            setX(vertexX);
            setY(vertexY);
            circle.setCenterX(vertexX);
            circle.setCenterY(vertexY);
        }
    }

    public Vertex<String> getActualVertex() {
        return actualVertex;
    }

    public void setActualVertex(Vertex<String> actualVertex) {
        this.actualVertex = actualVertex;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.imageView.setX(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.imageView.setY(y);
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String imagePath) {
        this.playerImage = new Image(imagePath);
        this.imageView.setImage(playerImage);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
