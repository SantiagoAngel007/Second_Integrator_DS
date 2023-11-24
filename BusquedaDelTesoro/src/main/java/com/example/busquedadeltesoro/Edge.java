package com.example.busquedadeltesoro;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge<K extends Comparable<K>> extends Group {
    private Vertex<K> origin;
    private Vertex<K> destiny;
    private int weight;
    private Line linea;
    private static final Color COLOR = Color.BLACK;

    public Edge(Vertex<K> origin, Vertex<K> destiny, int weight) {
        this.origin = origin;
        this.destiny = destiny;
        this.weight = weight;

        Line line = new Line(origin.getX(), origin.getY(), destiny.getX(), destiny.getY());
        line.setStroke(COLOR);

//        javafx.scene.control.Label weightLabel = new Label(Integer.toString(weight));
//        Label weightLabel = new Label(Integer.toString(weight));
//        weightLabel.setLayoutX((origin.getX() + destiny.getX()) / 2);
//        weightLabel.setLayoutY((origin.getY() + destiny.getX()) / 2);
//        weightLabel.setTextFill(Color.RED);

//         Agregar tanto la línea como la Label al Group actual (instancia de Edge)
//        getChildren().addAll(line, weightLabel);
    }

//    public Edge(Vertex<K> origin, Vertex<K> destiny, int weight) {
//        this.origin = origin;
//        this.destiny = destiny;
//        this.weight = weight;
//    }

    Line getLine() {
        Line line = new Line(origin.getX(), origin.getY(), destiny.getX(), destiny.getY());
        line.setStroke(COLOR);
        return line;
    }

    public Label getWeightLabel() {
        javafx.scene.control.Label weightLabel = new Label(Integer.toString(weight));
        weightLabel.setLayoutX((origin.getX() + destiny.getX()) / 2);
        weightLabel.setLayoutY((origin.getY() + destiny.getY()) / 2);
        weightLabel.setTextFill(Color.RED);
        return weightLabel;
    }

    public Vertex<K> getOrigin() {
        return origin;
    }

    public void setOrigin(Vertex<K> origin) {
        this.origin = origin;
    }

    public Vertex<K> getDestiny() {
        return destiny;
    }

    public void setDestiny(Vertex<K> destiny) {
        this.destiny = destiny;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}